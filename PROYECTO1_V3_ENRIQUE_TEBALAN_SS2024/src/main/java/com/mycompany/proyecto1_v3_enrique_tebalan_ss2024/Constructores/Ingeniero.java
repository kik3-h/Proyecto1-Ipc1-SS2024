/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.AdminPlanetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.Odisea;
/**
 *
 * @author DELL
 */
public class Ingeniero extends AdminConstructor {
     public Ingeniero() {
        super("Ingeniero", 1, 300, 200);  // Tarda 1 turno en construir una nave Odisea
    }


    public void construirNave(AdminPlanetas planeta) {
        if (planeta.getMonedas() >= 700) {  // Ejemplo: nave Odisea cuesta 700 monedas
            planeta.reducirMonedas(700);
            System.out.println("El ingeniero ha comenzado a construir una nave Odisea en el planeta " + planeta.getNombre());
            planeta.colocarNave(new Odisea()); 
            System.out.println("El ingeniero ha construido una nave Odisea en el planeta " + planeta.getNombre());
        } else {
            System.out.println("El planeta " + planeta.getNombre() + " no tiene suficientes recursos para construir una nave Odisea.");
        }
    }
}
