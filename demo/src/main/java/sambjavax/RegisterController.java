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
public class RegisterController implements Initializable {

    @FXML
    private TextField fieldUsuario;

    @FXML
    private PasswordField fieldContraseña;

    @FXML
    private Button botonAceptar;
    @FXML
    private TableView<Register> tablaAdmin;

    @FXML
    private TableColumn<Register, String> idUsuario;

    @FXML
    private TableColumn<Register, String> idContraseña;

    @FXML
    private TableColumn<Register, String> idNombre;

    @FXML
    private TableColumn<Register, String> idRol;

    @FXML
    private TableColumn<Register, String> idSalt;
    @FXML
    private Button botonVolver;

    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonDescargar;

    @FXML
    private ChoiceBox<String> rol;
    private String[] roles = {"Empleado","Admin"};

    private ObservableList<Register> usuarios = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar ChoiceBox
        rol.getItems().addAll(roles);
        botonDescargar.setOnAction(event -> descargarPDF());
        // Enlazar columnas con propiedades de la clase Register
        idUsuario.setCellValueFactory(new PropertyValueFactory<>("id"));
        idContraseña.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        idNombre.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        idRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        idSalt.setCellValueFactory(new PropertyValueFactory<>("salt"));

        // Inicializar la lista observable
        usuarios = FXCollections.observableArrayList();

        // Cargar usuarios desde la base de datos y establecerlos en la tabla
        refrescarTabla();
    }
    @FXML
    private void volver() throws IOException {
        App.setRoot("LoginVerdad");
    }

    private void refrescarTabla() {
        usuarios.clear();
        usuarios.addAll(cargarUsuariosDesdeBD());
        tablaAdmin.setItems(usuarios);
        tablaAdmin.refresh();  // Asegúrate de refrescar la tabla
    }

    private ObservableList<Register> cargarUsuariosDesdeBD() {
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String dbUsuario = "root";
        String dbContrasena = "dbrootpass";

        ObservableList<Register> usuarios = FXCollections.observableArrayList();  // Lista observable de usuarios

        try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbContrasena);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuarios");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String usuario = rs.getString("nombre_usuario");
                String contraseña = rs.getString("contrasena");
                String rol = rs.getString("rol");
                String salt = rs.getString("salt");

                Register nuevoUsuario = new Register(0, usuario, contraseña, rol, salt);
                usuarios.add(nuevoUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudieron cargar los usuarios desde la base de datos.");
        }

        return usuarios;
    }

    @FXML
private void descargarPDF() {
    PDF_register pdfGenerator = new PDF_register();
    try {
        pdfGenerator.generateUserPDF(usuarios);
        showAlert("Éxito", "El PDF ha sido generado correctamente.");
    } catch (IOException e) {
        e.printStackTrace();
        showAlert("Error", "Ocurrió un error al generar el PDF.");
    }
}

    @FXML
private void eliminarUsuario() {
    // Obtener el usuario seleccionado en la tabla
    Register usuarioSeleccionado = tablaAdmin.getSelectionModel().getSelectedItem();

    // Verificar que se haya seleccionado un usuario
    if (usuarioSeleccionado == null) {
        showAlert("Error", "Por favor, seleccione un usuario para eliminar.");
        return;
    }

    // Verificar si el usuario seleccionado es el mismo que está actualmente en sesión
    if (usuarioSeleccionado.getUsuario().equals(fieldUsuario.getText())) {
        showAlert("Error", "No puedes eliminar tu propio usuario.");
        return;
    }

    // Eliminar el usuario de la base de datos
    String url = "jdbc:mysql://localhost:3306/cesdb2022";
    String dbUsuario = "root";
    String dbContrasena = "dbrootpass";

    try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbContrasena)) {
        String query = "DELETE FROM Usuarios WHERE nombre_usuario = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, usuarioSeleccionado.getUsuario());

        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            // Eliminar el usuario de la lista observable
            usuarios.remove(usuarioSeleccionado);
            // Establecer la lista actualizada en la tabla
            tablaAdmin.setItems(usuarios);
            // Mostrar mensaje de éxito
            showAlert("Éxito", "Usuario eliminado correctamente.");
        } else {
            showAlert("Error", "Ocurrió un error al eliminar el usuario.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Error", "Ocurrió un error al eliminar el usuario.");
    }
}


    private boolean crearUsuario(String usuario, String contrasena, String rolSeleccionado) {
        // Generar salt aleatorio
        byte[] salt = generateSalt();
    
        // Hashear la contraseña con el salt
        String hashedPassword = hashPassword(contrasena, salt);
    
        // Insertar nuevo usuario en la base de datos
        String url = "jdbc:mysql://localhost:3306/cesdb2022";
        String dbUsuario = "root";
        String dbContrasena = "dbrootpass";
    
        try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbContrasena)) {
            String query = "INSERT INTO Usuarios (nombre_usuario, contrasena, salt, rol) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, Base64.getEncoder().encodeToString(salt));
            stmt.setString(4, rolSeleccionado);
    
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @FXML
    private void crearUsuario() throws IOException {
        String usuario = fieldUsuario.getText();
        String contrasena = fieldContraseña.getText();
        String rolSeleccionado = rol.getValue(); // Obtener el rol seleccionado
    
        if (usuario.isEmpty() || contrasena.isEmpty() || rolSeleccionado == null) {
            showAlert("Error", "Por favor, complete todos los campos.");
            return;
        }
    
        if (crearUsuario(usuario, contrasena, rolSeleccionado)) {
            showAlert("Éxito", "Usuario creado correctamente.");
            App.setRoot("AdminVerdad");
        } else {
            showAlert("Error", "Ocurrió un error al crear el usuario.");
        }
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
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