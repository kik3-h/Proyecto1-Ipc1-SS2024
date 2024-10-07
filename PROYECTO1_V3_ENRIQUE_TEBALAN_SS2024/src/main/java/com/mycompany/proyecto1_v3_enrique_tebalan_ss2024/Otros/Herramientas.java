/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Herramientas {
    Colores color = new Colores();
    
    public void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public void borrarLinea() {
        System.out.print("\033[F\033[2K");
    }
     
    public void enterParaContinuar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(color.amarillo("Presione enter para continuar"));
        scanner.nextLine();
    }
     
    public void separadorLinea() {
        System.out.println(color.cian("-------------------------------------------------------------------------------------------"));
    }
    
    public void saltoDeLinea() {
        System.out.println("\n");
    } 
    
    public void mensajeBienvenida()
    {
        limpiarPantalla();
        dobleLineaSeparadora();
        System.out.println(color.verde("                                ¡BIENVENID@! :D         "));
        saltoDeLinea(); 
        System.out.println(color.morado("                               1er. Proyecto 1 SS2024         "));
        saltoDeLinea(); 
        System.out.println(color.amarillo("                                  by: kik3.h         "));
        separadorLinea();
        
    }
     public void dobleLineaSeparadora()
    {
        System.out.println(color.cian("==========================================================================================="));
    }
     
     public void mensajeDespedida()
     {
         dobleLineaSeparadora();
         System.out.println(color.rojo("HAZ DECIDIDO SALIR, HASTA LUEGO :D"));
         System.exit(0);//segun esto cierra todo jsjs
     }
     public void menuOpciones()
     {
         System.out.println(color.cian("                1. CREAR Y DISEÑAR MAPA "));
         System.out.println(color.cian("                2. INICIAR PARTIDA (MAPA ALEATORIO)"));
         System.out.println(color.cian("                3. SALIR"));
     }
}
