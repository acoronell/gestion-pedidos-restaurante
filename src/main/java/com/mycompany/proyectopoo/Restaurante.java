package com.mycompany.proyectopoo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Restaurante {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Plato> menu;
    private LinkedList<Pedido> pedidosPendientes;
    private ArrayList<Pedido> todosLosPedidos;
    private int contadorPedidos = 0;

    // para que no genere excepciones
    public Restaurante() {
        usuarios = new ArrayList<>();
        menu = new ArrayList<>();
        pedidosPendientes = new LinkedList<>();
        todosLosPedidos = new ArrayList<>();
    }
    
    public Usuario IniciarSesion(String user, String password){
        //COMPLETAR CUANDO SE CONECTE A LA BASE DE DATOS!!!
        return null;
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
    
    public void agregarPlato(Plato plato) {
        menu.add(plato);
    }
    
    public Plato buscarPlato(String id) {
        for (Plato p : menu) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    public void eliminarPlato(String id) {
        Plato plato = buscarPlato(id);

        if (plato != null) {
            menu.remove(plato);
        }
    }
    
    public ArrayList<Plato> getMenu() {
        return menu;
    }
    
    public void agregarPedido(Pedido pedido) {
        todosLosPedidos.add(pedido);
        pedidosPendientes.add(pedido);
    }

    public Pedido buscarPedido(String id) {
        for (Pedido p : todosLosPedidos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void quitarDePendientes(Pedido pedido) {
        pedidosPendientes.remove(pedido);
    }

    public String generarIdPedido() {
        contadorPedidos++;
        return "PED" + contadorPedidos;
    }
}   

