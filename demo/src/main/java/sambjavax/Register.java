package sambjavax;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Register {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty usuario = new SimpleStringProperty();
    private final SimpleStringProperty contraseña = new SimpleStringProperty();
    private final SimpleStringProperty rol = new SimpleStringProperty();
    private final SimpleStringProperty salt = new SimpleStringProperty();

    public Register(int id, String usuario, String contraseña, String rol, String salt) {
        setId(id);
        setUsuario(usuario);
        setContraseña(contraseña);
        setRol(rol);
        setSalt(salt);
    }

    // Getters y setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public SimpleStringProperty usuarioProperty() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña.get();
    }

    public void setContraseña(String contraseña) {
        this.contraseña.set(contraseña);
    }

    public SimpleStringProperty contraseñaProperty() {
        return contraseña;
    }

    public String getRol() {
        return rol.get();
    }

    public void setRol(String rol) {
        this.rol.set(rol);
    }

    public SimpleStringProperty rolProperty() {
        return rol;
    }

    public String getSalt() {
        return salt.get();
    }

    public void setSalt(String salt) {
        this.salt.set(salt);
    }

    public SimpleStringProperty saltProperty() {
        return salt;
    }
}
