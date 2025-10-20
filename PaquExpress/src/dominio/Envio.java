package dominio;

import dominio.Paquete;
import dominio.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Envio implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private List<Paquete> paquetes;
    private Cliente cliente;
    private Repartidor repartidor;
    private Zona zona;
    private String estado;
    private Date fechaSalida;
    private Date fechaEntrega;

    public Envio(int id, List<Paquete> paquetes, Cliente cliente, Repartidor repartidor, Zona zona) {
        this.id = id;
        this.paquetes = new ArrayList<>(paquetes);
        this.cliente = cliente;
        this.repartidor = repartidor;
        this.zona = zona;
        this.estado = "Pendiente";
        this.fechaSalida = new Date();
        this.fechaEntrega = null;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void marcarEntregado() {
        this.estado = "Entregado";
        this.fechaEntrega = new Date();
    }
}
