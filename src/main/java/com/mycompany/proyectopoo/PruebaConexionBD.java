package com.mycompany.proyectopoo;

import java.sql.SQLException;
public class PruebaConexionBD {
    public static void main(String[] args) {
        try {
            Conexion.abrirConexion();
            System.out.println("Se conecto a MySQL");
            Conexion.cerrarConexion();
            System.out.println("Se desconecto de MySQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada: " + ex);
        } catch (SQLException ex) {
            System.out.println("Excepcion SQL: " + ex);
        }
    }   
}