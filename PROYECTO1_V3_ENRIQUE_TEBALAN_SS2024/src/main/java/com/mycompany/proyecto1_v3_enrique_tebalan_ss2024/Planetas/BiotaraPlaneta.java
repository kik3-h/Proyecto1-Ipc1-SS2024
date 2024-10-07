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
public class BiotaraPlaneta extends AdminPlanetas{
    public BiotaraPlaneta(String nombre, Posicion posicion, String dueño) {
        super(nombre, 0.85, 150, new Tecnico(), posicion, dueño);
    }

    // Producción de recursos específica para Planeta Biotara
    @Override
    public void producirRecursos() {
        aumentarMonedas(90);  // Producen recursos a un ritmo moderado
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Rooters("Rooters", 1.85, 3));
        }
    }
}
