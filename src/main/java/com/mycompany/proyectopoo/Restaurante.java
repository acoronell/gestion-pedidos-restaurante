package com.mycompany.proyectopoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Restaurante {

    private void mostrarError(SQLException e) {
        JOptionPane.showMessageDialog(null, "error con la base de datos:\n" + e.getMessage());
    }

    // USUARIOS 

    public Usuario IniciarSesion(String user, String password) {
        String sql = "SELECT id, nombre, usuario, password, rol FROM usuario WHERE usuario = ? AND password = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id");
                    String nombre = rs.getString("nombre");
                    String usuario = rs.getString("usuario");
                    String contraseña = rs.getString("password");
                    String rol = rs.getString("rol");

                    return switch (rol) {
                        case "ADMINISTRADOR" -> new Administrador(id, nombre, usuario, contraseña);
                        case "MESERO" -> new Mesero(id, nombre, usuario, contraseña);
                        case "CAJERO" -> new Cajero(id, nombre, usuario, contraseña);
                        case "COCINERO" -> new Cocinero(id, nombre, usuario, contraseña);
                        default -> null;
                    };
                }
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
        return null;
    }

    // PLATOS 

    public String listarMenu() {
        String texto = "";
        String sql = "SELECT id, nombre, categoria, precio FROM plato ORDER BY id";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                texto += rs.getString("id") + " - " + rs.getString("nombre")
                        + " (" + rs.getString("categoria") + ") $" + rs.getDouble("precio") + "\n";
            }
        } catch (SQLException e) {
            return "error al consultar el menu: " + e.getMessage();
        }
        if (texto.isEmpty()) {
            return "el menu esta vacio";
        }
        return "MENU DEL RESTAURANTE\n\n" + texto;
    }

    public Plato buscarPlato(String id) {
        String sql = "SELECT id, nombre, categoria, precio FROM plato WHERE id = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Plato(rs.getString("id"), rs.getString("nombre"),
                            rs.getString("categoria"), rs.getDouble("precio"));
                }
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
        return null;
    }

    public boolean agregarPlato(Plato plato) {
        String sql = "INSERT INTO plato (id, nombre, categoria, precio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, plato.getId());
            ps.setString(2, plato.getNombre());
            ps.setString(3, plato.getCategoria());
            ps.setDouble(4, plato.getPrecio());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            mostrarError(e);
            return false;
        }
    }

    public boolean modificarPlato(Plato plato) {
        String sql = "UPDATE plato SET nombre = ?, categoria = ?, precio = ? WHERE id = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, plato.getNombre());
            ps.setString(2, plato.getCategoria());
            ps.setDouble(3, plato.getPrecio());
            ps.setString(4, plato.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            mostrarError(e);
            return false;
        }
    }

    public boolean platoEnUso(String id) {
        String sql = "SELECT COUNT(*) FROM detalle_pedido WHERE id_plato = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            mostrarError(e);
            return true;
        }
    }

    public boolean eliminarPlato(String id) {
        String sql = "DELETE FROM plato WHERE id = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            mostrarError(e);
            return false;
        }
    }

    //PEDIDOS
    public String generarIdPedido() {
        int numero = 0;
        String sql = "SELECT MAX(CAST(SUBSTRING(id, 4) AS UNSIGNED)) FROM pedido";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                numero = rs.getInt(1);
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
        return String.format("PED%03d", numero + 1);
    }

    public boolean agregarPedido(Pedido pedido) {
        String sqlPedido = "INSERT INTO pedido (id, mesa, estado) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_pedido (id_pedido, id_plato, cantidad) VALUES (?, ?, ?)";
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            try (PreparedStatement psPedido = con.prepareStatement(sqlPedido);
                 PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                psPedido.setString(1, pedido.getId());
                psPedido.setInt(2, pedido.getMesa());
                psPedido.setString(3, pedido.getEstado().name());
                psPedido.executeUpdate();

                for (DetallePedido d : pedido.getDetalles()) {
                    psDetalle.setString(1, pedido.getId());
                    psDetalle.setString(2, d.getPlato().getId());
                    psDetalle.setInt(3, d.getCantidad());
                    psDetalle.executeUpdate();
                }
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { }
            mostrarError(e);
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ex) { }
        }
    }

    public boolean modificarDetalles(String idPedido, ArrayList<DetallePedido> detalles) {
        String sqlBorrar = "DELETE FROM detalle_pedido WHERE id_pedido = ?";
        String sqlDetalle = "INSERT INTO detalle_pedido (id_pedido, id_plato, cantidad) VALUES (?, ?, ?)";
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            try (PreparedStatement psBorrar = con.prepareStatement(sqlBorrar);
                 PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                psBorrar.setString(1, idPedido);
                psBorrar.executeUpdate();

                for (DetallePedido d : detalles) {
                    psDetalle.setString(1, idPedido);
                    psDetalle.setString(2, d.getPlato().getId());
                    psDetalle.setInt(3, d.getCantidad());
                    psDetalle.executeUpdate();
                }
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { }
            mostrarError(e);
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ex) { }
        }
    }

    public boolean actualizarEstado(String idPedido, EstadoPedido estado) {
        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, estado.name());
            ps.setString(2, idPedido);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            mostrarError(e);
            return false;
        }
    }

    public Pedido buscarPedido(String id) {
        String sql = "SELECT mesa, estado FROM pedido WHERE id = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int mesa = rs.getInt("mesa");
                    EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                    return new Pedido(id, mesa, estado, cargarDetalles(id));
                }
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
        return null;
    }

    private ArrayList<DetallePedido> cargarDetalles(String idPedido) throws SQLException {
        ArrayList<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT d.cantidad, p.id, p.nombre, p.categoria, p.precio "
                + "FROM detalle_pedido d JOIN plato p ON d.id_plato = p.id "
                + "WHERE d.id_pedido = ? ORDER BY d.id";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Plato plato = new Plato(rs.getString("id"), rs.getString("nombre"),
                            rs.getString("categoria"), rs.getDouble("precio"));
                    detalles.add(new DetallePedido(plato, rs.getInt("cantidad")));
                }
            }
        }
        return detalles;
    }


    public ArrayList<Pedido> getPedidosPorEstado(EstadoPedido... estados) {
        ArrayList<String> ids = new ArrayList<>();
        String sql = "SELECT id FROM pedido WHERE estado = ? ORDER BY id";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            for (EstadoPedido estado : estados) {
                ps.setString(1, estado.name());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ids.add(rs.getString("id"));
                    }
                }
            }
        } catch (SQLException e) {
            mostrarError(e);
        }

        ArrayList<Pedido> pedidos = new ArrayList<>();
        for (String id : ids) {
            pedidos.add(buscarPedido(id));
        }
        return pedidos;
    }
}