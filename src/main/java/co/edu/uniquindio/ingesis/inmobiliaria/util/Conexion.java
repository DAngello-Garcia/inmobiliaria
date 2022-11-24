package co.edu.uniquindio.ingesis.inmobiliaria.util;

import java.sql.*;

public class Conexion {
    public Connection getConexion() {
        String url = "jdbc:postgresql://172.17.0.4:5432/inmobiliaria";
        String username = "postgres";
        String password = "password";

        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
