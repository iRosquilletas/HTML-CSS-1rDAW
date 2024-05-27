package com.acalabuig;

import com.acalabuig.model.Maquinaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaquinariaTest2 {
    @Test
    public void testCalcularTiempoArado() {
        Maquinaria maquinaria = new Maquinaria("xd", 10, 5);
        assertEquals(2, maquinaria.calcularTiempoArado(10), 0.01);
    }

    @Test
    public void testAnyadirHorasTrabajo() {
        Maquinaria maquinaria = new Maquinaria("dx", 10, 5);
        maquinaria.anyadirHorasTrabajo(10);
        assertEquals(20, maquinaria.getHoraUso(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTipoMaquinariaNoVacio() {
        new Maquinaria("", 9, 8);
    }

}
