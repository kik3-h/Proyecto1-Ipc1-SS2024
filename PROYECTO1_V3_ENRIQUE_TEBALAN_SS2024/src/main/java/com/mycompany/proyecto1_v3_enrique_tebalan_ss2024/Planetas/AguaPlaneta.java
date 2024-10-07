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
public class AguaPlaneta extends AdminPlanetas {
     public AguaPlaneta(String nombre, Posicion posicion, String dueño) {
        super(nombre, 0.9, 180, new Operador(), posicion, dueño);
    }

    // Producción de recursos específica para Planeta Agua

    public void producirRecursos() {
        aumentarMonedas(80);  // Produce menos monedas, pero tiene guerreros especializados
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Aquaris("Aquaris", 1.6, 1));
        }
    }
}
