package com.acalabuig;

import com.acalabuig.model.Cultivo;
import com.acalabuig.model.Empleado;
import com.acalabuig.model.Maquinaria;
import com.acalabuig.model.Producto;

import org.junit.Test;

public class ExceptionTest {
    @Test(expected = IllegalArgumentException.class)
    public void testCultivoConstructorException() {
        new Cultivo("", 100, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpleadoConstructorException() {
        new Empleado("Vicent Canyaules", "L'amo de tot açò", -3500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductoConstructorException() {
        new Producto("Harina de trigo", -0.50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaquinariaConstructorException() {
        new Maquinaria("", 150, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaquinariaHorasNegativasException() {
        new Maquinaria("Tractor", -150, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaquinariaHetareasNegativasException() {
        Maquinaria maquinaria = new Maquinaria("Tractor", 150, 5);
        maquinaria.calcularTiempoArado(-50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpleadoAumentoNegativoException() {
        Empleado empleado = new Empleado("Vicent Canyaules", "L'amo de tot açò", 3500);
        empleado.darAumento(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductoDescuentoInvalidoPorAbajoException() {
        Producto producto = new Producto("Harina de trigo", 0.50);
        producto.calcularPrecioConDescuento(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductoDescuentoInvalidoPorArribaException() {
        Producto producto = new Producto("Harina de trigo", 0.50);
        producto.calcularPrecioConDescuento(110);
    }
}
