
package com.mycompany.proyectopoo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Mesero extends Usuario {

    public Mesero() {
    }

    public Mesero(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }

    @Override
    public String mostrarMenuOpciones() {
        return "Menu mesero\n"
                + "1. Registrar pedido\n"
                + "2. Modificar pedido\n"
                + "3. Cancelar pedido\n"
                + "4. Actualizar estado del pedido\n"
                + "5. Consultar estado del pedido\n"
                + "6. Consultar pedido"
                + "7. Consultar menu"
                + "8. Salir";
    }
    
    @Override
    public void ejecutarOpcion(int opcion, Restaurante restaurante){
        
        if (opcion == 1){
            Pedido pedido = armarPedido(restaurante);
            if (pedido != null){
                registrarPedido(restaurante, pedido);
            }
        }
        
        else if (opcion == 2){
            String id = JOptionPane.showInputDialog("Id del pedido que desea modificar: ");
            if (id != null){
                ArrayList<DetallePedido> detalles = armarDetalles(restaurante);
                
                if (detalles!= null && !detalles.isEmpty()){
                    modificarPedido(restaurante, id, detalles);
                }
            }
        }
        
        else if (opcion == 3) {
            String id = JOptionPane.showInputDialog("Id del pedido a cancelar: ");
            if (id != null) {
                CancelarPedido(restaurante, id);
            }
        }
        
        else if (opcion == 4){
            String id = JOptionPane.showInputDialog("Id del pedido: ");
            String nuevo = JOptionPane.showInputDialog("Nuevo estado: \n1. En preparación\n2. Listo");
            if (id != null && nuevo != null) {
                if (nuevo.equals("1")) {
                    actualizarEstadoPedido(restaurante, id, EstadoPedido.EnPreparacion);
                } 
                else if (nuevo.equals("2")) {
                    actualizarEstadoPedido(restaurante, id, EstadoPedido.Listo);
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
                }
            }
        }  
        
        else if (opcion == 5){
            String id = JOptionPane.showInputDialog("Id del pedido: ");
            if (id != null) {
                consultarEstadoPedido(restaurante, id);
            }
        }
        
        else if (opcion == 6){
            String id = JOptionPane.showInputDialog("Id del pedido: ");
            if (id != null) {
                consultarPedido(restaurante, id);
            }
        }
        
        else if(opcion == 7){
            consultarMenu(restaurante);
        }
    }
        
                
    public void registrarPedido(Restaurante restaurante, Pedido pedido) {
        restaurante.agregarPedido(pedido);
        JOptionPane.showMessageDialog(null, "Pedido "+pedido.getId()+ "resgitado \n Total: " +pedido.calcularTotal());
    }

    public void modificarPedido(Restaurante restaurante, String id, ArrayList<DetallePedido> detalles){
        Pedido pedido = restaurante.buscarPedido(id);
        
        if (pedido == null){
            JOptionPane.showMessageDialog(null, "pedido no encontrado");
        }
        
        else if(pedido.getEstado() != EstadoPedido.Pendiente){
            JOptionPane.showMessageDialog(null, "solo se pueden modificar pedidos pendientes");
        }
        
        else{
            JOptionPane.showMessageDialog(null, "pedido modificado. El total ahora es de: "+pedido.calcularTotal());
        }
    }
    
    public void CancelarPedido(Restaurante restaurante, String id) {
        Pedido pedido = restaurante.buscarPedido(id);
        
        if (pedido == null){
            JOptionPane.showMessageDialog(null, "pedido no encontrado");
        }
        
        else if(pedido.getEstado() != EstadoPedido.Pendiente){
            JOptionPane.showMessageDialog(null, "solo se pueden modificar pedidos pendientes");
        }
        
        else{
            pedido.cambiarEstado(EstadoPedido.Cancelado);
            restaurante.quitarDePendientes(pedido);
            JOptionPane.showMessageDialog(null, "pedido cancelado");
        }
    }

    public void actualizarEstadoPedido(Restaurante restaurante, String id, EstadoPedido estado) {
        Pedido pedido = restaurante.buscarPedido(id);
        
        if (pedido == null){
            JOptionPane.showMessageDialog(null, "pedido no encontrado");
        }
        
        else if(pedido.getEstado() == EstadoPedido.Cancelado || pedido.getEstado() == EstadoPedido.Pagado) {
            JOptionPane.showMessageDialog(null, "ya no se pueden hacer cambios de estado sobre este pedido");
        }
        
        else {
            pedido.cambiarEstado(estado);
            restaurante.quitarDePendientes(pedido);
            JOptionPane.showMessageDialog(null, "estado cambiado a: " + estado);
        }
    }

    public void consultarEstadoPedido(Restaurante restaurante, String id) {
        Pedido pedido = restaurante.buscarPedido(id);
        
        if (pedido == null){
            JOptionPane.showMessageDialog(null, "pedido no encontrado");
        }
        
        else{
            JOptionPane.showMessageDialog(null, "el pedido se encuentra: "+pedido.getEstado());
        }
    }
    
    public void consultarPedido(Restaurante restaurante, String id){
        Pedido pedido = restaurante.buscarPedido(id);
        
        if (pedido == null){
            JOptionPane.showMessageDialog(null, "pedido no encontrado");
            return;
        }
        
        String texto = "Pedido " + pedido.getId() + "-Mesa " + pedido.getMesa()
                + "\nEstado: " + pedido.getEstado() + "\n\n";
        for (DetallePedido d : pedido.getDetalles()) {
            texto += d.getCantidad() + " x " + d.getPlato().getNombre()
                    + " = $" + d.calcularSubtotal() + "\n";
        }
        texto += "\nTotal: $" + pedido.calcularTotal();
        JOptionPane.showMessageDialog(null, texto);
        
    }
    
    public void consultarMenu(Restaurante restaurante){
       
    }
    
    private Pedido armarPedido(Restaurante restaurante) {
        String mesaNumero = JOptionPane.showInputDialog("Numero de mesa:");
        if (mesaNumero == null) {
            return null;
        }
        try {
            int mesa = Integer.parseInt(mesaNumero);
            ArrayList<DetallePedido> detalles = armarDetalles(restaurante);
            if (detalles == null) {
                return null;
            }
            if (detalles.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El pedido debe tener al menos un plato");
                return null;
            }
            Pedido pedido = new Pedido(restaurante.generarIdPedido(), mesa);
            for (DetallePedido d : detalles) {
                pedido.agregarDetalle(d);
            }
            return pedido;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La mesa debe ser un numero");
            return null;
        }
    }

    private ArrayList<DetallePedido> armarDetalles(Restaurante restaurante) {
        ArrayList<DetallePedido> detalles = new ArrayList<>();
        int otro = JOptionPane.YES_OPTION;

        while (otro == JOptionPane.YES_OPTION) {
            consultarMenu(restaurante);
            String idPlato = JOptionPane.showInputDialog("Id del plato: ");
            if (idPlato == null) {
                return null;
            }
            Plato plato = restaurante.buscarPlato(idPlato);
            if (plato == null) {
                JOptionPane.showMessageDialog(null, "plato no encontrado");
            } else {
                String cantidadTexto = JOptionPane.showInputDialog("cantidad: ");
                if (cantidadTexto == null) {
                    return null;
                }
                try {
                    int cantidad = Integer.parseInt(cantidadTexto);
                    if (cantidad > 0) {
                        detalles.add(new DetallePedido(plato, cantidad));
                    } else {
                        JOptionPane.showMessageDialog(null, "la cantidad debe ser mayor que 0");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "la cantidad debe ser un numero");
                }
            }
            otro = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro plato?",
                    "Pedido", JOptionPane.YES_NO_OPTION);
        }
        return detalles;
    }
}