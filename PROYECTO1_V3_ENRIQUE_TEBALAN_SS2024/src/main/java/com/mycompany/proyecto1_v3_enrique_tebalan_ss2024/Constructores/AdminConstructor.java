/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.AdminPlanetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.AdminNaves;
/**
 *
 * @author DELL
 */
public abstract class AdminConstructor {
    protected String tipo;
    protected int tiempoDeConstruccion;  // Tiempo para construir la nave
    protected int precioDeCompra;  // Precio para comprar el constructor
    protected int precioDeVenta;  // Precio al que se puede vender el constructor

    public AdminConstructor(String tipo, int tiempoDeConstruccion, int precioDeCompra, int precioDeVenta) {
        this.tipo = tipo;
        this.tiempoDeConstruccion = tiempoDeConstruccion;
        this.precioDeCompra = precioDeCompra;
        this.precioDeVenta = precioDeVenta;
    }
 public AdminConstructor(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    public int getTiempoDeConstruccion() {  // Corregido el nombre del método
        return tiempoDeConstruccion;
    }

    public int getPrecioDeCompra() {
        return precioDeCompra;
    }

    public int getPrecioDeVenta() {
        return precioDeVenta;
    }
}
