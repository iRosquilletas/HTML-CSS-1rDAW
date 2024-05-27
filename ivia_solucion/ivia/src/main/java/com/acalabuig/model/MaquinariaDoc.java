package com.acalabuig.model;

/**
 * Clase que representa una maquinaria en el sistema.
 * 
 * Una maquinaria tiene un tipo, un número de horas de uso y una eficiencia en hectáreas por hora.
 * Se pueden calcular el tiempo de trabajo necesario para arar una cierta cantidad de hectáreas y añadir horas de trabajo adicionales.
 * 
 * @version 1.0
 * @see com.acalabuig.model.Maquinaria
 */
public class Maquinaria {

    private String tipo;
    /* En horas */
    private double horaUso; // in hours
    /* Hectáreas por hora */
    private double eficiencia; // hectares per hour

    /**
     * Crea una nueva maquinaria con el tipo, horas de uso y eficiencia especificados.
     * 
     * @param tipo El tipo de maquinaria.
     * @param horasUso Las horas de uso de la maquinaria.
     * @param eficiencia La eficiencia de la maquinaria en hectáreas por hora.
     * @throws IllegalArgumentException si el tipo es nulo o vacío, o si las horas de uso son negativas, o si la eficiencia es negativa o cero.
     */
    public Maquinaria(String tipo, double horasUso, double eficiencia) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Tipo no puede ser nulo o vacío.");
        }
        if (horasUso < 0 || eficiencia <= 0) {
            throw new IllegalArgumentException("Horas de uso no puede ser negativo y efeciciencia debe ser positiva.");
        }
        this.tipo = tipo;
        this.horaUso = horasUso;
        this.eficiencia = eficiencia;
    }

    /**
     * Obtiene el tipo de la maquinaria.
     * 
     * @return El tipo de la maquinaria.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene las horas de uso de la maquinaria.
     * 
     * @return Las horas de uso de la maquinaria.
     */
    public double getHoraUso() {
        return horaUso;
    }

    /**
     * Obtiene la eficiencia de la maquinaria.
     * 
     * @return La eficiencia de la maquinaria.
     */
    public double getEficiencia() {
        return eficiencia;
    }

    /**
     * Calcula el tiempo de trabajo necesario para arar una cierta cantidad de hectáreas.
     * 
     * @param hectareas La cantidad de hectáreas a arar.
     * @return El tiempo de trabajo necesario en horas.
     * @throws IllegalArgumentException si las hectáreas son negativas o cero.
     */
    public double calcularTiempoArado(double hectareas) {
        if (hectareas <= 0) {
            throw new IllegalArgumentException("Hectareas debe ser un número positivo.");
        }
        return hectareas / eficiencia;
    }

    /**
     * Añade horas de trabajo adicionales a la maquinaria.
     * 
     * @param horasAdicionales Las horas de trabajo adicionales a añadir.
     * @throws IllegalArgumentException si las horas adicionales son negativas.
     */
    public void anyadirHorasTrabajo(double horasAdicionales) {
        if (horasAdicionales < 0) {
            throw new IllegalArgumentException("Horas adicionales no pueden ser negativas.");
        }
        this.horaUso += horasAdicionales;
    }
}
