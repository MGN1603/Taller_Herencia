package Controladores;

import DAOs.DaoPersona;
import Modelo.Persona;
import java.util.ArrayList;

public class ControladorPersona {

    private final DaoPersona daoPersona;

    public ControladorPersona() {
        this.daoPersona = new DaoPersona();
    }

    public boolean guardarPersona(Persona persona) {
        return daoPersona.registrarPersona(persona);
    }

    public Persona buscarPersona(String documento) {
        return daoPersona.buscarPersona(documento);
    }

    public boolean eliminarPersona(String documento) {
        return daoPersona.eliminarPersona(documento);
    }

    public static boolean validarPersona(String nombre, String documento) {
        return nombre != null && !nombre.isEmpty() && documento != null && documento.matches("\\d+");
    }
    
    public ArrayList<Persona> listar(){
        return daoPersona.getPersona();
    }
}
