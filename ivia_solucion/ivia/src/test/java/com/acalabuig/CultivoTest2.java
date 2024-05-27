package com.acalabuig;

import com.acalabuig.model.Cultivo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CultivoTest2 {
    @Test
    public void testCalcularRendimientoTotal() {
        Cultivo cultivo = new Cultivo("XD", 100, 4);
        assertEquals(400, cultivo.calcularRendimientoTotal(), 0.01);
    }

    @Test
    public void testCalcularIngresos() {
        Cultivo cultivo = new Cultivo("algo", 100, 5);
        assertEquals(5000, cultivo.calcularIngresos(10), 0.01);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNombreCultivoNoPuedeSerVacio() {
        new Cultivo("", 100, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreaDebeSerPositivo() {
        new Cultivo("hola", 100, -10);
    }

}
