package logica;

import dominio.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Cliente> clientes;

    public GestorClientes() {
        clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente c) {
        if (c != null) {
            clientes.add(c);
            System.out.println("Cliente agregado: " + c.getNombre());
        }
    }

    public void eliminarCliente(int id) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente eliminado: " + cliente.getNombre());
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }

    public Cliente buscarCliente(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
}
