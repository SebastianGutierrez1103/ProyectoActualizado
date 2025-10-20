package logica;

import dominio.Paquete;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorPaquetes implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Paquete> paquetes;

    public GestorPaquetes() {
        paquetes = new ArrayList<>();
    }

    public void agregarPaquete(Paquete p) {
        if (p != null) {
            paquetes.add(p);
            System.out.println("Paquete agregado con ID: " + p.getId());
        }
    }

    public void eliminarPaquete(int id) {
        Paquete paquete = buscarPaquete(id);
        if (paquete != null) {
            paquetes.remove(paquete);
            System.out.println("Paquete eliminado con ID: " + id);
        } else {
            System.out.println("Paquete con ID " + id + " no encontrado.");
        }
    }

    public Paquete buscarPaquete(int id) {
        for (Paquete p : paquetes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Paquete> listarPaquetes() {
        return paquetes;
    }
}
