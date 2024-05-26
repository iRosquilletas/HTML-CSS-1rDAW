package sambjavax;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class MotocicletaVentas {
    private final SimpleIntegerProperty id_motoA = new SimpleIntegerProperty();
    private final SimpleStringProperty marca = new SimpleStringProperty();
    private final SimpleStringProperty modelo = new SimpleStringProperty();
    private final SimpleStringProperty matricula = new SimpleStringProperty();
    private final SimpleStringProperty bastidor = new SimpleStringProperty();

    public MotocicletaVentas(int id_motoA, String marca, String modelo, String matricula, String bastidor) {
        setId_motoA(id_motoA);
        setMarca(marca);
        setModelo(modelo);
        setMatricula(matricula);
        setBastidor(bastidor);
    }

    public int getId_motoA() {
        return id_motoA.get();
    }

    public void setId_motoA(int id_motoA) {
        this.id_motoA.set(id_motoA);
    }

    public SimpleIntegerProperty idProperty() {
        return id_motoA;
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

    public String getBastidor() {
        return bastidor.get();
    }

    public void setBastidor(String bastidor) {
        this.bastidor.set(bastidor);
    }

    public SimpleStringProperty bastidorProperty() {
        return bastidor;
    }
}