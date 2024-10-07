/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Planetas;

import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.AdminPlanetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.AdminPlanetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Posicion;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.GuerrerosYPlanetas.*;
/**
 *
 * @author DELL
 */
public class TierraPlaneta extends AdminPlanetas{
    public TierraPlaneta(String nombre, Posicion posicion, String dueño) {
        super(nombre, 0.8, 200, new Ingeniero(), posicion, dueño);
    }

   // Producción de recursos específica para Planeta Tierra
    @Override
    public void producirRecursos() {
        aumentarMonedas(100);  // El Planeta Tierra produce más monedas por turno
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Terradiente("Terradiente", 1.5, 1));
        }
    }
}
