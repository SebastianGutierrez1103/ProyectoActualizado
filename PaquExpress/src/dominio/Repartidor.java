package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repartidor implements Serializable {
    protected String cedula;
    protected String nombre;
    protected String telefono;
    protected Zona zona;
    protected List<Envio> enviosAsignados;

    public Repartidor(String cedula, String nombre, String telefono, Zona zona) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.zona = zona;
        this.enviosAsignados = new ArrayList<>();
    }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public Zona getZona() { return zona; }

    public void asignarEnvio(Envio e) {
        enviosAsignados.add(e);
    }

    public List<Envio> listarEnvios() {
        return enviosAsignados;
    }

    public int getCantidadEnvios() {
        return enviosAsignados.size();
    }

    public void entregarPaquete(Paquete p) {
        for (Envio e : enviosAsignados) {
            if (e.getPaquetes().contains(p)) {
                e.setEstado("Entregado");
            }
        }
    }

    public double getCapacidadMaxima() {
        return 0; // Las subclases lo sobreescriben
    }

    @Override
    public String toString() {
        return "Repartidor{" + "cedula=" + cedula + ", nombre=" + nombre +
                ", zona=" + (zona != null ? zona.getNombre() : "N/A") +
                ", envíos=" + getCantidadEnvios() + '}';
    }
}
