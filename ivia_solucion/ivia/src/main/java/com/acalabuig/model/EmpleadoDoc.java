package com.acalabuig.model;

/**
 * Clase que representa a un empleado en el sistema.
 * 
 * Un empleado tiene un nombre, un cargo y un salario.
 * Se pueden calcular el salario anual y dar aumentos al salario.
 * 
 * @version 1.0
 * @see com.acalabuig.model.Empleado
 */
public class Empleado {

    private String nombre;
    private String cargo;
    /* En euros */
    private double salario; // in Euros

    /**
     * Crea un nuevo empleado con el nombre, cargo y salario especificados.
     * 
     * @param nombre El nombre del empleado.
     * @param cargo El cargo del empleado.
     * @param salario El salario del empleado.
     * @throws IllegalArgumentException si el nombre o el cargo son nulos o vacíos, o si el salario es negativo o cero.
     */
    public Empleado(String nombre, String cargo, double salario) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede ser nulo o vacío.");
        }
        if (cargo == null || cargo.isEmpty()) {
            throw new IllegalArgumentException("Cargo no puede ser nulo o vacío.");
        }
        if (salario <= 0) {
            throw new IllegalArgumentException("Salario debe ser un número positivo.");
        }
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    /**
     * Obtiene el nombre del empleado.
     * 
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el cargo del empleado.
     * 
     * @return El cargo del empleado.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Obtiene el salario del empleado.
     * 
     * @return El salario del empleado.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Calcula el salario anual multiplicando el salario mensual por 12.
     * 
     * @return El salario anual del empleado.
     */
    public double calculaSalarioAnual() {
        return salario * 12;
    }

    /**
     * Da un aumento al salario del empleado según un porcentaje especificado.
     * 
     * @param porcentaje El porcentaje de aumento.
     * @throws IllegalArgumentException si el porcentaje de aumento es negativo.
     */
    public void darAumento(double porcentaje) {
        if (porcentaje < 0) {
            throw new IllegalArgumentException("El porcentaje de aumento no puede ser negativo.");
        }
        this.salario += this.salario * porcentaje / 100;
    }
}
