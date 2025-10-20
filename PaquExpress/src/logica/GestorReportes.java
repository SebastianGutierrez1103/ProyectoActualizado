package logica;

import dominio.Reporte;
import dominio.Zona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorReportes implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Reporte> historialReportes;

    public GestorReportes() {
        this.historialReportes = new ArrayList<>();
    }

    public String generarReporteRepartidor(int id) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("Reporte repartidor id=").append(id).append("\n");
        return sb.toString();
    }

    public String generarReporteGeneral() {
        return "Reporte general (stub)";
    }

    public String generarReporteZona(Zona z) {
        return "Reporte zona: " + (z != null ? z.getNombre() : "null");
    }

    public void guardarReporte(Reporte r) {
        if (r != null) {
            historialReportes.add(r);
        }
    }

    public List<String> listarReportes() {
        List<String> salida = new ArrayList<>();
        for (Reporte r : historialReportes) {
            salida.add(r.getTipo() + " - " + r.getFecha());
        }
        return salida;
    }
}
