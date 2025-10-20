package dominio;

/*import dominio.Vehiculo;

public class Moto extends Vehiculo {

    private static final long serialVersionUID = 1L;

    public Moto(String placa, double capacidad) {
        super(placa, capacidad);
    }

    @Override
    public String toString() {
        return "Moto - " + super.toString();
    }
}*/
public class Moto extends Repartidor {
    private double capacidad;

    public Moto(String cedula, String nombre, String telefono, Zona zona, double capacidad) {
        super(cedula, nombre, telefono, zona);
        this.capacidad = capacidad;
    }

    @Override
    public double getCapacidadMaxima() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Repartidor en Moto{" +
                "nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad + "kg" +
                ", zona=" + (zona != null ? zona.getNombre() : "N/A") +
                '}';
    }
}