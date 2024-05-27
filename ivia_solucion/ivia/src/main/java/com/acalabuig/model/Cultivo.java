package com.acalabuig.model;

public class Cultivo {

    private String nombre;
    /* En hectareas */
    private double area;
    /* En toneladas por area */
    private double rendimiento;

    public Cultivo(String nombre, double area, double rendimiento) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del cultivo no puede ser nullo o vacío.");
        }
        if (area <= 0 || rendimiento <= 0) {
            throw new IllegalArgumentException("Area y rendimiento deben ser números positivos");
        }
        this.nombre = nombre;
        this.area = area;
        this.rendimiento = rendimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public double getArea() {
        return area;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public double calcularRendimientoTotal() {
        return area * rendimiento;
    }

    public double calcularIngresos(double precioPorTonelada) {
        return calcularRendimientoTotal() * precioPorTonelada;
    }
}
