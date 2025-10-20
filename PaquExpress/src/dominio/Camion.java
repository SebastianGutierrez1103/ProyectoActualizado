package dominio;

/*import dominio.Vehiculo;

public class Camion extends Vehiculo {

    private static final long serialVersionUID = 1L;

    public Camion(String placa, double capacidad) {
        super(placa, capacidad);
    }

    @Override
    public String toString() {
        return "Camion - " + super.toString();
    }
}*/
public class Camion extends Repartidor {
    private double capacidad;

    public Camion(String cedula, String nombre, String telefono, Zona zona, double capacidad) {
        super(cedula, nombre, telefono, zona);
        this.capacidad = capacidad;
    }

    @Override
    public double getCapacidadMaxima() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Repartidor en Camion{" +
                "nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad + "kg" +
                ", zona=" + (zona != null ? zona.getNombre() : "N/A") +
                '}';
    }
}