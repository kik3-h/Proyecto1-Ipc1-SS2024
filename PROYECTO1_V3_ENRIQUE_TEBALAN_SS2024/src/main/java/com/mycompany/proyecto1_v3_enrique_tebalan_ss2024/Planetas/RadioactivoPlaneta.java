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
public class RadioactivoPlaneta extends AdminPlanetas {
    public RadioactivoPlaneta(String nombre, Posicion posicion, String dueño) {
        super(nombre, 0.95, 130, new Ingeniero(), posicion, dueño);
    }

    // Producción de recursos específica para Planeta Radioactivo
    @Override
    public void producirRecursos() {
        aumentarMonedas(70);  // Produce menos monedas, pero guerreros más fuertes
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Rdonix("Rdonix", 1.90, 3));
        }
    }
}
