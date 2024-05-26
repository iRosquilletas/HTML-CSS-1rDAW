package sambjavax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
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

public class InformacionController implements Initializable {

    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonDescargar;
    @FXML
    private Button botonVolver;
    @FXML
    private TableView<Informacion> tablaRegistro;

    @FXML
    private TableColumn<Informacion, Integer> id;

    @FXML
    private TableColumn<Informacion, String> bastidor;

    @FXML
    private TableColumn<Informacion, String> marca;

    @FXML
    private TableColumn<Informacion, String> matricula;

    @FXML
    private TableColumn<Informacion, String> modelo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        bastidor.setCellValueFactory(cellData -> cellData.getValue().bastidorProperty());
        marca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        matricula.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());
        modelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());

        try {
            ObservableList<Informacion> registros = getInformacion();
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
    private void handleBotonEliminar(ActionEvent event) {
        Informacion registroSeleccionado = tablaRegistro.getSelectionModel().getSelectedItem();
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
    

    @FXML
    private void handleBotonDescargarPDF(ActionEvent event) {
        System.out.println("Botón Descargar PDF presionado...");
        List<Informacion> data = tablaRegistro.getItems();
        PDF_informacion pdfGenerator = new PDF_informacion();
        try {
            pdfGenerator.generatePDF(data);
            System.out.println("PDF generado con éxito.");
            // Mostrar un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("PDF generado con éxito.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al generar el PDF.");
            alert.showAndWait();
        }
    }
    
    
    public ObservableList<Informacion> getInformacion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String user = "root";
        String password = "dbrootpass";

        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Motocicletas");
        ResultSet rs = stmt.executeQuery();

        ObservableList<Informacion> registros = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("id_moto");
            String bastidor = rs.getString("numero_bastidor");
            String marca = rs.getString("marca");
            String matricula = rs.getString("matricula");
            String modelo = rs.getString("modelo");

            Informacion registro = new Informacion(id, bastidor, marca, matricula, modelo);
            registros.add(registro);
        }
        tablaRegistro.setItems(registros);
        rs.close();
        stmt.close();
        conn.close();
        return registros;
    }
}
