package com.acalabuig;

import com.acalabuig.model.Empleado;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmpleadoTest2 {
    @Test
    public void testCalculaSalarioAnual() {
        Empleado empleado = new Empleado("xd", "ninguno", 1000);
        assertEquals(12000, empleado.calculaSalarioAnual(), 0.001);
    }

    @Test
    public void testDarAumento() {
        Empleado empleado = new Empleado("dx", "alguno", 2000);
        empleado.darAumento(10);
        assertEquals(2200, empleado.getSalario(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNombreEmpleadoNoVacio() {
        new Empleado("", "kk", 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSalarioDebeSerPositivo() {
        new Empleado("dx", "ls", -1);
    }
}
