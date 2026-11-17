
package com.mycompany.proyectopoo;

public class Cajero extends Usuario {

    public Cajero() {
    }

    public Cajero(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }
    
    @Override
    public String mostrarMenuOpciones() {
        // despliegue de opciones disponibles como cajero
        return "";
    }
    
    public void consultarEstadoPedido(String id) {
        //consulta el estado de un pedido específico
    }
     
    public void cobrarPedido(String id) {
        // consulta el estado y generar la factura
    } 
    public void consultarPedidoPorCobrar() {
        // consulta todos los pedidos que están pendientes de pago
    } 
}
