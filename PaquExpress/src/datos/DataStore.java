package datos;

import java.io.Serializable;
import java.util.ArrayList;
import logica.GestorClientes;
import logica.GestorEnvios;
import logica.GestorPaquetes;
import logica.GestorRepartidores;
import logica.GestorReportes;
import dominio.Zona;

public class DataStore implements Serializable {

    private static final long serialVersionUID = 1L;

    public GestorEnvios gestorEnvios;
    public GestorReportes gestorReportes;
    public GestorRepartidores gestorRepartidores;
    public GestorClientes gestorClientes;
    public GestorPaquetes gestorPaquetes;

    public ArrayList<Zona> zonas;

    public DataStore() {
    }

    public DataStore(GestorEnvios ge, GestorReportes gr, GestorRepartidores gR,
            GestorClientes gc, GestorPaquetes gp) {
        this.gestorEnvios = ge;
        this.gestorReportes = gr;
        this.gestorRepartidores = gR;
        this.gestorClientes = gc;
        this.gestorPaquetes = gp;
    }
}
