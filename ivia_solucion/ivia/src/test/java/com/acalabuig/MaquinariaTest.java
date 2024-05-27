package com.acalabuig;

import com.acalabuig.model.Maquinaria;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaquinariaTest {
    @Test
    public void testCalcularTiempoArado() {
        Maquinaria maquinaria = new Maquinaria("Tractor", 150, 5);
        assertEquals(10, maquinaria.calcularTiempoArado(50), 0.001);
    }

    @Test
    public void testAnyadirHorasTrabajo() {
        Maquinaria maquinaria = new Maquinaria("Tractor", 150, 5);
        maquinaria.anyadirHorasTrabajo(10);
        assertEquals(160, maquinaria.getHoraUso(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTipoMaquinariaNoVacio() {
        new Maquinaria("", 150, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHorasUsoNoNegativo() {
        new Maquinaria("Tractor", -150, 5);
    }
}
