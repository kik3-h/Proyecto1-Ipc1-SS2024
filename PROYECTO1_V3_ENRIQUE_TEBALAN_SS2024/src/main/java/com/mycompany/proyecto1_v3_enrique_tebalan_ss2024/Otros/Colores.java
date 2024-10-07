/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_v3_enrique_tebalan_ss2024.Otros;

/**
 *
 * @author DELL
 */
public class Colores {
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    private static final String PURPURA = "\u001B[35m";
    private static final String CIAN = "\u001B[36m";
    private static final String BLANCO = "\u001B[37m";
    public static final String CELESTE = "\u001B[96m";

    // Colores secundarios
    public static final String NARANJA = "\u001B[38;5;208m";
    private static final String VERDE_CLARO = "\u001B[38;5;118m";
    public static final String AZUL_CLARO = "\u001B[38;5;69m";
    private static final String ROSA = "\u001B[38;5;206m";
    private static final String MORADO = "\u001B[38;5;57m";
    private static final String CIAN_CLARO = "\u001B[38;5;87m";

    public static final String GRIS = "\u001B[90m";

    public String azul(String palabra) {
        return AZUL + palabra + RESET;
    }

    public String rojo(String palabra) {
        return ROJO + palabra + RESET;
    }

    public String verde(String palabra) {
        return VERDE + palabra + RESET;
    }

    public String amarillo(String palabra) {
        return AMARILLO + palabra + RESET;
    }

    public String purpura(String palabra) {
        return PURPURA + palabra + RESET;
    }

    public String cian(String palabra) {
        return CIAN + palabra + RESET;
    }

    public String blanco(String palabra) {
        return BLANCO + palabra + RESET;
    }

    public String gris(String palabra) {
        return GRIS + palabra + RESET;
    }

    public String naranja(String palabra) {
        return NARANJA + palabra + RESET;
    }

    public String verdeClaro(String palabra) {
        return VERDE_CLARO + palabra + RESET;
    }

    public String azulClaro(String palabra) {
        return AZUL_CLARO + palabra + RESET;
    }

    public String rosa(String palabra) {
        return ROSA + palabra + RESET;
    }

    public String morado(String palabra) {
        return MORADO + palabra + RESET;
    }

    public String cianClaro(String palabra) {
        return CIAN_CLARO + palabra + RESET;
    }
}
