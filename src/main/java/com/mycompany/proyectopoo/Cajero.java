
package com.mycompany.proyectopoo;

import javax.swing.JOptionPane;

public class Cajero extends Usuario {

    public Cajero() {
    }

    public Cajero(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }
    
    @Override
    public String mostrarMenuOpciones() {
        return "Menu cajero\n"
                + "1. Consultar estado pedido\n"
                + "2. Cobrar pedido\n"
                + "3. Consultar pedido por cobrar\n"
                + "4. Salir\n";
    }
    @Override
    public void ejecutarOpcion(int opcion, Restaurante restaurante){
        if (opcion == 1){
            String id = JOptionPane.showInputDialog("Id del pedido: ");
            if (id != null) {
              consultarEstadoPedido(restaurante, id);
            }  
        }
        
        else if (opcion == 2){
            String id = JOptionPane.showInputDialog("Id del pedido: ");
            if (id != null){
            cobrarPedido(restaurante, id);
            }   
        }
        
        else if (opcion == 3){
            consultarPedidoPorCobrar(restaurante);
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
     
    public void cobrarPedido(Restaurante restaurante, String id) {
        Pedido pedido = restaurante.buscarPedido(id);

        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "pedido no encontrado.");
            return;
        }
        if (pedido.getEstado() == EstadoPedido.Pagado) {
            JOptionPane.showMessageDialog(null, "este pedido ya fue pagado");
            return;
        }
        if (pedido.getEstado() != EstadoPedido.Listo) {
            JOptionPane.showMessageDialog(null,
                    "este pedido aun no se puede cobrar. Estado actual: " + pedido.getEstado());
            return;
        }

        String forma = JOptionPane.showInputDialog(
                "Total a pagar: $" + pedido.calcularTotal()
              + "\n\nForma de pago:\n1. Efectivo\n2. Tarjeta");
        if (forma == null) {
            return;
        }
        String nombreForma;
        if (forma.equals("1")) {
            nombreForma = "Efectivo";
        } 
        
        else if (forma.equals("2")) {
            nombreForma = "Tarjeta";
        } 
        
        else {
            JOptionPane.showMessageDialog(null, "Opcion no valida");
            return;
        }

        pedido.cambiarEstado(EstadoPedido.Pagado);

        String factura = "factura\n"
                + "Pedido: " + pedido.getId() + "   Mesa: " + pedido.getMesa() + "\n\n";
        for (DetallePedido d : pedido.getDetalles()) {
            factura += d.getCantidad() + " x " + d.getPlato().getNombre()
                    + " = $" + d.calcularSubtotal() + "\n";
        }
        factura += "\nTotal: $" + pedido.calcularTotal()
                + "\nForma de pago: " + nombreForma
                + "\nCajero: " + getNombre();
        JOptionPane.showMessageDialog(null, factura);
    }

    public void consultarPedidoPorCobrar(Restaurante restaurante) {
        String texto = "";

        for (Pedido p : restaurante.getTodosLosPedidos()) {
            if (p.getEstado() == EstadoPedido.PendienteDePago) {
            texto += p.getId() + " - Mesa " + p.getMesa()
                    + " - $" + p.calcularTotal() + "\n";
            }
        }

        if (texto.isEmpty()) {
            texto = "No hay pedidos por cobrar.";
        }
    JOptionPane.showMessageDialog(null, "Pedidos por cobrar: " + texto);    
    }
}
