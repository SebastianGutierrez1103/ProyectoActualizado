package datos;

import java.io.*;
import java.util.ArrayList;
import logica.GestorClientes;
import logica.GestorEnvios;
import logica.GestorPaquetes;
import logica.GestorRepartidores;
import logica.GestorReportes;

public class ArchivoManager {

    private static final String DEFAULT_FILENAME = "paquexpress.dat";

    private String filename;

    public ArchivoManager() {
        this.filename = DEFAULT_FILENAME;
    }

    public ArchivoManager(String filename) {
        this.filename = filename;
    }  
    

    public void guardarDatos(GestorEnvios ge, GestorReportes gr,
            GestorRepartidores gR, GestorClientes gc,
            GestorPaquetes gp) {
        DataStore store = new DataStore(ge, gr, gR, gc, gp);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(store);
            System.out.println("Datos guardados correctamente en: " + filename);
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DataStore cargarDatos() {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("Archivo de datos no encontrado (" + filename + "). Se devolvera null.");
            return null;
        }  
        

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            if (obj instanceof DataStore) {
                System.out.println("Datos cargados correctamente desde: " + filename);
                return (DataStore) obj;
            } else {
                System.err.println("Contenido del archivo no corresponde a un DataStore.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void guardarDatos() {
        System.out.println("Uso: guardarDatos(gestorEnvios, gestorReportes, gestorRepartidores, gestorClientes, gestorPaquetes)");
    }

    public void cargarDatosVoid() {
        System.out.println("Uso: DataStore ds = cargarDatos(); luego extrae los gestores.");
    }
    
}
