
package com.mycompany.proyectopoo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cocinero extends Usuario{

    public Cocinero() {
    }

    public Cocinero(String id, String nombre, String user, String password) {
        super(id, nombre, user, password);
    }
    
    @Override
    public String mostrarMenuOpciones() {
        return "Menu cocinero\n"
                + "1. Consultar estado pedido\n"
                + "2. Consultar pedidos pendientes\n"
                + "3. Salir\n";
    }
    
    @Override
    public void ejecutarOpcion(int opcion, Restaurante restaurante){
        if (opcion == 1){
            String id = JOptionPane.showInputDialog("Id del pedido: ");
            
            if(id!= null){
                consultarEstadoPedido(restaurante, id);
            }
        }
        
        else if (opcion == 2){
            consultarPedidosPendientes(restaurante);
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
    
    public void consultarPedidosPendientes(Restaurante restaurante) {
        ArrayList<Pedido> pedidos = restaurante.getPedidosPorEstado(
                EstadoPedido.PENDIENTE, EstadoPedido.EN_PREPARACION);

        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "no hay pedidos por preparar");
            return;
        }
        String texto = "Pedidos por preparar\n\n";
        for (Pedido p : pedidos) {
            texto += "pedido " + p.getId() + " - mesa " + p.getMesa()
                    + " (" + p.getEstado() + ")\n";
            for (DetallePedido d : p.getDetalles()) {
                texto += "   " + d.getCantidad() + " x " + d.getPlato().getNombre() + "\n";
            }
            texto += "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    @Override
    public int getOpcionSalir() {
        return 3;
    }
}
