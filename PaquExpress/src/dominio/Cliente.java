package dominio;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String cedula;
    protected String nombre;
    protected String direccion;
    protected Zona zona;
    protected String telefono;

    public Cliente(String cedula, String nombre, String direccion, Zona zona, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.zona = zona;
        this.telefono = telefono;
    }

    public int getId() {
        try {
            return Integer.parseInt(cedula);
        } catch (NumberFormatException e) {
            return cedula.hashCode();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public void setDireccion(String d) {
        this.direccion = d;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " | Cedula: " + cedula
                + " | Zona: " + (zona != null ? zona.getNombre() : "No asignada");
    }
}
