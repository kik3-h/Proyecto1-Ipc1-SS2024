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
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.AMARILLO;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.AZUL;
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
public class MotorDeJuego {
     private AdminJugador jugador1;
    private AdminJugador jugador2;
    private AdminMapa mapa;
    private AdminTienda tienda;
    private int turno;
    private AdminFlota[] flotasEnCamino = new AdminFlota[50];  // Máximo de 50 flotas en tránsito
    Colores c = new Colores();
    Herramientas h = new Herramientas();

    private Scanner sc;  // Única instancia de Scanner para evitar problemas de entrada/salida

    public MotorDeJuego(AdminJugador jugador1, AdminJugador jugador2, AdminMapa mapa) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.mapa = mapa;
        this.turno = 1;
        this.tienda = new AdminTienda();
        this.sc = new Scanner(System.in);  // Iniciar el scanner
    }
// Método para verificar si un jugador ha ganado

    public void primerJuego() {
        System.out.println(c.cian("KONQUEST - El objetivo del juego es conquistar todos los planetas."));
        System.out.println(c.cian("¡El jugador que conquiste todos los planetas gana!"));
        mapa.mostrarMapa();

        while (true) {
            h.separadorLinea();
            System.out.println("Es el turno: " + turno);
            if (turno % 2 != 0) {
                jugarTurno(jugador1);
                if (verificarVictoria(jugador1)) {
                    break;  // Terminar el juego si jugador1 ha ganado
                }
            } else {
                jugarTurno(jugador2);
                if (verificarVictoria(jugador2)) {
                    break;  // Terminar el juego si jugador2 ha ganado
                }
            }

            // Al final de cada turno, actualizar flotas y ejecutar producción de recursos
            actualizarFlotas();  // Verifica si alguna flota ha llegado a su destino
            producirRecursos();  // Actualiza recursos en cada turno
            mapa.mostrarMapa();  // Muestra el mapa actualizado
            turno++;
        }
        h.dobleLineaSeparadora();
        System.out.println(c.verde("¡Fin del juego!"));
    }
// Método para gestionar las opciones del turno

    public void jugarTurno(AdminJugador jugador) {
        boolean seguirTurno = true;

        while (seguirTurno) {
            verificarFlotas(); // Verifica si alguna flota ha llegado antes de que el jugador tome acción
            h.separadorLinea();
            System.out.println(c.cian("                         Turno actual: ") + c.rojo(jugador.getNombre()));
            h.separadorLinea();
            System.out.print(CELESTE+"1. Consultar planeta  ");
            System.out.print("2. Simular envío de flota  ");
            System.out.print("3. Enviar flota  ");
            System.out.print("4. Construir nave  ");
            System.out.println("5. Ir a la tienda  ");
            System.out.print("6. Ver recursos actuales  ");
            System.out.print("7. Terminar turno  ");
            System.out.print("8. Rendirse  "+RESET);
            System.out.print("  Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1:
                    consultarPlaneta();
                    break;
                case 2:
                    simularEnviarFlota(jugador);
                    break;
                case 3:
                    enviarFlota(jugador);
                    break;
                case 4:
                    construirNave(jugador);
                    break;
                case 5:
                    tienda.mostrarMenu(jugador);
                    break;
                case 6:
                    mostrarRecursosJugador(jugador);
                    break;
                case 7:
                    System.out.println(c.rojo("Turno terminado."));
                    seguirTurno = false;
                    break;
                case 8:
                    System.out.println(c.rojo(jugador.getNombre() + " se ha rendido."));
                    h.mensajeDespedida();
                    break;
                default:
                    System.out.println(c.rojo("Opción no válida."));
            }
            h.separadorLinea();
        }
    }

    // Método para verificar flotas en camino y si han llegado a su destino
    public void verificarFlotas() {
        h.separadorLinea();
        System.out.println(c.cian("     Flotas en camino:"));
        for (int i = 0; i < flotasEnCamino.length; i++) {
            if (flotasEnCamino[i] != null && flotasEnCamino[i].getTurnoLlegada() <= turno) {
                // La flota ha llegado al planeta de destino
                AdminPlanetas destino = flotasEnCamino[i].getDestino();
                AdminPlanetas origen = flotasEnCamino[i].getOrigen();
                AdminsGuerreros[] guerrerosAtacantes = flotasEnCamino[i].getGuerreros();

                System.out.println(c.verde("La flota ha llegado al planeta " + destino.getNombre()));

                if (destino.getDueño().equals("Neutral") || !destino.getDueño().equals(flotasEnCamino[i].getJugador().getNombre())) {
                    // Si es un planeta neutral o enemigo, inicia la batalla
                    boolean conquista = destino.batalla(guerrerosAtacantes, origen);
                    if (conquista) {
                        System.out.println(c.rojo("El planeta " + destino.getNombre() + " ha sido conquistado por " + flotasEnCamino[i].getJugador().getNombre() + "!"));
                        destino.setDueño(flotasEnCamino[i].getJugador().getNombre());
                    } else {
                        System.out.println(c.amarillo("Los defensores han repelido el ataque en " + destino.getNombre() + "."));
                    }
                } else {
                    // Si es un planeta aliado, simplemente se refuerzan los guerreros
                    destino.reforzarGuerreros(guerrerosAtacantes);
                    System.out.println(c.cian("Los guerreros han sido enviados para reforzar el planeta " + destino.getNombre() + "."));
                }

                // Eliminar la flota de las flotas en camino
                flotasEnCamino[i] = null;
            }
        }
    }

    // Método para actualizar las flotas en cada turno
    public void actualizarFlotas() {
        for (int i = 0; i < flotasEnCamino.length; i++) {
            if (flotasEnCamino[i] != null && flotasEnCamino[i].getTurnoLlegada() <= turno) {
                AdminPlanetas destino = flotasEnCamino[i].getDestino();
                AdminPlanetas origen = flotasEnCamino[i].getOrigen();
                AdminsGuerreros[] guerrerosAtacantes = flotasEnCamino[i].getGuerreros();
                AdminJugador jugador = flotasEnCamino[i].getJugador();
                h.separadorLinea();
                System.out.println(c.verde("La flota ha llegado al planeta " + destino.getNombre()));

                // Lógica de batalla o refuerzo
                if (destino.getDueño().equals("Neutral") || !destino.getDueño().equals(jugador.getNombre())) {
                    // Si es un planeta neutral o enemigo, inicia la batalla
                    boolean conquista = destino.batalla(guerrerosAtacantes, origen);
                    if (conquista) {
                        System.out.println(c.verde("El planeta " + destino.getNombre() + " ha sido conquistado por ") + c.rojo(jugador.getNombre() + "!"));
                        destino.setDueño(jugador.getNombre());
                    } else {
                        System.out.println(c.amarillo("Los defensores han repelido el ataque en " + destino.getNombre() + "."));
                    }
                } else {
                    // Si es un planeta aliado, refuerza los guerreros
                    destino.reforzarGuerreros(guerrerosAtacantes);
                    System.out.println(c.cian("Los guerreros han reforzado el planeta " + destino.getNombre() + "."));
                }

                // Eliminar la flota que ha llegado
                flotasEnCamino[i] = null;
            }
        }
    }

// Mostrar los recursos y planetas del jugador
    public void mostrarRecursosJugador(AdminJugador jugador) {
        h.separadorLinea();
        System.out.println("\n---- RECURSOS DE " + ROJO+ jugador.getNombre()+RESET+ " ----");
        System.out.println("Monedas: " + jugador.getPlanetaInicial().getMonedas());
        System.out.println("Planetas:");
        for (int i = 0; i < jugador.getPlanetas().length; i++) {
            if (jugador.getPlanetas()[i] != null) {
                 AdminPlanetas planeta = jugador.getPlanetas()[i];
                System.out.println("- " + planeta.getNombre() + " (Coordenadas: "
                        + planeta.getPosicion().getX() + "," + planeta.getPosicion().getY() + ")");
            }
        }
        h.separadorLinea();
    }
// Método para gestionar la producción de recursos al final del turno

    public void producirRecursos() {
        h.separadorLinea();
        System.out.println(AMARILLO+"\n--- PRODUCCIÓN DE RECURSOS ---"+RESET);
        for (int i = 0; i < mapa.getNumPlanetas(); i++) {
             AdminPlanetas planeta = mapa.getPlaneta(i);
            if (planeta != null) {
                System.out.println(AZUL+"Produciendo recursos en el planeta " + planeta.getNombre()+RESET);
                int recursosPrevios = planeta.getMonedas();
                planeta.producirRecursos();
                System.out.println(AZUL+"Recursos producidos: " + (planeta.getMonedas() - recursosPrevios) + " estelares."+RESET);
            }
        }
    }

// Mejorado para inicio de turno
    public void mostrarInicioDeTurno(int turno, AdminJugador jugador) {
        h.separadorLinea();
        System.out.println("\n************ INICIO DEL TURNO " + turno + " ************");
        System.out.println(c.rojo("Jugador actual: " + jugador.getNombre()));
        System.out.println("Es tu turno para realizar acciones. ¿Qué deseas hacer?");
    }

    // Método para consultar un planeta
    public void consultarPlaneta() {
        h.separadorLinea();
        System.out.println("---- CONSULTA DE PLANETA ----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas del planeta (formato: x,y): ");
        String[] coordenadas = sc.nextLine().split(",");
        int x = Integer.parseInt(coordenadas[0]);
        int y = Integer.parseInt(coordenadas[1]);

        AdminPlanetas planeta = mapa.obtenerPlaneta(new Posicion(x, y));

        if (planeta != null) {
            planeta.mostrarInfo();
        } else {
            System.out.println("El planeta no existe en esa posición.");
        }
        h.separadorLinea();
    }

    public void construirNave(AdminJugador jugador) {
        h.separadorLinea();
        Scanner sc = new Scanner(System.in);
         AdminPlanetas planeta;
         
        // Bucle para validar coordenadas
        while (true) {
            System.out.println("Ingrese las coordenadas del planeta donde desea construir la nave (formato: x,y): ");
            String[] coords = sc.nextLine().split(",");

            try {
                int x = Integer.parseInt(coords[0].trim());
                int y = Integer.parseInt(coords[1].trim());
                planeta = mapa.obtenerPlaneta(new Posicion(x, y));

                // Validar que el planeta exista y pertenezca al jugador
                if (planeta != null && planeta.getDueño().equals(jugador.getNombre())) {
                    break;  // Salir del bucle si las coordenadas son válidas
                } else {
                    System.out.println("El planeta no te pertenece o no existe. Intenta de nuevo.");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordenadas no válidas. Inténtalo de nuevo.");
            }
        }

        // Mostrar las opciones de naves
        System.out.println(CELESTE+"¿Qué tipo de nave desea construir?");
        System.out.println("1. Helios (Costo: 100 estelares)");
        System.out.println("2. Galaxia Prime (Costo: 200 estelares)");
        System.out.println("3. Nebulon (Costo: 500 estelares)");
        System.out.println("4. Odisea (Costo: 700 estelares)"+RESET);

        int opcionNave = sc.nextInt();
        switch (opcionNave) {
            case 1:
                planeta.construirNave("Helios");
                break;
            case 2:
                planeta.construirNave("Galaxia Prime");
                break;
            case 3:
                planeta.construirNave("Nebulon");
                break;
            case 4:
                planeta.construirNave("Odisea");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Método para simular el envío de flotas (sin realizar el ataque real)
    public void simularEnviarFlota(AdminJugador jugador) {
        Scanner sc = new Scanner(System.in);
                h.separadorLinea();
        // Ingresar las coordenadas del planeta de origen
        System.out.println(c.cian("Ingrese las coordenadas del planeta de origen (formato: x,y): "));
        String[] origenCoords = sc.nextLine().split(",");
        int origenX = Integer.parseInt(origenCoords[0].trim());
        int origenY = Integer.parseInt(origenCoords[1].trim());
        AdminPlanetas origen = mapa.obtenerPlaneta(new Posicion(origenX, origenY));

        if (origen == null || !origen.getDueño().equals(jugador.getNombre())) {
            System.out.println(c.rojo("El planeta de origen no te pertenece o no existe."));
            return;
        }

        // Verificar si hay naves disponibles
        if (origen.getNumNaves() == 0) {
            System.out.println(c.amarillo("No hay naves disponibles en el planeta " + origen.getNombre() + "."));
            return;
        }

        // Mostrar las naves disponibles y pedir al jugador que seleccione una
        System.out.println(c.cian("Seleccione la nave que desea enviar:"));
        AdminNaves[] navesDisponibles = origen.getNaves();
        for (int i = 0; i < navesDisponibles.length; i++) {
            if (navesDisponibles[i] != null) {
                System.out.println((i + 1) + ". " + navesDisponibles[i].getNombre());
            }
        }
        int opcionNave = sc.nextInt();
        sc.nextLine();  // Limpiar el buffer

        // Validar que la opción seleccionada sea válida
        if (opcionNave <= 0 || opcionNave > navesDisponibles.length || navesDisponibles[opcionNave - 1] == null) {
            System.out.println(ROJO+"Selección de nave inválida."+RESET);
            return;
        }

        AdminNaves naveSeleccionada = navesDisponibles[opcionNave - 1];

        // Mostrar los tipos de guerreros disponibles
        System.out.println(CELESTE+"Seleccione el tipo de guerrero a enviar:"+RESET);
        AdminsGuerreros[] guerrerosDisponibles = origen.getGuerreros();
        for (int i = 0; i < guerrerosDisponibles.length; i++) {
            if (guerrerosDisponibles[i] != null) {
                System.out.println((i + 1) + ". " + guerrerosDisponibles[i].getTipo());
            }
        }
        int opcionGuerrero = sc.nextInt();
        sc.nextLine();  // Limpiar el buffer

        // Validar que la opción seleccionada sea válida
        if (opcionGuerrero <= 0 || opcionGuerrero > guerrerosDisponibles.length || guerrerosDisponibles[opcionGuerrero - 1] == null) {
            System.out.println(ROJO+"Selección de guerrero inválida."+RESET);
            return;
        }

        AdminsGuerreros guerreroSeleccionado = guerrerosDisponibles[opcionGuerrero - 1];

        // Ingresar la cantidad de guerreros
        System.out.println(CELESTE+"Ingrese la cantidad de guerreros a enviar: "+RESET);
        int cantidadGuerreros = sc.nextInt();
        if (cantidadGuerreros > origen.numGuerreros) {
            System.out.println(AMARILLO+"No tienes suficientes guerreros."+RESET);
            return;
        }

        // Ingresar la cantidad de estelares para mejorar la tasa de supervivencia
        System.out.println(CELESTE+"Ingrese la cantidad de estelares para mejorar la tasa de supervivencia: "+RESET);
        int estelaresSupervivencia = sc.nextInt();
        sc.nextLine();  // Limpiar el buffer

        // Declarar la variable Planeta destino fuera del bloque while
         AdminPlanetas destino = null;
        boolean entradaValida = false;

        // Ingresar las coordenadas del planeta de destino y validarlas
        while (!entradaValida) {
            try {
                System.out.println(CELESTE+"Ingrese las coordenadas del planeta de destino (formato: x,y): "+RESET);
                String[] destinoCoords = sc.nextLine().split(",");
                if (destinoCoords.length != 2) {
                    throw new NumberFormatException(ROJO+"Formato incorrecto."+RESET);
                }

                // Convertir las coordenadas ingresadas a enteros
                int destinoX = Integer.parseInt(destinoCoords[0].trim());
                int destinoY = Integer.parseInt(destinoCoords[1].trim());

                // Crear la posición destino correctamente
                Posicion posicionDestino = new Posicion(destinoX, destinoY);

                // Obtener el planeta de destino usando la posición
                destino = mapa.obtenerPlaneta(posicionDestino);

                if (destino == null) {
                    System.out.println(AMARILLO+"El planeta de destino no existe. Intente de nuevo."+RESET);
                } else {
                    entradaValida = true;  // Las coordenadas son válidas, salimos del bucle
                }

            } catch (NumberFormatException e) {
                System.out.println(ROJO+"Error en el formato de las coordenadas. Asegúrese de ingresar el formato correcto (x,y)."+RESET);
            }
        }

        // Calcular distancia y tasa de supervivencia
        int turnosParaLlegada = mapa.calcularDistancia(origen, destino, naveSeleccionada);

        double tasaSupervivencia = naveSeleccionada.calculoDeTasaDeSupervivencia(turnosParaLlegada, estelaresSupervivencia);
        System.out.println(CELESTE+"La flota tardará " + turnosParaLlegada + " turnos en llegar."+RESET);

        System.out.println(VERDE+"Tasa de supervivencia estimada: " + tasaSupervivencia + "%."+RESET);
    }

    // Método para enviar una flota y realizar el ataque real
    public void enviarFlota(AdminJugador jugador) {
    Scanner envFlota = new Scanner(System.in);
        h.separadorLinea();
    // Ingresar las coordenadas del planeta de origen
    System.out.println(CELESTE+"Ingrese las coordenadas del planeta de origen (formato: x,y): "+RESET);
    String[] origenCoords = envFlota.nextLine().split(",");
    int origenX = Integer.parseInt(origenCoords[0].trim());
    int origenY = Integer.parseInt(origenCoords[1].trim());
     AdminPlanetas origen = mapa.obtenerPlaneta(new Posicion(origenX, origenY));

    // Verificar que el planeta de origen pertenezca al jugador
    if (origen == null || !origen.getDueño().equals(jugador.getNombre())) {
        System.out.println(AMARILLO+"El planeta de origen no te pertenece o no existe."+RESET);
        return;
    }

    // Verificar si hay naves disponibles en el planeta de origen
    if (origen.getNumNaves() == 0) {
        System.out.println(ROJO+"No hay naves disponibles en el planeta " + origen.getNombre() + "."+RESET);
        return;
    }

    // Mostrar las naves disponibles y pedir al jugador que seleccione una
    System.out.println(CELESTE+"Seleccione la nave que desea enviar:"+RESET);
    AdminNaves[] navesDisponibles = origen.getNaves();
    for (int i = 0; i < navesDisponibles.length; i++) {
        if (navesDisponibles[i] != null) {
            System.out.println((i + 1) + ". " + navesDisponibles[i].getNombre());
        }
    }
    int opcionNave = sc.nextInt();
    sc.nextLine();  // Limpiar el buffer
    AdminNaves naveSeleccionada = navesDisponibles[opcionNave - 1];

    // Mostrar los guerreros disponibles y pedir al jugador que seleccione uno
    System.out.println(CELESTE+"Ingrese la cantidad de guerreros a enviar: "+RESET);
    int cantidadGuerreros = sc.nextInt();
    if (cantidadGuerreros > origen.numGuerreros) {
        System.out.println(ROJO+"No tienes suficientes guerreros."+RESET);
        return;
    }

    // Crear el arreglo de guerreros atacantes
    AdminsGuerreros[] guerrerosAtacantes = new AdminsGuerreros[cantidadGuerreros];
    for (int i = 0; i < cantidadGuerreros; i++) {
        guerrerosAtacantes[i] = origen.guerreros[i];  // Copiar guerreros desde el origen
    }

    // Ingresar las coordenadas del planeta de destino
    System.out.println(CELESTE+"Ingrese las coordenadas del planeta de destino (formato: x,y): "+RESET);
    String[] destinoCoords = envFlota.nextLine().split(",");
    int destinoX = Integer.parseInt(destinoCoords[0].trim());
    int destinoY = Integer.parseInt(destinoCoords[1].trim());
    AdminPlanetas destino = mapa.obtenerPlaneta(new Posicion(destinoX, destinoY));

    // Verificar que el planeta de destino exista
    if (destino == null) {
        System.out.println(ROJO+"El planeta de destino no existe."+RESET);
        return;
    }

    // Calcular distancia en turnos
    int turnosParaLlegada = mapa.calcularDistancia(origen, destino, naveSeleccionada);

    // Crear la flota y agregarla al arreglo de flotas en tránsito
    for (int i = 0; i < flotasEnCamino.length; i++) {
        if (flotasEnCamino[i] == null) {
            AdminFlota nuevaFlota = new AdminFlota(cantidadGuerreros, naveSeleccionada, turno, origen, destino, turnosParaLlegada, jugador);
            flotasEnCamino[i] = nuevaFlota; // Añadir flota al arreglo
            System.out.println(AMARILLO+"Flota enviada. Llegará en " + turnosParaLlegada + " turnos."+RESET);
            break;
        }
    }

    // Actualizar los guerreros en el planeta de origen
    origen.numGuerreros -= cantidadGuerreros;

    // Eliminar la nave usada (se puede ajustar si queremos reutilizar naves)
    origen.eliminarNave();
}

    // Método para verificar la victoria del jugador
    private boolean verificarVictoria(AdminJugador jugador) {
        for (int i = 0; i < mapa.getNumPlanetas(); i++) {
            AdminPlanetas planeta = mapa.getPlaneta(i);
            if (!planeta.getDueño().equals(jugador.getNombre())) {
                return false;  // Si hay algún planeta que no es del jugador, no ha ganado
            }
        }
        h.dobleLineaSeparadora();
        System.out.println(VERDE+jugador.getNombre() + " ha ganado al conquistar todos los planetas!"+RESET);
        h.dobleLineaSeparadora();
        return true;
    }
}
