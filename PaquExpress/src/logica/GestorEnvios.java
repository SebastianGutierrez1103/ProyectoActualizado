package logica;

import dominio.Repartidor;
import dominio.Zona;
import dominio.Envio;
import dominio.Paquete;
import dominio.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorEnvios implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Envio> envios;
    private int siguienteId = 1;

    public GestorEnvios() {
        this.envios = new ArrayList<>();
    }

    public Envio crearEnvio(List<Paquete> paquetes, Cliente cliente, Zona zona) {
        Envio e = new Envio(siguienteId++, paquetes, cliente, null, zona);
        envios.add(e);
        return e;
    }

    public void asignarRepartidor(Envio envio) {
        if (envio == null || envio.getZona() == null) {
            return;
        }
        Repartidor r = balancearEnvios(envio.getZona());
        if (r != null) {
            envio.setRepartidor(r);
            r.asignarEnvio(envio);
        }
    }

    public Repartidor balancearEnvios(Zona zona) {
        if (zona == null) {
            return null;
        }

        Repartidor mejor = null;
        for (Repartidor r : zona.listarRepartidores()) {
            if (mejor == null || r.getCantidadEnvios() < mejor.getCantidadEnvios()) {
                mejor = r;
            }
        }
        return mejor;
    }

    public List<Envio> listaEnvios() {
        return new ArrayList<>(envios);
    }

    public Envio buscarEnvio(int id) {
        for (Envio e : envios) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public boolean verificarPaqueteDeRepartidor(Repartidor r, Paquete p) {
        for (Envio e : envios) {
            if (e.getRepartidor() != null && e.getRepartidor().equals(r)) {
                if (e.getPaquetes().contains(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void actualizarEstadoEnvio(int id, String estado) {
        Envio e = buscarEnvio(id);
        if (e != null) {
            e.setEstado(estado);
        }
    }
}
