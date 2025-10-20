package dominio;

import java.io.Serializable;

public class Paquete implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private double peso;
    private double dimensiones;
    private String destino;

    public Paquete(int id, double peso, double dimensiones, String destino) {
        this.id = id;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public double getDimensiones() {
        return dimensiones;
    }

    public String getDestino() {
        return destino;
    }

    public void setPeso(double p) {
        this.peso = p;
    }

    public void setDestino(String d) {
        this.destino = d;
    }

    @Override
    public String toString() {
        return "Paquete ID: " + id
                + " | Peso: " + peso + "kg"
                + " | Dimensiones: " + dimensiones + "cm³"
                + " | Destino: " + destino;
    }
}
