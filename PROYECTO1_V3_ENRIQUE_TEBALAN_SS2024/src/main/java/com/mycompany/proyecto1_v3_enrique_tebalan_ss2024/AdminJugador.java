/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024;
import java.util.Scanner;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.GuerrerosYPlanetas.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Herramientas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Planetas.*;
/**
 *
 * @author DELL
 */
public class AdminJugador {
    private String nombre;
    private AdminPlanetas planetaInicial;
    private AdminPlanetas[] planetas;
    private int numPlanetas;
    private AdminConstructor[] constructores;
    private int numConstructores;

    public AdminJugador(String nombre, AdminPlanetas planetaInicial) {
        this.nombre = nombre;
        this.planetaInicial = planetaInicial;
        this.planetas = new AdminPlanetas[10];  // El jugador puede tener un máximo de 10 planetas
        this.planetas[0] = planetaInicial;  // El planeta inicial se agrega automáticamente
        this.numPlanetas = 1;  // El jugador empieza con un planeta
        this.constructores = new AdminConstructor[10];
        this.numConstructores = 0;
    }

     public String getNombre() {
        return nombre;
    }

    public AdminPlanetas getPlanetaInicial() {
        return planetaInicial;
    }

    // Método para devolver el arreglo de planetas
    public AdminPlanetas[] getPlanetas() {
        return planetas;
    }

    // Método para agregar un nuevo planeta al jugador
    public void agregarPlaneta(AdminPlanetas planeta) {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        if (numPlanetas < planetas.length) {
            planetas[numPlanetas++] = planeta;
            System.out.println(c.verde("El planeta " + planeta.getNombre() + " ha sido conquistado por " + nombre));
            h.separadorLinea();
        } else {
            System.out.println(c.amarillo("No puedes controlar más de " + planetas.length + " planetas."));
        }
    }

  
    public void agregarConstructor(AdminConstructor constructor){
         Colores c = new Colores();
        Herramientas h = new Herramientas();
        if (numConstructores < constructores.length) {
            constructores[numConstructores++] = constructor;
            System.out.println(c.verde("Constructor " + constructor.getTipo() + " agregado."));
        } else {
            System.out.println(c.amarillo("No puedes tener más de " + constructores.length + " constructores."));
        }
    }

    public void vender(String tipoConstructor, int precioVenta) {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        for (int i = 0; i < numConstructores; i++) {
            if (constructores[i].getTipo().equals(tipoConstructor)) {
                // Vender el constructor, desplazando los elementos del arreglo
                for (int j = i; j < numConstructores - 1; j++) {
                    constructores[j] = constructores[j + 1];
                }
                constructores[--numConstructores] = null; // Elimina la referencia
                planetaInicial.aumentarMonedas(precioVenta); // Aumenta el dinero del jugador
                System.out.println(c.verde("Has vendido un " + tipoConstructor + " por " + precioVenta + " estelares."));
                return;
            }
        }
        System.out.println(c.rojo("No tienes ningún " + tipoConstructor + " para vender."));
    }

    public AdminConstructor[] getConstructores() {
        return constructores; // Devuelve el arreglo de constructores
    }
}
