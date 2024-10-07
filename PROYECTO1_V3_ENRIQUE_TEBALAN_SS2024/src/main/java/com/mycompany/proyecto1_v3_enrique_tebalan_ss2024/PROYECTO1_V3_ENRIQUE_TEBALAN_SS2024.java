/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.GuerrerosYPlanetas.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Herramientas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Planetas.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.CELESTE;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.RESET;
import static com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores.ROJO;
import java.util.Scanner;
/**
 *
 * @author DELL
 */
public class PROYECTO1_V3_ENRIQUE_TEBALAN_SS2024 {

    public static void main(String[] args) {
        Colores color = new Colores();
        Herramientas h = new Herramientas();
        Scanner sc = new Scanner(System.in);
        h.dobleLineaSeparadora();
        h.mensajeBienvenida();
        h.menuOpciones();
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            // DISEÑAR MAPAAAA
            case 1:
                    System.out.print(color.amarillo("           Ingrese el número de filas para el mapa: "));
                    int filas = sc.nextInt();
                    System.out.print(color.amarillo("           Ingrese el número de columnas para el mapa: "));
                    int columnas = sc.nextInt();

                    AdminMapa mapa = new AdminMapa(filas, columnas); //aca creamos dimensiones mapa

                    sc.nextLine();  // Limpiar buffer

                    // Obtener nombres de los jugadores
                    System.out.print(color.cian("           Ingrese el nombre del Jugador 1: "));
                    String nombre1 = sc.nextLine();
                    System.out.print(color.cian("           Ingrese el nombre del Jugador 2: "));
                    String nombre2 = sc.nextLine();

                    // Jugador 1 selecciona posición
                    Posicion posicion1 = seleccionarPosicion(sc, nombre1, filas, columnas);
                    AdminPlanetas planeta1 = seleccionarTipoDePlaneta(sc, nombre1, posicion1);

                    // Jugador 2 selecciona posición
                    Posicion posicion2 = seleccionarPosicion(sc, nombre2, filas, columnas);
                    AdminPlanetas planeta2 = seleccionarTipoDePlaneta(sc, nombre2, posicion2);

                    AdminJugador jugador1 = new AdminJugador(nombre1, planeta1);
                    AdminJugador jugador2 = new AdminJugador(nombre2, planeta2);

                    mapa.colocarPlaneta(planeta1);
                    mapa.colocarPlaneta(planeta2);

                    // Generar planetas neutrales
                    System.out.print(color.verde("          Ingrese el número de planetas neutrales a crear: "));
                    int numNeutrales = sc.nextInt();
                    generarPlanetasNeutrales(mapa, numNeutrales);

                    // Iniciar el juego
                    MotorDeJuego juego = new MotorDeJuego(jugador1, jugador2, mapa);
                    h.separadorLinea();
                    juego.primerJuego();
            break;
            case 2:
            System.out.println("aletioraridad SIN FUNCIONAR :(");
            break;
            
            case 3:
                h.mensajeDespedida();
           break;
            default:
                System.out.println("INRESE OPCION VALIDA");
        }
    }

// Método para que los jugadores seleccionen si desean colocar su planeta manualmente o aleatoriamente
    public static Posicion seleccionarPosicion(Scanner sc, String jugador, int filas, int columnas) {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        h.separadorLinea();
        System.out.println("            "+ROJO+jugador +RESET+ ", ¿deseas ubicar tu planeta manualmente? (S/N): ");
        char respuesta = sc.next().charAt(0);

        if (respuesta == 'S' || respuesta == 's') {
            System.out.print(c.cian("Ingrese la posición de su planeta (formato: x,y): "));
            String[] coords = sc.next().split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            return new Posicion(x, y);
        } else {
            // Asignar una posición aleatoria
            int x = (int) (Math.random() * filas);
            int y = (int) (Math.random() * columnas);
            System.out.println("            "+CELESTE+"Posición aleatoria asignada: ("+RESET + x + "," + y + ")");
            return new Posicion(x, y);
        }

    }

    // Método para que los jugadores seleccionen el tipo de planeta inicial
    public static AdminPlanetas seleccionarTipoDePlaneta(Scanner sc, String dueño, Posicion posicion) {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        h.separadorLinea();
        System.out.println("            Seleccione el tipo de planeta para " + ROJO+ dueño + RESET+ ": ");
        System.out.println(CELESTE+"            1. Planeta Tierra");
        System.out.println("            2. Planeta Agua");
        System.out.println("            3. Planeta Fuego");
        System.out.println("            4. Planeta Biotara");
        System.out.println("            5. Planeta Radioactivo"+RESET);
        System.out.print("              Ingrese una opción: ");
        int tipo = sc.nextInt();

        switch (tipo) {
            case 1:
                return new TierraPlaneta("Tierra", posicion, dueño);
            case 2:
                return new AguaPlaneta("Agua", posicion, dueño);
            case 3:
                return new FuegoPlaneta("Fuego", posicion, dueño);
            case 4:
                return new BiotaraPlaneta("Biotara", posicion, dueño);
            case 5:
                return new RadioactivoPlaneta("Radioactivo", posicion, dueño);
            default:
                System.out.println(c.rojo("Opción no válida. Seleccionando Planeta Tierra por defecto."));
                return new TierraPlaneta("Tierra", posicion, dueño);
        }
    }

    // Método para generar planetas neutrales aleatorios con tipos de planetas
    public static void generarPlanetasNeutrales(AdminMapa mapa, int numNeutrales) {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        for (int i = 0; i < numNeutrales; i++) {
            Posicion posicion;
            do {
                int fila = (int) (Math.random() * mapa.getFilas());
                int columna = (int) (Math.random() * mapa.getColumnas());
                posicion = new Posicion(fila, columna);
            } while (mapa.obtenerPlaneta(posicion) != null);

            // Seleccionar tipo de planeta neutral de manera aleatoria
            int tipo = (int) (Math.random() * 5) + 1;
            AdminPlanetas planetaNeutral;
            switch (tipo) {
                case 1:
                    planetaNeutral = new TierraPlaneta("Neutral Tierra" + (i + 1), posicion, "Neutral");
                    break;
                case 2:
                    planetaNeutral = new AguaPlaneta("Neutral Agua" + (i + 1), posicion, "Neutral");
                    break;
                case 3:
                    planetaNeutral = new FuegoPlaneta("Neutral Fuego" + (i + 1), posicion, "Neutral");
                    break;
                case 4:
                    planetaNeutral = new BiotaraPlaneta("Neutral Biotara" + (i + 1), posicion, "Neutral");
                    break;
                case 5:
                    planetaNeutral = new RadioactivoPlaneta("Neutral Radioactivo" + (i + 1), posicion, "Neutral");
                    break;
                default:
                    planetaNeutral = new TierraPlaneta("Neutral Tierra" + (i + 1), posicion, "Neutral");
            }
            mapa.colocarPlaneta(planetaNeutral);
        }
    }
}


