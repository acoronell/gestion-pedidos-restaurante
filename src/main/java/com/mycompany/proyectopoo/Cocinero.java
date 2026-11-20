
package com.mycompany.proyectopoo;

public class Cocinero extends Usuario{

    public Cocinero() {
    }

    public Cocinero(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }
    
    @Override
    public String mostrarMenuOpciones() {
        // despliegue de opciones disponibles como cocinero
        return "";
    }
    
    @Override
    public abstract void ejecutarOpcion(int opcion, Restaurante restaurante);
    
     public void consultarEstadoPedido() {
    }
    
     public void consultarPedidosPendientes() {
    }
}
