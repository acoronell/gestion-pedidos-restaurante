
package com.mycompany.proyectopoo;

public class Administrador extends Usuario {

    public Administrador() {
    }
    
    public Administrador(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }
    
    @Override
    public String mostrarMenuOpciones() {
        // despliegue de opciones disponibles como admin
        return "";
    }
    
    private void añadirPlato() {
    }

    private void modificarPlato() {
    }

    private void eliminarPlato() {
    }
}