package sambjavax;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField fieldUsuario;

    @FXML
    private PasswordField fieldContraseña;

    @FXML
    private Button botonEntrar;
    @FXML
    private Button botonAdmin;


    @FXML
    private void crearUser() throws IOException {
        App.setRoot("AdminVerdad");
    }
    @FXML
    private void login() throws IOException {
        String usuario = fieldUsuario.getText();
        String contrasena = fieldContraseña.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            showAlert("Error", "Por favor, rellene todos los campos.");
            return;
        }

        if (autenticarUsuario(usuario, contrasena)) {
            showAlert("Éxito", "Login exitoso.");
            // Consulta la base de datos para obtener el id_usuario del usuario autenticado
    int idUsuario = obtenerIdUsuario(usuario);
    
    // Almacena el id_usuario en la variable de sesión
    Session.userId = idUsuario;
            App.setRoot("menu");
        } else {
            showAlert("Error", "Usuario o contraseña incorrectos.");
        }
    }

    // Método para obtener el id_usuario del usuario autenticado
private int obtenerIdUsuario(String usuario) {
    String url = "jdbc:mysql://localhost:3306/cesdb2022";
    String dbUsuario = "root";
    String dbContrasena = "dbrootpass";
    
    try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbContrasena)) {
        String query = "SELECT id_usuario FROM Usuarios WHERE nombre_usuario = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, usuario);
    
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_usuario");
        } else {
            return -1; // Usuario no encontrado
        }
    
    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Error", "Ocurrió un error al conectar con la base de datos.");
        return -1;
    }
}

    private boolean autenticarUsuario(String usuario, String contrasena) {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String dbUsuario = "root";
        String dbContrasena = "dbrootpass";
    
        try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbContrasena)) {
            String query = "SELECT contrasena, salt FROM Usuarios WHERE nombre_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("contrasena");
                byte[] salt = Base64.getDecoder().decode(rs.getString("salt"));
                String enteredHash = hashPassword(contrasena, salt);
                return storedHash.equals(enteredHash);
            } else {
                return false;
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrió un error al conectar con la base de datos.");
            return false;
        }
    }
    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}