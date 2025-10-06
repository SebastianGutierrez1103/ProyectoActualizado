/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquexpress;

/**
 *
 * @author 0
 */
public class RepCamion extends Repartidor {
    
      private String placa;

    public RepCamion(String placa, String cedula, String telefono, String nombre, double capacidadKg, Zona zona) {
        super(cedula, telefono, nombre, capacidadKg, zona);
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }
    
}
