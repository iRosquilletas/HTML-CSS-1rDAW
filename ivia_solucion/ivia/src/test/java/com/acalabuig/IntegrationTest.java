package com.acalabuig;

import com.acalabuig.model.Cultivo;
import com.acalabuig.model.Empleado;
import com.acalabuig.model.Maquinaria;
import com.acalabuig.model.Producto;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntegrationTest {
    @Test
    public void testCompanyiaAgricultoraIntegration() {
        Cultivo cultivo = new Cultivo("Trigo", 100, 3);
        Empleado empleado = new Empleado("Vicent Canyaules", "L'amo de tot açò", 3500);
        Producto producto = new Producto("Harina de trigo", 0.50);
        Maquinaria maquinaria = new Maquinaria("Tractor", 150, 5);

        assertEquals("Trigo", cultivo.getNombre());
        assertEquals(3, cultivo.getRendimiento(), 0.001);
        assertEquals(1500, cultivo.calcularIngresos(5), 0.001);

        assertEquals("Vicent Canyaules", empleado.getNombre());
        assertEquals("L'amo de tot açò", empleado.getCargo());
        assertEquals(42000, empleado.calculaSalarioAnual(), 0.001);
        assertEquals(3500, empleado.getSalario(), 0.001);

        assertEquals("Harina de trigo", producto.getNombre());
        assertEquals(0.45, producto.calcularPrecioConDescuento(10), 0.001);

        assertEquals("Tractor", maquinaria.getTipo());
        assertEquals(10, maquinaria.calcularTiempoArado(50), 0.001);
    }
}
