/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Planetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.AdminPlanetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Posicion;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.GuerrerosYPlanetas.*;
/**
 *
 * @author DELL
 */
public class FuegoPlaneta extends AdminPlanetas {
    public FuegoPlaneta(String nombre, Posicion posicion, String dueño) {
        super(nombre, 0.7, 220, new Obrero(), posicion, dueño);
    }

    // Producción de recursos específica para Planeta Fuego
   
    public void producirRecursos() {
        aumentarMonedas(120);  // Produce más monedas y guerreros ofensivos
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Ignis("Ignis", 1.75, 2));
        }
    }
}
