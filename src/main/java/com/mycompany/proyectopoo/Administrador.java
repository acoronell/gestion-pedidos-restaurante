
package com.mycompany.proyectopoo;

import javax.swing.JOptionPane;

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
                + "4. Consultar menu\n"
                + "5. Salir";
    }
    
    @Override
    public void ejecutarOpcion(int opcion, Restaurante restaurante){
        if (opcion == 1){
            añadirPlato(restaurante);
        }
        
        else if (opcion == 2){
            modificarPlato(restaurante);
        }
        
        else if (opcion == 3){
            eliminarPlato(restaurante);
        }
        
        else if(opcion == 4){
            consultarMenu(restaurante);
        }
    }
    
    public void añadirPlato(Restaurante restaurante) {
        String id = JOptionPane.showInputDialog("ID del plato: ");
        String nombre = JOptionPane.showInputDialog("Nombre del plato: ");
        String categoria = JOptionPane.showInputDialog("Categoria del plato: ");
        String precio = JOptionPane.showInputDialog("Precio del plato: ");
        
        if (id == null || nombre == null || categoria == null || precio == null) {
            return;  
        }
        
        if (id.isEmpty() || nombre.isEmpty() || categoria.isEmpty() || precio.isEmpty()) {
            JOptionPane.showMessageDialog(null, "es necesario llenar todos los campos");
            return;
        }
        if (restaurante.buscarPlato(id) != null) {
            JOptionPane.showMessageDialog(null, "ya existe un plato con ese id");
            return;
        }
        try {
            double precioPlato = Double.parseDouble(precio);
            if (precioPlato <= 0) {
                JOptionPane.showMessageDialog(null, "el precio debe ser mayor a 0");
                return;
            }
            restaurante.agregarPlato(new Plato(id, nombre, categoria, precioPlato));
            JOptionPane.showMessageDialog(null, "plato añadido correctamente");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "el precio debe ser un numero");
        }
    }

    public void modificarPlato(Restaurante restaurante) {
        String id = JOptionPane.showInputDialog("ID del plato: ");
        
        if (id == null){
            return;
        }
        
        Plato plato = restaurante.buscarPlato(id);
        if (plato == null){
            JOptionPane.showMessageDialog(null, "plato no encontrado");
            return;
        }
        
        String nombre = JOptionPane.showInputDialog("Nombre del plato: ", plato.getNombre());
        String categoria = JOptionPane.showInputDialog("Categoria del plato: ", plato.getCategoria());
        String precio = JOptionPane.showInputDialog("Precio del plato: ", plato.getPrecio());
        
        if (nombre == null || categoria == null || precio == null) {
            return;  
        }
        
        try {
            double precioPlato = Double.parseDouble(precio);
            if (nombre.isEmpty() || categoria.isEmpty() || precioPlato <= 0) {
                JOptionPane.showMessageDialog(null, "datos incompletos o precio menor a 0");
                return;
            }
            plato.setNombre(nombre);
            plato.setCategoria(categoria);
            plato.setPrecio(precioPlato);
            JOptionPane.showMessageDialog(null, "plato modificado");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "el precio debe ser un numero");
        }
        
    }

    public void eliminarPlato(Restaurante restaurante) {
        String id = JOptionPane.showInputDialog("ID del plato: ");
        
        if (id == null){
            return;
        }
        
        Plato plato = restaurante.buscarPlato(id);
        if (plato == null){
            JOptionPane.showMessageDialog(null, "plato no encontrado");
            return;
        }
        
        int confirmar = JOptionPane.showConfirmDialog(null,
                "¿Eliminar \"" + plato.getNombre() + "\"?", "Eliminar plato",
                JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            restaurante.eliminarPlato(id);
            JOptionPane.showMessageDialog(null, "plato eliminado.");
        }
    }
    
    public void consultarMenu(Restaurante restaurante) {
        if (restaurante.getMenu().isEmpty()) {
            JOptionPane.showMessageDialog(null, "el menu esta vacio");
            return;
        }
        String texto = "Menu\n\n";
        for (Plato p : restaurante.getMenu()) {
            texto += p.getId() + " - " + p.getNombre() + " (" + p.getCategoria() + ") $" + p.getPrecio() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }
}