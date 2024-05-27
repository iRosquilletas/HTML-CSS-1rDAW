package com.acalabuig;

import com.acalabuig.model.Producto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProductoTest2 {
    @Test
    public void testCalcularPrecioConDescuento() {
        Producto producto = new Producto("xd", 100);
        assertEquals(10, producto.calcularPrecioConDescuento(90), 0.001);
    }
}
