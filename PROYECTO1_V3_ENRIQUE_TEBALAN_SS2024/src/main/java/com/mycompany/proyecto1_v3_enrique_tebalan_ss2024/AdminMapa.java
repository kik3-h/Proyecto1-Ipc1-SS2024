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
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.CELESTE;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.RESET;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.ROJO;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.VERDE;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Herramientas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Planetas.*;
/**
 *
 * @author DELL
 */
public class AdminMapa {
    Colores c = new Colores();
    Herramientas h = new Herramientas();
    private AdminPlanetas[] planetas;  // Arreglo de planetas en el mapa
    private int filas;
    private int columnas;
    private int numPlanetas;  // Contador de planetas

    public AdminMapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.planetas = new AdminPlanetas[60];  // Limitar a 60 planetas en el mapa
        this.numPlanetas = 0;
    }

    // Método para colocar un planeta en el mapa
    public void colocarPlaneta(AdminPlanetas planeta) {
        if (numPlanetas >= planetas.length) {
            System.out.println(c.rojo("No se pueden colocar más planetas."));
            return;
        }
        Posicion pos = planeta.getPosicion();
        if (obtenerPlaneta(pos) == null) {
            planetas[numPlanetas++] = planeta;  // Añadir el planeta y aumentar el contador
            h.separadorLinea();
            System.out.println(c.cian(          "Planeta " + planeta.getNombre() + " colocado en (" + pos.getX() + ", " + pos.getY() + ")"));
        } else {
            System.out.println(c.amarillo(          "La posición (" + pos.getX() + ", " + pos.getY() + ") ya está ocupada."));
        }
    }

    // Obtener un planeta por su posición
    public AdminPlanetas obtenerPlaneta(Posicion posicion) {
        for (int i = 0; i < numPlanetas; i++) {
            if (planetas[i].getPosicion().getX() == posicion.getX() && planetas[i].getPosicion().getY() == posicion.getY()) {
                return planetas[i];
            }
        }
        return null;
    }

    // Método para obtener el número total de planetas en el mapa
    public int getNumPlanetas() {
        return numPlanetas;
    }

    // Método para obtener un planeta por índice
    public AdminPlanetas getPlaneta(int index) {
        if (index >= 0 && index < numPlanetas) {
            return planetas[index];
        }
        return null;
    }

   public int calcularDistancia(AdminPlanetas p1, AdminPlanetas p2, AdminNaves nave) {
    if (p1 == null || p2 == null) {
        System.out.println(c.amarillo("Uno de los planetas es nulo."));
        return 0;
    }
    Posicion pos1 = p1.getPosicion();
    Posicion pos2 = p2.getPosicion();
    
    // Calculamos la distancia en el plano 2D como la distancia  entre dos puntos
    int distancia = (int) Math.sqrt(Math.pow(pos2.getX() - pos1.getX(), 2) + Math.pow(pos2.getY() - pos1.getY(), 2));
    
    // Calculamos los turnos en base a la velocidad de la nave
    return (int) Math.ceil((double) distancia / nave.getVelocidad());
}


    // Mostrar el mapa visualmente con más detalles
  public void mostrarMapa() {
    String[][] tablero = new String[filas][columnas];

    // Inicializar el tablero vacío
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            tablero[i][j] = "|   |";  // Celda vacía
        }
    }

    // Colocar los planetas en el tablero
    for (int i = 0; i < numPlanetas; i++) {
        AdminPlanetas planeta = planetas[i];
        int x = planeta.getPosicion().getX();
        int y = planeta.getPosicion().getY();

        String dueño = planeta.getDueño().equals("Neutral") ? "N" : planeta.getDueño().substring(0, 1);
        tablero[x][y] = VERDE+"|" + planeta.getNombre().charAt(0) + "-" + dueño + "|"+RESET;
    }
                              
    // Mostrar el mapa con números de f     ila y columna
    h.dobleLineaSeparadora();
    System.out.println(c.morado("                    MAPA KONQUEST"));
    
    // Mostrar los números de columna en la parte superior
    System.out.print("   ");  // Espacio para el índice de fila
    for (int j = 0; j <columnas; j++) {
        System.out.print("  " + VERDE+ j +RESET+ "  ");
    }
    System.out.println();
    
    // Mostrar el tablero con números de fila en el lateral
    for (int i = 0; i < filas; i++) {
        System.out.print(VERDE+i + "  "+RESET);  // Número de fila al inicio de cada línea
        for (int j = 0; j < columnas; j++) {
            System.out.print(tablero[i][j] + " ");
        }
        System.out.println();
    }
    h.dobleLineaSeparadora();
}


    // Obtener el número de filas del mapa
    public int getFilas() {
        return filas;
    }

    // Obtener el número de columnas del mapa
    public int getColumnas() {
        return columnas;
    }
}
