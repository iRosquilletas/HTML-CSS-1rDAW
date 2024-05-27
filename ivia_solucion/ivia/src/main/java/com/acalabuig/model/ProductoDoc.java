package com.acalabuig.model;

/**
 * Clase que representa un producto en el sistema.
 * 
 * Un producto tiene un nombre y un precio.
 * Se pueden calcular el precio con descuento de un producto.
 * 
 * @version 1.0
 * @see com.acalabuig.model.Producto
 */
public class Producto {

    private String nombre;
    /* En euros */
    private double precio;

    /**
     * Crea un nuevo producto con el nombre y precio especificados.
     * 
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     * @throws IllegalArgumentException si el nombre es nulo o vacío, o si el precio
     *                                  es negativo o cero.
     */
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

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Calcula el precio con descuento del producto aplicando un porcentaje de
     * descuento especificado.
     * 
     * @param porcentajeDescuento El porcentaje de descuento a aplicar.
     * @return El precio con descuento del producto.
     * @throws IllegalArgumentException si el porcentaje de descuento es negativo o
     *                                  mayor que 100.
     */
    public double calcularPrecioConDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        return precio * (1 - porcentajeDescuento / 100);
    }
}
