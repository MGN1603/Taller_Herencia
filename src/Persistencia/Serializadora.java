package Persistencia;

import Modelo.Persona;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializadora {

    private ArrayList<Persona> listaPersona;

    public Serializadora() {
        this.listaPersona = leerListaPersona();
    }

    public ArrayList<Persona> getListaPersonas() {
        return listaPersona;
    }

    public void escribirPersona() {
        try {
            FileOutputStream archivo = new FileOutputStream("personas.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(listaPersona);
            escritor.close();
        } catch (IOException ex) {

        }
    }

    public ArrayList<Persona> leerListaPersona() {

        try {
            FileInputStream archivo = new FileInputStream("personas.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            ArrayList<Persona> lista = (ArrayList<Persona>) lector.readObject();
            lector.close();
            return lista;
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }
}

