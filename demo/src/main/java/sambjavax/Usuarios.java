package sambjavax;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Usuarios {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty nombre = new SimpleStringProperty();
    private final SimpleStringProperty direccion = new SimpleStringProperty();
    private final SimpleStringProperty ip_address = new SimpleStringProperty();

    public Usuarios(int id, String nombre, String direccion, String ip_address) {
        setId(id);
        setNombre(nombre);
        setDireccion(direccion);
        setIp_address(ip_address);
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

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public SimpleStringProperty direccionProperty() {
        return direccion;
    }

    public String getIp_address() {
        return ip_address.get();
    }

    public void setIp_address(String ip_address) {
        this.ip_address.set(ip_address);
    }

    public SimpleStringProperty ip_addressProperty() {
        return ip_address;
    }
}