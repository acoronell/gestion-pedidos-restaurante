
package com.mycompany.proyectopoo;

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

    private void registrarPedido() {
    }

    private void modificarOCancelarPedido() {
    }

    private void actualizarEstadoPedido() {
    }

    private void consultarEstadoPedido() {
    }
}