package com.acalabuig.model;

/**
 * Clase que representa un cultivo en el sistema.
 * 
 * Un cultivo tiene un nombre, un área en hectáreas y un rendimiento en toneladas por área.
 * Se pueden calcular el rendimiento total y los ingresos generados por el cultivo.
 * 
 * @version 1.0
 * @see com.acalabuig.model.Cultivo
 * @deprecated Esta clase está siendo reemplazada por una implementación más eficiente.
 */
public class Cultivo {

    private String nombre;
    /* En hectareas */
    private double area;
    /* En toneladas por area */
    private double rendimiento;

    /**
     * Crea un nuevo cultivo con el nombre, área y rendimiento especificados.
     * 
     * @param nombre El nombre del cultivo.
     * @param area El área del cultivo en hectáreas.
     * @param rendimiento El rendimiento del cultivo en toneladas por área.
     * @throws IllegalArgumentException si el nombre es nulo o vacío, o si el área o el rendimiento son negativos o cero.
     */
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

    /**
     * Obtiene el nombre del cultivo.
     * 
     * @return El nombre del cultivo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el área del cultivo en hectáreas.
     * 
     * @return El área del cultivo.
     */
    public double getArea() {
        return area;
    }

    /**
     * Obtiene el rendimiento del cultivo en toneladas por área.
     * 
     * @return El rendimiento del cultivo.
     */
    public double getRendimiento() {
        return rendimiento;
    }

    /**
     * Calcula el rendimiento total del cultivo multiplicando el área por el rendimiento.
     * 
     * @return El rendimiento total del cultivo.
     */
    public double calcularRendimientoTotal() {
        return area * rendimiento;
    }

    /**
     * Calcula los ingresos generados por el cultivo multiplicando el rendimiento total por el precio por tonelada.
     * 
     * @param precioPorTonelada El precio por tonelada del producto del cultivo.
     * @return Los ingresos generados por el cultivo.
     */
    public double calcularIngresos(double precioPorTonelada) {
        return calcularRendimientoTotal() * precioPorTonelada;
    }
}
