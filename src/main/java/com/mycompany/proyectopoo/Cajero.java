
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
    
    private void consultarEstadoPedido() {
    }
     
    private void cobrarPedido() {
        // consulta el estado y generar la factura
    } 
    private void cancelarPedido() {
        // consulta el estado y generar la factura
    } 
}
