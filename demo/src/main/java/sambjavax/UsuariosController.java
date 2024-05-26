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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    @FXML
    private TableView<Usuarios> tablaUsuarios;

    @FXML
    private TableColumn<Usuarios, String> nombre;

    @FXML
    private TableColumn<Usuarios, String> direccion;

    @FXML
    private TableColumn<Usuarios, String> ip_address;

    @FXML
    private TableColumn<Usuarios, Integer> id;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Usuarios> usuarios = getTodosUsuarios();
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            ip_address.setCellValueFactory(new PropertyValueFactory<>("ip_address"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablaUsuarios.setItems(usuarios);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Usuarios> getTodosUsuarios() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String user = "root";
        String password = "dbrootpass";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tabla_prueba");

        ObservableList<Usuarios> usuarios = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("first_name");
            String direccion = rs.getString("email");
            String ip_address = rs.getString("ip_address");

            Usuarios usuario = new Usuarios(id, nombre, direccion, ip_address);
            usuarios.add(usuario);
        }

        tablaUsuarios.setItems(usuarios);
        rs.close();
        stmt.close();
        conn.close();
        return usuarios; // Se elimina la llamada a tablaUsuarios.setItems(usuarios);
    }

}

// tableUsuarios.setItems(usuarios);
// return usuarios;