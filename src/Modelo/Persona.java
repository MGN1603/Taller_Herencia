package Modelo;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    protected String nombre;
    protected String documento;

    public Persona(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    // Método abstracto para personalizar en subclases
    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " - " + nombre + " (" + documento + ")";
    }

}
