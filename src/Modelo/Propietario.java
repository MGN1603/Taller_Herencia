package Modelo;

public class Propietario extends Persona {

    private String telefono;

    public Propietario(String nombre, String documento, String telefono) {
        super(nombre, documento);
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String getTipo() {
        return "Propietario";
    }
}
