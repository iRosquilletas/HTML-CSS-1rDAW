package com.acalabuig;

import com.acalabuig.model.Cultivo;

import org.junit.Test;
import static org.junit.Assert.*;

public class CultivoTest {
    @Test
    public void testCalcularRendimientoTotal() {
        Cultivo cultivo = new Cultivo("Trigo", 100, 3);
        assertEquals(300, cultivo.calcularRendimientoTotal(), 0.001);
    }

    @Test
    public void testCalcularIngresos() {
        Cultivo cultivo = new Cultivo("Wheat", 100, 3);
        assertEquals(1500, cultivo.calcularIngresos(5), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNombreCultivoNoPuedeSerVacio() {
        new Cultivo("", 100, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreaDebeSerPositivo() {
        new Cultivo("Trigo", -100, 3);
    }
}
