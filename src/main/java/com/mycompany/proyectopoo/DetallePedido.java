
package com.mycompany.proyectopoo;

public class DetallePedido {
    private Plato plato;
    private int cantidad;

    public DetallePedido() {
    }

    public DetallePedido(Plato plato, int cantidad) {
        this.plato = plato;
        this.cantidad = cantidad;
    }
   
    public double calcularSubtotal() {
        return 0; // cantidad por precio del plato
    }

    public Plato getPlato() {
        return plato;
    }

    public int getCantidad() {
        return cantidad;
    }
   
}

