package sambjavax;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ventas {
    private final SimpleIntegerProperty idVenta = new SimpleIntegerProperty();
    private final SimpleIntegerProperty idUsuario = new SimpleIntegerProperty();
    private final SimpleStringProperty cliente = new SimpleStringProperty();
    private final SimpleStringProperty correo = new SimpleStringProperty();
    private final SimpleStringProperty telefono = new SimpleStringProperty();
    private final SimpleStringProperty direccion = new SimpleStringProperty();
    private final SimpleStringProperty dni = new SimpleStringProperty();  
    private final SimpleIntegerProperty idMoto = new SimpleIntegerProperty();

    public Ventas(int idVenta, int idUsuario, String cliente, String correo, String telefono, String direccion, String dni, int idMoto) {
        setIdVenta(idVenta);
        setIdUsuario(idUsuario);
        setCliente(cliente);
        setCorreo(correo);
        setTelefono(telefono);
        setDireccion(direccion);
        setDni(dni);  
        setIdMoto(idMoto);
    }

    public int getIdVenta() {
        return idVenta.get();
    }

    public void setIdVenta(int idVenta) {
        this.idVenta.set(idVenta);
    }

    public SimpleIntegerProperty idVentaProperty() {
        return idVenta;
    }

    public int getIdUsuario() {
        return idUsuario.get();
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario.set(idUsuario);
    }

    public SimpleIntegerProperty idUsuarioProperty() {
        return idUsuario;
    }

    public String getCliente() {
        return cliente.get();
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public SimpleStringProperty clienteProperty() {
        return cliente;
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public SimpleStringProperty correoProperty() {
        return correo;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public SimpleStringProperty telefonoProperty() {
        return telefono;
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

    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public SimpleStringProperty dniProperty() {
        return dni;
    }

    public int getIdMoto() {
        return idMoto.get();
    }

    public void setIdMoto(int idMoto) {
        this.idMoto.set(idMoto);
    }

    public SimpleIntegerProperty idMotoProperty() {
        return idMoto;
    }
}
