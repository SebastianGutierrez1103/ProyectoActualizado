package dominio;

import dominio.Cliente;

public class Empresa extends Cliente {

    private static final long serialVersionUID = 1L;

    public Empresa(String cedula, String nombre, String direccion, Zona zona, String telefono) {
        super(cedula, nombre, direccion, zona, telefono);
    }

    @Override
    public String toString() {
        return "Cliente Empresa - " + super.toString();
    }
}
