/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves;

import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.CELESTE;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.RESET;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Herramientas;

/**
 *
 * @author DELL
 */
public class AdminNaves {
    protected String tipo;
    protected int capacidad;
    protected double velocidad; // Velocidad de la nave
    protected double tasaDeSupervivenciaBase;
    protected double factorDeDistancia;
    protected double factorDeRecursos;

    public AdminNaves(String tipo, int capacidad, double tasaDeSupervivenciaBase, double factorDeDistancia, double factorDeRecursos, double velocidad) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.tasaDeSupervivenciaBase = tasaDeSupervivenciaBase;
        this.factorDeDistancia = factorDeDistancia;
        this.factorDeRecursos = factorDeRecursos;
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public double getTasaDeSupervivenciaBase() {
        return tasaDeSupervivenciaBase;
    }

    public double getFactorDeDistancia() {
        return factorDeDistancia;
    }

    public double getFactorDeRecursos() {
        return factorDeRecursos;
    }

   // Método para calcular la tasa de supervivencia
    public double calculoDeTasaDeSupervivencia(double distancia, int estelaresInvertidos) {
        // Tasa de supervivencia inicial basada en la distancia
        double tasaBase = Math.max(100 - distancia * 10, 10);  // Ejemplo: cada 1 año luz reduce 10% la tasa, mínimo 10%

        // Aumenta la tasa con los estelares invertidos
        double tasaSupervivencia = tasaBase + (estelaresInvertidos / 10.0);  // Ejemplo: Cada 10 estelares aumenta 1%

        // Asegurarse de que la tasa no exceda el 100%
        return Math.min(tasaSupervivencia, 100);
    }
    // Método para mostrar la información de la nave

    public void mostrarInfo() {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        h.separadorLinea();
        System.out.println(CELESTE+"Tipo de Nave: " + tipo);
        System.out.println("Capacidad: " + capacidad);
        System.out.println("Tasa de Supervivencia Base: " + tasaDeSupervivenciaBase);
        System.out.println("Factor de Distancia: " + factorDeDistancia);
        System.out.println("Factor de Recursos: " + factorDeRecursos+RESET);
    }
}
