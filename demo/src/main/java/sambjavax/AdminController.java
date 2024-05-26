package sambjavax;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

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
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminController {

    @FXML
    private Button botonEntrarAdmin;
    @FXML
    private Button botonVolver;

    @FXML
    private PasswordField fieldContraseñaAdmin;

    @FXML
    private TextField fieldUsuarioAdmin;

    @FXML
    private void volver() throws IOException {
        App.setRoot("LoginVerdad");
    }
    @FXML
    private void login() throws IOException {
        String usuario = fieldUsuarioAdmin.getText();
        String contrasena = fieldContraseñaAdmin.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos.");
            return;
        }

        if (validarCredenciales(usuario, contrasena)) {
            // Acceso permitido, ir a la siguiente página
            // Por ejemplo, cargar el FXML correspondiente y mostrar la interfaz de administrador
            System.out.println("Acceso permitido al panel de administrador.");
            App.setRoot("RegisterVerdad");
        } else {
            showAlert("Error", "Credenciales incorrectas o acceso no autorizado.");
        }
    }

    private boolean validarCredenciales(String usuario, String contrasena) {
        // Validar las credenciales en la base de datos
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String dbUsuario = "root";
        String dbContrasena = "dbrootpass";

        try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbContrasena)) {
            String query = "SELECT contrasena, salt, rol FROM Usuarios WHERE nombre_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Si hay resultados, obtener la contraseña y el salt de la base de datos
                String contrasenaEncriptada = rs.getString("contrasena");
                String saltBase64 = rs.getString("salt");
                String rol = rs.getString("rol");

                // Convertir el salt de Base64 a byte[]
                byte[] salt = Base64.getDecoder().decode(saltBase64);

                // Hashear la contraseña proporcionada por el usuario con el salt de la base de datos
                String contrasenaIngresadaEncriptada = hashPassword(contrasena, salt);

                // Comparar la contraseña ingresada encriptada con la contraseña encriptada almacenada en la base de datos
                return contrasenaIngresadaEncriptada.equals(contrasenaEncriptada) && "Admin".equals(rol);
            } else {
                return false; // Credenciales incorrectas o usuario no encontrado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
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
