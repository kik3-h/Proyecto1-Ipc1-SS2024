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
public class AdminFlota {
     private int cantidadGuerreros;
    private AdminNaves nave;
    private int turnoSalida;
    private int turnoLlegada;
    private AdminPlanetas destino;
    private AdminPlanetas origen;  // Se añade origen
    private AdminJugador jugador;  // Se añade referencia al jugador

    public AdminFlota(int cantidadGuerreros, AdminNaves nave, int turnoSalida, AdminPlanetas origen, AdminPlanetas destino, int turnosParaLlegada, AdminJugador jugador) {
        this.cantidadGuerreros = cantidadGuerreros;
        this.nave = nave;
        this.turnoSalida = turnoSalida;
        this.turnoLlegada = turnoSalida + turnosParaLlegada;
        this.origen = origen;
        this.destino = destino;
        this.jugador = jugador;
    }

    public int getTurnoLlegada() {
        return turnoLlegada;
    }

    public AdminPlanetas getDestino() {
        return destino;
    }

    public AdminPlanetas getOrigen() {
        return origen;
    }

    public AdminJugador getJugador() {
        return jugador;
    }

    public AdminsGuerreros[] getGuerreros() {
        // lógica para gestionar la tripulación.
        return new AdminsGuerreros[cantidadGuerreros];  // Lógica simplificada
    }

    public void mostrarFlota() {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        h.separadorLinea();
        System.out.println(c.azul("Flota con " + cantidadGuerreros + " guerreros en la nave " + nave.getNombre()));
        System.out.println(c.verde("Turno de salida: " + turnoSalida));
        System.out.println(c.rojo("Turno de llegada: " + turnoLlegada + " al planeta " + destino.getNombre()));
    }
    
    
    public boolean batalla(AdminsGuerreros[] guerrerosAtacantes) {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        h.separadorLinea();
        System.out.println(c.amarillo("Enviando flota con la nave " + nave.getNombre() + " y " + cantidadGuerreros + " guerreros."));
        
        if (destino.getDueño().equals("Neutral") || !destino.getDueño().equals(jugador.getNombre())) {
            // Si es un planeta neutral o enemigo, inicia la batalla
            boolean conquista = destino.batalla(guerrerosAtacantes, origen);
            if (conquista) {
                System.out.println(c.amarillo("El planeta " + destino.getNombre() + " ha sido conquistado por " + jugador.getNombre() + "!"));
                destino.setDueño(jugador.getNombre());
                return true; // Retorna verdadero si la conquista fue exitosa
            } else {
                System.out.println(c.verde("Los defensores han repelido el ataque en " + destino.getNombre() + "."));
                return false; // Retorna falso si no se conquista
            }
        } else {
            // Si es un planeta aliado, simplemente se refuerzan los guerreros
            destino.reforzarGuerreros(guerrerosAtacantes);
            System.out.println(c.verde("Los guerreros han sido enviados para reforzar el planeta " + destino.getNombre() + "."));
            return false; // Retorna falso, no hay conquista
        }
    }
}
