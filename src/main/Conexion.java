package main;

import java.sql.*;

public class Conexion {
    static String url = "jdbc:mysql://82.197.82.62:3306/u984447967_op2024b";
    static String user = "u984447967_unipaz";
    static String pass = "estudiantesPoo2024B.*";

    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo en la conexión: " + e.getMessage());
        }
        return con;
    }


}