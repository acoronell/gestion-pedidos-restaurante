
package com.mycompany.proyectopoo;

import java.util.ArrayList;

public class Pedido {
    private String id;
    private int mesa;
    private EstadoPedido estado;
    private ArrayList<DetallePedido> detalles;
    
    public Pedido() {
    }

    public Pedido(String id, int mesa, EstadoPedido estado, ArrayList<DetallePedido> detalles) {
        this.id = id;
        this.mesa = mesa;
        this.estado = estado;
        this.detalles = detalles;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    
    public void agregarDetalle(DetallePedido detalle) {
    }

    public double calcularTotal() {
        return 0.0;
    }

    public void cambiarEstado(EstadoPedido estado) {
    }
}
