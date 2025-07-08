package com.ejemplo;

/**
 * Clase Usuario para la plataforma HealthTrack
 * NOTA: Esta clase contiene el error original del sistema
 */
public class Usuario {
    private String nombre;
    private double peso;

    /**
     * Constructor para crear un nuevo usuario
     * @param nombre Nombre del usuario
     * @param peso Peso inicial del usuario
     */
    public Usuario(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    /**
     * Obtiene el nombre del usuario
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el peso actual del usuario
     * @return peso actual del usuario
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Actualiza el peso del usuario
     * ERROR CRÍTICO: En lugar de asignar el nuevo peso, se está restando 1kg
     * @param nuevoPeso El nuevo peso a asignar
     */
    public void actualizarPeso(double nuevoPeso) {
        // ERROR: En lugar de asignar el nuevo peso, se está restando 1kg.
        this.peso -= 1;
    }

    /**
     * Muestra la información del usuario
     */
    public void mostrarInformacion() {
        System.out.println("Usuario: " + nombre + ", Peso Actual: " + peso + " kg");
    }
}