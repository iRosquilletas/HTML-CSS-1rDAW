package com.acalabuig.model;

public class Maquinaria {
    private String tipo;
    /* En horas */
    private double horaUso; // in hours
    /* Hectáreas por hora */
    private double eficiencia; // hectares per hour

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

    public String getTipo() {
        return tipo;
    }

    public double getHoraUso() {
        return horaUso;
    }

    public double getEficiencia() {
        return eficiencia;
    }

    public double calcularTiempoArado(double hectareas) {
        if (hectareas <= 0) {
            throw new IllegalArgumentException("Hectareas debe ser un número positivo.");
        }
        return hectareas / eficiencia;
    }

    public void anyadirHorasTrabajo(double horasAdicionales) {
        if (horasAdicionales < 0) {
            throw new IllegalArgumentException("Horas adicionales no pueden ser negativas.");
        }
        this.horaUso += horasAdicionales;
    }
}
