package DAOs;

import Modelo.Persona;
import Persistencia.Serializadora;
import java.util.ArrayList;

public class DaoPersona {

    private final ArrayList<Persona> personas;
    private final Serializadora serializadoraPersona;

    public DaoPersona() {
        this.serializadoraPersona = new Serializadora();
        this.personas = serializadoraPersona.getListaPersonas();
    }

    public ArrayList<Persona> getPersona() {
        return personas;
    }

    public boolean registrarPersona(Persona persona) {
        if (persona != null && buscarPersona(persona.getDocumento()) == null) {
            personas.add(persona);
            serializadoraPersona.escribirPersona();
            return true;
        }
        return false;
    }

    public Persona buscarPersona(String documento) {
        for (Persona persona : personas) {
            if (persona.getDocumento().equals(documento)) {
                return persona;
            }
        }
        return null;
    }

    public boolean eliminarPersona(String documento) {
        Persona persona = buscarPersona(documento);
        if (persona != null) {
            personas.remove(persona);
            serializadoraPersona.escribirPersona();
            return true;
        }
        return false;
    }

}
