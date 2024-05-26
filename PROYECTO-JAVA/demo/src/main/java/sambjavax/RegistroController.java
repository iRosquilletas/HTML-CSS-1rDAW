/*package sambjavax;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {

    @FXML
    private TableView<Registro> tablaRegistro;

    @FXML
    private TableColumn<Registro, Integer> id;
    @FXML
    private TableColumn<Registro, String> bastidor;
    @FXML
    private TableColumn<Registro, String> marca;
    @FXML
    private TableColumn<Registro, String> matricula;
    @FXML
    private TableColumn<Registro, String> modelo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id_moto"));
        bastidor.setCellValueFactory(new PropertyValueFactory<>("bastidor"));
        marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        try {
            ObservableList<Registro> registros = getRegistro();
            tablaRegistro.setItems(registros);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Registro> getRegistro() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String user = "root";
        String password = "dbrootpass";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Motocicletas");

        ObservableList<Registro> registros = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("id_moto");
            String bastidor = rs.getString("numero_bastidor");
            String marca = rs.getString("marca");
            String matricula = rs.getString("matricula");
            String modelo = rs.getString("modelo");

            Registro registro = new Registro(id, bastidor, marca, matricula, modelo);
            registros.add(registro);
        }
        tablaRegistro.setItems(registros);
        rs.close();
        stmt.close();
        conn.close();
        return registros;
    }
}
*/
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
public class RegistroController {

    @FXML
    private TextField fieldMarca;
    @FXML
    private TextField fieldModelo;
    @FXML
    private TextField fieldMatricula;
    @FXML
    private TextField fieldBastidor;
    @FXML
    private Button botonAplicar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonVolver;
    @FXML
    private TableView<Registro> tablaRegistro;
    @FXML
    private TableColumn<Registro, Integer> id;
    @FXML
    private TableColumn<Registro, String> bastidor;
    @FXML
    private TableColumn<Registro, String> marca;
    @FXML
    private TableColumn<Registro, String> matricula;
    @FXML
    private TableColumn<Registro, String> modelo;

    @FXML
    private void initialize() {
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        bastidor.setCellValueFactory(cellData -> cellData.getValue().bastidorProperty());
        marca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        matricula.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());
        modelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
    
        try {
            ObservableList<Registro> registros = getRegistro();
            tablaRegistro.setItems(registros);
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
        String marca = fieldMarca.getText();
        String modelo = fieldModelo.getText();
        String matricula = fieldMatricula.getText();
        String bastidor = fieldBastidor.getText();

        // Validar que los campos no estén vacíos
        if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || bastidor.isEmpty()) {
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
            String query = "INSERT INTO Motocicletas (marca, modelo, matricula, numero_bastidor) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, matricula);
            statement.setString(4, bastidor);
            statement.executeUpdate();
            
            // Actualizar la tabla
            ObservableList<Registro> registros = getRegistro();
            tablaRegistro.setItems(registros);

            // Limpiar los campos después de agregar el registro
            fieldMarca.clear();
            fieldModelo.clear();
            fieldMatricula.clear();
            fieldBastidor.clear();

            // Mostrar un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Registro agregado con éxito.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar cualquier excepción que pueda ocurrir al ejecutar la consulta SQL
            // Mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al agregar el registro. Por favor, inténtelo de nuevo."+ e.getMessage());
            alert.showAndWait();
            
        }
    }

    @FXML
private void handleBotonEliminar(ActionEvent event) {
    Registro registroSeleccionado = tablaRegistro.getSelectionModel().getSelectedItem();
    if (registroSeleccionado == null) {
        // Si no se ha seleccionado ninguna fila, muestra un mensaje de error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, seleccione una fila para eliminar.");
        alert.showAndWait();
        return;
    }

    try {
        // Obtener la conexión a la base de datos
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cesdb2022", "root", "dbrootpass");

        // Crear la consulta SQL para eliminar el registro
        String query = "DELETE FROM Motocicletas WHERE id_moto = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, registroSeleccionado.getId());

        // Ejecutar la consulta SQL
        int rowsDeleted = statement.executeUpdate();

        if (rowsDeleted > 0) {
            // Si se eliminó correctamente, quitar la fila de la tabla
            tablaRegistro.getItems().remove(registroSeleccionado);

            // Mostrar un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Registro eliminado con éxito.");
            alert.showAndWait();
        } else {
            // Si no se pudo eliminar, mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al eliminar el registro. Por favor, inténtelo de nuevo.");
            alert.showAndWait();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar cualquier excepción que pueda ocurrir al ejecutar la consulta SQL
        // Mostrar un mensaje de error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error al eliminar el registro. Por favor, inténtelo de nuevo.");
        alert.showAndWait();
    }
}

    public ObservableList<Registro> getRegistro() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String user = "root";
        String password = "dbrootpass";

        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Motocicletas");
        ResultSet rs = stmt.executeQuery();

        ObservableList<Registro> registros = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("id_moto");
            String bastidor = rs.getString("numero_bastidor");
            String marca = rs.getString("marca");
            String matricula = rs.getString("matricula");
            String modelo = rs.getString("modelo");

            Registro registro = new Registro(id, bastidor, marca, matricula, modelo);
            registros.add(registro);
        }
        tablaRegistro.setItems(registros);
        rs.close();
        stmt.close();
        conn.close();
        return registros;
    }
}
