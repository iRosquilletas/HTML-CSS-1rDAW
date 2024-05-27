package com.acalabuig.model;

public class Producto {
    private String nombre;
    /* En euros */
    private double precio;

    public Producto(String nombre, double precio) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de producto no puede ser nulo o vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("Precio debe ser positivo.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double calcularPrecioConDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        return precio * (1 - porcentajeDescuento / 100);
    }
}
