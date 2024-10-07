/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.GuerrerosYPlanetas.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.AMARILLO;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.AZUL;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.RESET;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Herramientas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Planetas.*;
import java.util.Scanner;
/**
 *
 * @author DELL
 */
public class AdminTienda {
     private static final int ValorObrero = 50;
    private static final int ValorOperador = 100;
    private static final int ValorTecnico = 250;
    private static final int ValorIngeniero = 300;

    private Scanner scanner;
Colores c = new Colores();
    Herramientas h = new Herramientas();
    public AdminTienda() {
        scanner = new Scanner(System.in);
        
    }

    public void mostrarMenu(AdminJugador jugador) {
        System.out.println(AMARILLO+"    TIENDA"+RESET);
        System.out.println("1. Comprar");
        System.out.println("2. Vender");
        System.out.println("3. Salir de la tienda");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        switch (opcion) {
            case 1:
                comprar(jugador);
                break;
            case 2:
                vender(jugador);
                break;
            case 3:
                System.out.println("Saliendo de la tienda.");
                break;
            default:
                System.out.println("Opción no válida. Intenta de nuevo.");
                mostrarMenu(jugador);
                break;
        }
    }

    private void comprar(AdminJugador jugador) {
        h.separadorLinea();
        System.out.println(AMARILLO+"Seleccione el tipo de constructor a comprar:");
        System.out.println("1. Obrero - Precio: " + ValorObrero + " estelares");
        System.out.println("2. Operador - Precio: " + ValorOperador + " estelares");
        System.out.println("3. Técnico - Precio: " + ValorTecnico + " estelares");
        System.out.println("4. Ingeniero - Precio: " + ValorIngeniero + " estelares"+RESET);

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        switch (opcion) {
            case 1:
                procesarCompra(jugador, new Obrero(), ValorObrero);
                break;
            case 2:
                procesarCompra(jugador, new Operador(), ValorOperador);
                break;
            case 3:
                procesarCompra(jugador, new Tecnico(), ValorTecnico);
                break;
            case 4:
                procesarCompra(jugador, new Ingeniero(), ValorIngeniero);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void procesarCompra(AdminJugador jugador, AdminConstructor constructor, int precio) {
        h.separadorLinea();
        if (jugador.getPlanetaInicial().getMonedas() >= precio) {
            jugador.getPlanetaInicial().reducirMonedas(precio);
            jugador.agregarConstructor(constructor);  // Agregar el constructor al jugador
            System.out.println("Has comprado un " + constructor.getTipo() + ".");
        } else {
            System.out.println("No tienes suficiente dinero.");
        }
    }

    private void vender(AdminJugador jugador) {
        h.separadorLinea();
        System.out.println(AMARILLO+"Seleccione qué desea vender:");
        // Aquí puedes incluir la lógica para mostrar lo que el jugador tiene y los precios de venta
        System.out.println("1. Obrero - Precio de venta: 0 estelares");
        System.out.println("2. Operador - Precio de venta: 70 estelares");
        System.out.println("3. Técnico - Precio de venta: 175 estelares");
        System.out.println("4. Ingeniero - Precio de venta: 200 estelares"+RESET);
        // Para vender naves, puedes agregar otra opción aquí

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        switch (opcion) {
            case 1:
                jugador.vender("Obrero", 0);  // Lógica de venta para el Obrero
                break;
            case 2:
                jugador.vender("Operador", 70);
                break;
            case 3:
                jugador.vender("Técnico", 175);
                break;
            case 4:
                jugador.vender("Ingeniero", 200);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}
