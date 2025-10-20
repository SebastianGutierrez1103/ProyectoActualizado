package dominio;

import dominio.Zona;
import dominio.Envio;
import java.io.Serializable;
import java.util.Date;

public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Date fecha;
    private String tipo;
    private String contenido;
    private Envio envio;
    private Zona zona;

    public Reporte(int id, String tipo, String contenido, Envio envio, Zona zona) {
        this.id = id;
        this.fecha = new Date();
        this.tipo = tipo;
        this.contenido = contenido;
        this.envio = envio;
        this.zona = zona;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public Envio getEnvio() {
        return envio;
    }

    public Zona getZona() {
        return zona;
    }

    @Override
    public String toString() {
        return "Reporte ID: " + id
                + ", Tipo: " + tipo
                + ", Fecha: " + fecha
                + ", Contenido: " + contenido;
    }
}
