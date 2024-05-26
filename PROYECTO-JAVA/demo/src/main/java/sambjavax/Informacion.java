package sambjavax;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Informacion {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty bastidor = new SimpleStringProperty();
    private final SimpleStringProperty marca = new SimpleStringProperty();
    private final SimpleStringProperty matricula = new SimpleStringProperty();
    private final SimpleStringProperty modelo = new SimpleStringProperty();

    public Informacion(int id, String bastidor, String marca, String matricula, String modelo) {
        setId(id);
        setBastidor(bastidor);
        setMarca(marca);
        setMatricula(matricula);
        setModelo(modelo);
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

    public String getBastidor() {
        return bastidor.get();
    }

    public void setBastidor(String nombre) {
        this.bastidor.set(nombre);
    }

    public SimpleStringProperty bastidorProperty() {
        return bastidor;
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public SimpleStringProperty modeloProperty() {
        return modelo;
    }

    public String getMatricula() {
        return matricula.get();
    }

    public void setMatricula(String matricula) {
        this.matricula.set(matricula);
    }

    public SimpleStringProperty matriculaProperty() {
        return matricula;
    }
}
