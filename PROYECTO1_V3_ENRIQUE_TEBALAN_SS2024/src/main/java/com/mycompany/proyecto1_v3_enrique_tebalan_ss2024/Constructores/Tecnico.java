/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.AdminPlanetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.Nebulon;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.AdminNaves;
/**
 *
 * @author DELL
 */
public class Tecnico extends AdminConstructor {
     public Tecnico() {
        super("Técnico", 1, 250, 175);  // Tarda 1 turno en construir una nave Nebulon
    }

 
    public void construirNave(AdminPlanetas planeta) {
        if (planeta.getMonedas() >= 500) {  // Ejemplo: nave Nebulon cuesta 500 monedas
            planeta.reducirMonedas(500);
            System.out.println("El técnico ha comenzado a construir una nave Nebulon en el planeta " + planeta.getNombre());
            planeta.colocarNave(new Nebulon()); 
            System.out.println("El técnico ha construido una nave Nebulon en el planeta " + planeta.getNombre());
        } else {
            System.out.println("El planeta " + planeta.getNombre() + " no tiene suficientes recursos para construir una nave Nebulon.");
        }
    }
}
