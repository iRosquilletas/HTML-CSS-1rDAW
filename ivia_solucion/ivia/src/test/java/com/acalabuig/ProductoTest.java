package com.acalabuig;

import com.acalabuig.model.Producto;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductoTest {
    @Test
    public void testCalcularPrecioConDescuento() {
        Producto producto = new Producto("Wheat Flour", 0.50);
        assertEquals(0.45, producto.calcularPrecioConDescuento(10), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNombreProductoNoVacio() {
        new Producto("", 0.50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrecioDebeSerPositivo() {
        new Producto("Harina de trigo", -0.50);
    }
}
