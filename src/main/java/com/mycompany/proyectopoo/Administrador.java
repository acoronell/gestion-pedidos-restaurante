
package com.mycompany.proyectopoo;

public class Administrador extends Usuario {

    public Administrador() {
    }
    
    public Administrador(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }
    
    @Override
    public String mostrarMenuOpciones() {
        return "Menu administrador\n"
                + "1. Añadir plato\n"
                + "2. Modificar plato\n"
                + "3. Eliminar plato\n"
                + "4. Consultar plato\n"
                + "5. Consultar menu\n"
                + "6. Consultar pedido"
                + "7. Consultar menu"
                + "8. Salir";
    }
    }
    
    public void añadirPlato() {
    }

    public void modificarPlato() {
    }

    public void eliminarPlato() {
    }
    
    public void consultarMenu() {
    }
}