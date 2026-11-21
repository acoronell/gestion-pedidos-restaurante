package com.mycompany.proyectopoo;

import java.sql.*;
public class Conexion {
   // La conexión a la BD
    private static Connection con = null;

    // El driver de MySQL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // El url de la BD, se especifica el nombre del host y el de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante";
    
    // Abrir la conexión con la base de datos
    public static void abrirConexion() throws ClassNotFoundException, SQLException{
        // Se realiza la conexion
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, "root", "root");
    }
    
    // Cerrar la conexión
    public static void cerrarConexion() throws SQLException {
        con.close();
        con = null;
    }
}
