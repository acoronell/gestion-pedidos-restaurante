
package com.mycompany.proyectopoo;

import java.util.ArrayList;

public class Mesero extends Usuario {

    public Mesero() {
    }

    public Mesero(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }

    @Override
    public String mostrarMenuOpciones() {
        // despliegue de opciones disponibles como mesero
        return "";
    }

    public void registrarPedido(Pedido pedido) {
    }

    public void modificarPedido(String id, ArrayList<DetallePedido> detalles){
    }
    
    public void CancelarPedido(String id) {
    }

    public void actualizarEstadoPedido(String id, EstadoPedido estado) {
    }

    public void consultarEstadoPedido(String id) {
    }
    
    public void consultarPedido(String id){
    }
    
    public void consultarMenu(){
    }
}