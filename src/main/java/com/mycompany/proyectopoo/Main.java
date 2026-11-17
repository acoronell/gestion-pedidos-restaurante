
package com.mycompany.proyectopoo;

public class Main {

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        
        //registrar usuario
        Mesero mesero = new Mesero("001", "carlos", "carlos01", "1234");
        Cocinero cocinero = new Cocinero("002", "pedro", "pedro01", "2341");
        Administrador administrador = new Administrador("003", "ana", "ana01", "3412");
        Cajero cajero = new Cajero("004", "luis", "luis01", "4321");

        restaurante.registrarUsuario(mesero);
        restaurante.registrarUsuario(cocinero);
        restaurante.registrarUsuario(administrador);
        restaurante.registrarUsuario(cajero);
        
        //buscar usuario
        Usuario encontrado = restaurante.buscarUsuario("001");
        if (encontrado != null) {
            System.out.println("usuario encontrado: " + encontrado.getNombre());
        } else {
            System.out.println("usuario no encontrado");
        }
        
        //eliminar usuario
        restaurante.eliminarUsuario("001");

        //comprueba que fue eliminado
        Usuario eliminado = restaurante.buscarUsuario("001");
        if (eliminado == null) {
            System.out.println("usuario eliminado correctamente");
        } else {
            System.out.println("el usuario todavia existe");
        }
    }
}

    
