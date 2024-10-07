/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Naves.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.GuerrerosYPlanetas.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Constructores.*;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Colores;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros.Herramientas;
import com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Planetas.*;

/**
 *
 * @author DELL
 */
public abstract class AdminPlanetas  {
    protected String nombre;
    protected double porcentajeDeMuerte;
    protected int monedas;
    protected AdminConstructor constructor;
    private AdminNaves[] naves;
    protected AdminsGuerreros[] guerreros;
    private int numNaves;
    public int numGuerreros;
    protected Posicion posicion;
    protected String dueño;

    public AdminPlanetas(String nombre, double porcentajeDeMuerte, int monedas, AdminConstructor constructor, Posicion posicion, String dueño) {
        this.nombre = nombre;
        this.porcentajeDeMuerte = porcentajeDeMuerte;
        this.monedas = 200; // Asignar 200 monedas como cantidad inicial
        this.constructor = constructor;
        this.naves = new AdminNaves[5];
        this.guerreros = new AdminsGuerreros[10];
        this.numNaves = 0;
        this.numGuerreros = 0;
        this.posicion = posicion;
        this.dueño = dueño;
    }

    // Método para producir recursos automáticamente cada turno

public void producirRecursos() {
  
    
// La cantidad de monedas y guerreros producidos depende del tipo de planeta
    Colores c = new Colores();
    Herramientas h = new Herramientas();
    h.separadorLinea();
    if (this instanceof TierraPlaneta) {
        aumentarMonedas((int) (Math.random() * (100 - 50 + 1)) + 50);  // Entre 50 y 100 estelares
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Terradiente("Terradiente", 1.5, 1));  // Guerreros tipo Terradiente
        }
    } else if (this instanceof AguaPlaneta) {
        aumentarMonedas((int) (Math.random() * (120 - 60 + 1)) + 60);  // Entre 60 y 120 estelares
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Aquaris("Aquaris", 1.6, 1));  // Guerreros tipo Aquaris
        }
    } else if (this instanceof FuegoPlaneta) {
        aumentarMonedas((int) (Math.random() * (140 - 70 + 1)) + 70);  // Entre 70 y 140 estelares
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Ignis("Ignis", 1.75, 2));  // Guerreros tipo Ignis
        }
    } else if (this instanceof BiotaraPlaneta) {
        aumentarMonedas((int) (Math.random() * (160 - 80 + 1)) + 80);  // Entre 80 y 160 estelares
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Rooters("Rooters", 1.85, 3));  // Guerreros tipo Rooters
        }
    } else if (this instanceof RadioactivoPlaneta) {
        aumentarMonedas((int) (Math.random() * (180 - 90 + 1)) + 90);  // Entre 90 y 180 estelares
        if (numGuerreros < guerreros.length) {
            colocarGuerrero(new Rdonix("Rdonix", 1.90, 3));  // Guerreros tipo Rdonix
        }
    }

    System.out.println("Recursos producidos en el planeta " + nombre + ": " + monedas + " estelares, " + numGuerreros + " guerreros.");
}


    public String getNombre() {
        return nombre;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public AdminNaves[] getNaves() {
        return naves;  // Devuelve el arreglo de naves
    }

    public AdminsGuerreros[] getGuerreros() {
        return guerreros;
    }

    public int getNumNaves() {
        return numNaves;
    }

    public int getNumGuerreros() {
        return numGuerreros;
    }

    public int getMonedas() {
        return monedas;
    }

    public void aumentarMonedas(int cantidad) {
        this.monedas += cantidad;
    }

    public void reducirMonedas(int cantidad) {
        if (monedas >= cantidad) {
            this.monedas -= cantidad;
        } else {
            System.out.println("¡No hay suficientes monedas en el planeta " + nombre + "!");
        }
    }

    // Método para colocar una nave en el planeta
    public void colocarNave(AdminNaves nave) {
        if (numNaves >= naves.length) {
            expandirNaves();  // Expande el arreglo si está lleno
        }
        naves[numNaves++] = nave;
        System.out.println("Nave " + nave.getNombre() + " colocada en el planeta " + nombre);
    }

    private void expandirNaves() {
        AdminNaves[] nuevoArreglo = new AdminNaves[naves.length + 5];
        for (int i = 0; i < naves.length; i++) {
            nuevoArreglo[i] = naves[i];
        }
        naves = nuevoArreglo;
    }

    public void colocarGuerrero(AdminsGuerreros guerrero) {
        if (numGuerreros >= guerreros.length) {
            expandirGuerreros();  // Expande el arreglo si está lleno
        }
        guerreros[numGuerreros++] = guerrero;
        System.out.println("Guerrero colocado en el planeta " + nombre);
    }

    private void expandirGuerreros() {
        AdminsGuerreros[] nuevoArreglo = new AdminsGuerreros[guerreros.length + 10];
        for (int i = 0; i < guerreros.length; i++) {
            nuevoArreglo[i] = guerreros[i];
        }
        guerreros = nuevoArreglo;
    }

    // Simulación de batalla entre guerreros
    public boolean batalla(AdminsGuerreros[] atacantes, AdminPlanetas planetaAtacante) {
        int defensoresRestantes = numGuerreros;
        int atacantesRestantes = atacantes.length;

        while (defensoresRestantes > 0 && atacantesRestantes > 0) {
            AdminsGuerreros defensor = guerreros[defensoresRestantes - 1];
            AdminsGuerreros atacante = atacantes[atacantesRestantes - 1];

            double fuerzaDefensor = defensor.getFactorDeMuerte() * this.porcentajeDeMuerte;
            double fuerzaAtacante = atacante.getFactorDeMuerte() * planetaAtacante.porcentajeDeMuerte;

            if (fuerzaAtacante > fuerzaDefensor) {
                defensoresRestantes--;
            } else {
                atacantesRestantes--;
            }
        }

        if (atacantesRestantes > 0) {
            setDueño(planetaAtacante.getDueño());
            numGuerreros = atacantesRestantes;
            return true;
        }

        return false;  // Los defensores ganan
    }

    public void mostrarInfo() {
        Colores c = new Colores();
        Herramientas h = new Herramientas();
        System.out.println("Planeta: " + nombre + " (Posición: " + posicion + ")");
        System.out.println("Dueño: " + (dueño.isEmpty() ? "Neutral" : dueño));
        System.out.println("Dinero: " + monedas);
        System.out.println("Número de Naves: " + numNaves+" *");
        System.out.println("Número de Guerreros: " + numGuerreros+" *");
    }
//fin mostrarInfo

    public void construirNave(String tipoNave) {
    switch (tipoNave) {
        case "Helios":
            if (monedas >= 100) {
                reducirMonedas(100);
                colocarNave(new Helios());
                System.out.println("Nave Helios construida en el planeta " + nombre);
            } else {
                System.out.println("No hay suficientes monedas para construir una nave Helios.");
            }
            break;
        case "Galaxia Prime":
            if (monedas >= 200) {
                reducirMonedas(200);
                colocarNave(new Galaxiaprime());
                System.out.println("Nave Galaxia Prime construida en el planeta " + nombre);
            } else {
                System.out.println("No hay suficientes monedas para construir una nave Galaxia Prime.");
            }
            break;
        case "Nebulon":
            if (monedas >= 500) {
                reducirMonedas(500);
                colocarNave(new Nebulon());
                System.out.println("Nave Nebulon construida en el planeta " + nombre);
            } else {
                System.out.println("No hay suficientes monedas para construir una nave Nebulon.");
            }
            break;
        case "Odisea":
            if (monedas >= 700) {
                reducirMonedas(700);
                colocarNave(new Odisea());
                System.out.println("Nave Odisea construida en el planeta " + nombre);
            } else {
                System.out.println("No hay suficientes monedas para construir una nave Odisea.");
            }
            break;
        default:
            System.out.println("Tipo de nave desconocido.");
    }
}


    public void eliminarNave() {
    if (numNaves > 0) {
        // Eliminar la última nave del arreglo
        naves[numNaves - 1] = null;
        numNaves--; // Disminuir el contador de naves
        System.out.println("Nave eliminada del planeta " + nombre);
    } else {
        System.out.println("No hay naves para eliminar en el planeta " + nombre);
    }
}

  public void reforzarGuerreros(AdminsGuerreros[] guerrerosAtacantes) {
    for (AdminsGuerreros guerrero : guerrerosAtacantes) {
        if (guerrero != null) {
            colocarGuerrero(guerrero);
        }
    }
    System.out.println("Guerreros reforzados en el planeta " + nombre);
}


   private void actualizarGuerreros() {
    AdminsGuerreros[] nuevosGuerreros = new AdminsGuerreros[guerreros.length];
    int indice = 0;
    for (AdminsGuerreros guerrero : guerreros) {
        if (guerrero != null) {
            nuevosGuerreros[indice++] = guerrero;
        }
    }
    guerreros = nuevosGuerreros;
    numGuerreros = indice;
}

}
