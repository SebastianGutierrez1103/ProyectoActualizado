package logica;

import dominio.Repartidor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorRepartidores implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Repartidor> repartidores;

    public GestorRepartidores() {
        this.repartidores = new ArrayList<>();
    }

    public void agregarRepartidor(Repartidor r) {
        if (r != null) {
            repartidores.add(r);
        }
    }

    public void eliminarRepartidor(String cedula) {
        repartidores.removeIf(r -> r.getCedula().equals(cedula));
    }

    public Repartidor buscarRepartidor(String cedula) {
        for (Repartidor r : repartidores) {
            if (r.getCedula().equals(cedula)) {
                return r;
            }
        }
        return null;
    }

    public List<Repartidor> listarRepartidores() {
        return new ArrayList<>(repartidores);
    }
}
