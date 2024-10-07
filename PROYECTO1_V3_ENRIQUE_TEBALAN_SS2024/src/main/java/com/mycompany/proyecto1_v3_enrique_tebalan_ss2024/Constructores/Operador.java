/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.AdminPlanetas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.Galaxiaprime;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.AdminNaves;
/**
 *
 * @author DELL
 */
public class Operador extends AdminConstructor {
    public Operador() {
        super("Operador", 2, 100, 70);  // Tarda 2 turnos en construir una nave Galaxia Prime
    }

    public void construirNave(AdminPlanetas planeta) {
        if (planeta.getMonedas() >= 200) {
            planeta.reducirMonedas(200);
            System.out.println("Operador ha construido una nave Galaxia Prime en el planeta " + planeta.getNombre());
        } else {
            System.out.println("No hay suficientes recursos para construir una nave en el planeta " + planeta.getNombre());
        }
    }
}
