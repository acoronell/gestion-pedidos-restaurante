package com.mycompany.proyectopoo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Restaurante {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Plato> menu;
    private LinkedList<Pedido> pedidosPendientes;
    private ArrayList<Pedido> todosLosPedidos;

    // para que no genere excepciones
    public Restaurante() {
        usuarios = new ArrayList<>();
        menu = new ArrayList<>();
        pedidosPendientes = new LinkedList<>();
        todosLosPedidos = new ArrayList<>();
    }
    
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    public Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }

        return null;
    }
    
    public void eliminarUsuario(String id) {
    Usuario usuario = buscarUsuario(id);

    if (usuario != null) {
        usuarios.remove(usuario);
    }
  }
}   

