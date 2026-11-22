
package com.mycompany.proyectopoo;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        // conectar con MySQL
        try {
            Conexion.abrirConexion();
            JOptionPane.showMessageDialog(null,
                    "Conexion con la base de datos exitosa");

            Restaurante restaurante = new Restaurante();

            int opcionPrincipal = 0;

            // menu principal
            while (opcionPrincipal != 2) {

                String entradaPrincipal = JOptionPane.showInputDialog(
                        "MENU PRINCIPAL\n"
                        + "1. Iniciar sesion\n"
                        + "2. Salir");

                if (entradaPrincipal == null) {
                    break;
                }

                try {
                    opcionPrincipal = Integer.parseInt(entradaPrincipal);

                    if (opcionPrincipal == 1) {

                        Usuario usuario = null;

                        // inicio de sesion
                        while (usuario == null) {

                            String user = JOptionPane.showInputDialog("usuario: ");
                            if (user == null) {
                                break;
                            }

                            String password = JOptionPane.showInputDialog("contraseña: ");
                            if (password == null) {
                                break;
                            }

                            usuario = restaurante.IniciarSesion(user, password);

                            if (usuario == null) {
                                JOptionPane.showMessageDialog(null,
                                        "Usuario o contraseña incorrectos");
                            }
                        }

                        if (usuario != null) {

                            JOptionPane.showMessageDialog(null,
                                    "Bienvenido " + usuario.getNombre());

                            // menu segun el tipo de usuario
                            int opcion = -1;
                            int opcionSalir = usuario.getOpcionSalir();

                            while (opcion != opcionSalir) {

                                String entrada = JOptionPane.showInputDialog(
                                        usuario.mostrarMenuOpciones());

                                if (entrada == null) {
                                    break;
                                }

                                try {
                                    opcion = Integer.parseInt(entrada);

                                    if (opcion == opcionSalir) {
                                        JOptionPane.showMessageDialog(null,
                                                "Sesion cerrada");
                                    } else {
                                        usuario.ejecutarOpcion(opcion, restaurante);
                                    }

                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "debe ingresar un numero valido");
                                }
                            }
                        }

                    } else if (opcionPrincipal == 2) {

                        JOptionPane.showMessageDialog(null,
                                "Programa finalizado");

                    } else {

                        JOptionPane.showMessageDialog(null,
                                "opcion incorrecta");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "debe ingresar un numero valido");
                }
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "no se encontro el controlador JDBC: " + e.getMessage());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "eror con la base de datos: " + e.getMessage());

        } finally {
            try {
                Conexion.cerrarConexion();
            } catch (SQLException e) {
                System.out.println("error al cerrar conexion: " + e.getMessage());
            }
        }
    }
}
