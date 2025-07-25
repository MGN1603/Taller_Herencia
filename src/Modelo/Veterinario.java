package Modelo;

public class Veterinario extends Persona {

    private String especialidad;

    public Veterinario(String especialidad, String nombre, String documento) {
        super(nombre, documento);
        this.especialidad = especialidad;

    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String getTipo() {
        return "Veterinario";
    }

}
