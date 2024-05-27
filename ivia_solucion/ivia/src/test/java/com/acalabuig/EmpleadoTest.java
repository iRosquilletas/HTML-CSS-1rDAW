package com.acalabuig;

import com.acalabuig.model.Empleado;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmpleadoTest {
    @Test
    public void testCalculaSalarioAnual() {
        Empleado empleado = new Empleado("John Doe", "Manager", 3500);
        assertEquals(42000, empleado.calculaSalarioAnual(), 0.001);
    }

    @Test
    public void testDarAumento() {
        Empleado empleado = new Empleado("John Doe", "Manager", 3500);
        empleado.darAumento(10);
        assertEquals(3850, empleado.getSalario(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNombreEmpleadoNoVacio() {
        new Empleado("", "L'amo de tot açò", 3500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSalarioDebeSerPositivo() {
        new Empleado("Vicent Canyaules", "L'amo de tot açò", -3500);
    }
}
