/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquexpress;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 0
 */
public abstract class Repartidor {
    
   private String cedula;
   private String telefono;
   private String nombre;
   private Zona zona;
   private double capacidadKg;
   private List<Envio> enviosAsignados;


    public Repartidor(String cedula, String telefono, String nombre,double capacidadKg, Zona zona) {
        this.cedula = cedula;
        this.telefono = telefono;
        this.nombre = nombre;
        this.zona = zona;
        this.capacidadKg = capacidadKg;
        this.enviosAsignados = new ArrayList<>();

    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }

    
    public Zona getZona() {
        return zona;
    }

    public List<Envio> getEnviosAsignados() {
        return enviosAsignados;
    }
    
    public void setZona(Zona zona) {
        this.zona = zona;
    }
    
    public int cantidadEnviosAsignados(){
        return enviosAsignados.size();    
    }
   
    public void asignarEnvio(Envio e){
        this.enviosAsignados.add(e);

    }
    
    public void envioEntregado(int idEnvio) {
        for (Envio e : enviosAsignados) {
            if (e.getId()== idEnvio && e.getEstado().equals("EN PROCESO")) {
                e.setEstado("ENTREGADO");
                e.setFechaEntrega(new java.util.Date());
                return;
            }
        }
    }
    
    public void envioFallido(int idEnvio) {
        for (Envio e : enviosAsignados) {
            if (e.getId()== idEnvio && e.getEstado().equals("EN PROCESO")) {
                e.setEstado("NO ENTREGADO");
                e.setFechaEntrega(new java.util.Date());
                return;
            }
        }
    } 
    
}
