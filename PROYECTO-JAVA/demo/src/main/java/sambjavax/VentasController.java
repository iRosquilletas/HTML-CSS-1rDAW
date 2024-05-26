package sambjavax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class VentasController implements Initializable {

    @FXML
    private Button botonEliminar;

    @FXML
    private Button fieldAplicar;
    @FXML
    private Button botonVolver;

    @FXML
    private TextField fieldCorreo;

    @FXML
    private TextField fieldDireccion;

    @FXML
    private TextField fieldDni;

    @FXML
    private TextField fieldNombre;

    @FXML
private TextField fieldIdMoto;

    @FXML
    private TextField fieldTelefono;
    
    @FXML
    private TableView<Ventas> tablaVentas;

    @FXML
    private TableView<MotocicletaVentas> tablaRegistro;

    // Columnas para la tabla de ventas
    @FXML
    private TableColumn<Ventas, String> nombre;
    @FXML
    private TableColumn<Ventas, String> correo;
    @FXML
    private TableColumn<Ventas, String> telefono;
    @FXML
    private TableColumn<Ventas, String> direccion;
    @FXML
    private TableColumn<Ventas, String> dni;
    @FXML
    private TableColumn<Ventas, Integer> id_venta;
    @FXML
    private TableColumn<Ventas, Integer> id_usuario;
    @FXML
    private TableColumn<Ventas, Integer> id_moto;

    // Columnas para la tabla de motocicletas
    @FXML
    private TableColumn<MotocicletaVentas, String> marca;
    @FXML
    private TableColumn<MotocicletaVentas, String> modelo;
    @FXML
    private TableColumn<MotocicletaVentas, String> matricula;
    @FXML
    private TableColumn<MotocicletaVentas, String> bastidor;
    @FXML
    private TableColumn<MotocicletaVentas, Integer> id_motoA;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar columnas para la tabla de ventas
        nombre.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        dni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        id_venta.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        id_usuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        id_moto.setCellValueFactory(new PropertyValueFactory<>("idMoto"));

        // Configurar columnas para la tabla de motocicletas
        marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        bastidor.setCellValueFactory(new PropertyValueFactory<>("bastidor"));
        id_motoA.setCellValueFactory(new PropertyValueFactory<>("id_motoA"));

        try {
            ObservableList<Ventas> ventas = getVentas();
            tablaVentas.setItems(ventas);

            ObservableList<MotocicletaVentas> motocicletas = getMotocicletas();
            tablaRegistro.setItems(motocicletas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML 
    private void handleBotonVolver() throws IOException {
        App.setRoot("menu");
    }


 @FXML
private void handleBotonAplicar(ActionEvent event) {
    String nombre = fieldNombre.getText();
    String correo = fieldCorreo.getText();
    String telefono = fieldTelefono.getText();
    String direccion = fieldDireccion.getText();
    String dni = fieldDni.getText();
    int idUsuario = Session.userId; // Aquí estableces el id_usuario apropiado
    int idMotoSeleccionada = Integer.parseInt(fieldIdMoto.getText()); // Obtén el id_moto desde el campo de texto

    // Validar que los campos no estén vacíos
    if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || dni.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, complete todos los campos.");
        alert.showAndWait();
        return;
    }

    // Insertar los datos en la base de datos
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cesdb2022", "root", "dbrootpass");

        // Insertar el registro en la tabla Ventas
        String insertQuery = "INSERT INTO Ventas (id_usuario, cliente, correo, telefono, direccion, dni, id_moto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
        insertStatement.setInt(1, idUsuario);
        insertStatement.setString(2, nombre);
        insertStatement.setString(3, correo);
        insertStatement.setString(4, telefono);
        insertStatement.setString(5, direccion);
        insertStatement.setString(6, dni);
        insertStatement.setInt(7, idMotoSeleccionada);

        insertStatement.executeUpdate();

        // Realizar el JOIN y guardar la información en motosVendidas.txt
        saveVentasInfoToFile(conn);

        // Eliminar la moto seleccionada de la tabla Motocicletas
        //String deleteQuery = "DELETE FROM Motocicletas WHERE id_moto = ?";
        //PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
        //deleteStatement.setInt(1, idMotoSeleccionada);
        //deleteStatement.executeUpdate();

        // Actualizar la tabla
        ObservableList<Ventas> ventas = getVentas();
        tablaVentas.setItems(ventas);

        ObservableList<MotocicletaVentas> motocicletas = getMotocicletas();
        tablaRegistro.setItems(motocicletas);

        // Limpiar los campos después de agregar el registro
        fieldNombre.clear();
        fieldCorreo.clear();
        fieldTelefono.clear();
        fieldDireccion.clear();
        fieldDni.clear();

        // Mostrar un mensaje de éxito
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Registro agregado con éxito.");
        alert.showAndWait();
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        // Manejar cualquier excepción que pueda ocurrir al ejecutar la consulta SQL
        // Mostrar un mensaje de error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error al agregar el registro. Por favor, inténtelo de nuevo." + e.getMessage());
        alert.showAndWait();
    }
}

private void saveVentasInfoToFile(Connection conn) throws SQLException, IOException {
    String joinQuery = "SELECT V.id_venta, V.id_usuario, V.cliente, V.correo, V.telefono, V.direccion, V.dni, V.id_moto, M.marca, M.modelo, M.matricula, M.numero_bastidor " +
            "FROM Ventas V " +
            "JOIN Motocicletas M ON V.id_moto = M.id_moto";

    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(joinQuery);

    String filePath = "/home/samy/Desktop/1rDAW/Java/PROYECTO-JAVA/demo/src/main/java/sambjavax/motosVendidas/motosVendidas.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
        while (rs.next()) {
            int idVenta = rs.getInt("id_venta");
            int idUsuario = rs.getInt("id_usuario");
            String cliente = rs.getString("cliente");
            String correo = rs.getString("correo");
            String telefono = rs.getString("telefono");
            String direccion = rs.getString("direccion");
            String dni = rs.getString("dni");
            int idMoto = rs.getInt("id_moto");
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");
            String matricula = rs.getString("matricula");
            String bastidor = rs.getString("numero_bastidor");

            String record = String.format("ID Venta: %d, ID Usuario: %d, Cliente: %s, Correo: %s, Telefono: %s, Direccion: %s, DNI: %s, ID Moto: %d, Marca: %s, Modelo: %s, Matricula: %s, Bastidor: %s%n",
                    idVenta, idUsuario, cliente, correo, telefono, direccion, dni, idMoto, marca, modelo, matricula, bastidor);
            writer.write(record);
        }
    }
   
    rs.close();
    stmt.close();
}

@FXML
private void handleEliminarVenta(ActionEvent event) {
    // Obtener la venta seleccionada en la tabla
    Ventas ventaSeleccionada = tablaVentas.getSelectionModel().getSelectedItem();

    if (ventaSeleccionada != null) {
        try {
            // Eliminar la venta de la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cesdb2022", "root", "dbrootpass");
            String deleteQuery = "DELETE FROM Ventas WHERE id_venta = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, ventaSeleccionada.getIdVenta());
            deleteStatement.executeUpdate();
            conn.close();

            // Actualizar la tabla de ventas
            ObservableList<Ventas> ventas = getVentas();
            tablaVentas.setItems(ventas);

            // Mostrar un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Venta eliminada con éxito.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            // Mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al eliminar la venta. Por favor, inténtelo de nuevo.");
            alert.showAndWait();
        }
    } else {
        // Mostrar un mensaje si no se ha seleccionado ninguna venta
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, seleccione una venta para eliminar.");
        alert.showAndWait();
    }
}
    public ObservableList<Ventas> getVentas() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String user = "root";
        String password = "dbrootpass";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Ventas");

        ObservableList<Ventas> ventas = FXCollections.observableArrayList();
        while (rs.next()) {
            int idVenta = rs.getInt("id_venta");
            int idUsuario = rs.getInt("id_usuario");
            String cliente = rs.getString("cliente");
            String correo = rs.getString("correo");
            String telefono = rs.getString("telefono");
            String direccion = rs.getString("direccion");
            String dni = rs.getString("dni");
            int idMoto = rs.getInt("id_moto");

            Ventas venta = new Ventas(idVenta, idUsuario, cliente, correo, telefono, direccion, dni, idMoto);
            ventas.add(venta);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        return ventas;
    }

    public ObservableList<MotocicletaVentas> getMotocicletas() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String user = "root";
        String password = "dbrootpass";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Motocicletas");

        ObservableList<MotocicletaVentas> motocicletas = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("id_moto");
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");
            String matricula = rs.getString("matricula");
            String bastidor = rs.getString("numero_bastidor");

            MotocicletaVentas moto = new MotocicletaVentas(id, marca, modelo, matricula, bastidor);
            motocicletas.add(moto);
        }

        rs.close();
        stmt.close();
        conn.close();
        return motocicletas;
    }
}
