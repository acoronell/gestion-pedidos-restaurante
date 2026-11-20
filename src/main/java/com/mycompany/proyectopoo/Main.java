
package com.mycompany.proyectopoo;

import javax.swing.JOptionPane;

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
        
        //login
        Usuario usuario = null;
        
        while (usuario == null){
            String user = JOptionPane.showInputDialog("Usuario");
            if (user == null){
                return;
            }
            
            String password = JOptionPane.showInputDialog("Contraseña");
            if (password == null){
                return;
            }
            
            usuario = restaurante.IniciarSesion(user, password);
            
            if (usuario == null){
                JOptionPane.showMessageDialog(null, "Usuairo o contraseña incorrectos");
            }
            
        } 
        
        //menu segun el rol de la persona que inicio sesion
        
        JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre());
        
        String op = "";
        while (!op.equals("0")) {
            op = JOptionPane.showInputDialog(usuario.mostrarMenuOpciones());

            if (op == null) {
                return;
            }

            if (op.equals("0")) {
                JOptionPane.showMessageDialog(null, "Sesion cerrada.");
            } 
}
        
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

    
