package dominio;

import dominio.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private ArrayList<Repartidor> repartidores;
    private ArrayList<Cliente> clientes;

    public Zona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.repartidores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void asignarRepartidor(Repartidor r) {
        if (r != null && !repartidores.contains(r)) {
            repartidores.add(r);
            System.out.println("Repartidor " + r.getNombre() + " asignado a la zona " + nombre);
        }
    }

    public void asignarCliente(Cliente c) {
        if (c != null && !clientes.contains(c)) {
            clientes.add(c);
            System.out.println("Cliente " + c.getNombre() + " asignado a la zona " + nombre);
        }
    }

    public List<Repartidor> listarRepartidores() {
        return repartidores;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Map<Repartidor, Integer> getRepartidoresConCarga() {
        Map<Repartidor, Integer> carga = new HashMap<>();
        for (Repartidor r : repartidores) {
            carga.put(r, r.getCantidadEnvios());
        }
        return carga;
    }

    @Override
    public String toString() {
        return "Zona ID: " + id
                + " | Nombre: " + nombre
                + " | Repartidores: " + repartidores.size()
                + " | Clientes: " + clientes.size();
    }
}
