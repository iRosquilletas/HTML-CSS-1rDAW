package com.acalabuig.model;

public class Empleado {
    private String nombre;
    private String cargo;
    /* En euros */
    private double salario; // in Euros

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

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public double calculaSalarioAnual() {
        return salario * 12;
    }

    public void darAumento(double porcentaje) {
        if (porcentaje < 0) {
            throw new IllegalArgumentException("El porcentaje de aumento no puede ser negativo.");
        }
        this.salario += this.salario * porcentaje / 100;
    }
}
