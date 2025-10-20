package dominio;

import dominio.Cliente;

public class Particular extends Cliente {

    private static final long serialVersionUID = 1L;

    public Particular(String cedula, String nombre, String direccion, Zona zona, String telefono) {
        super(cedula, nombre, direccion, zona, telefono);
    }

    @Override
    public String toString() {
        return "Cliente Particular - " + super.toString();
    }
}
