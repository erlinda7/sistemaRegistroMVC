/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;

public class ConexionBBDD {

    Connection miConexion;

    public Connection Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemamvc?useTimezone=true&serverTimezone=America/La_Paz", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return miConexion;
    }
    /*
    public static void main(String[] args) {
        ConexionBBDD c=new ConexionBBDD();
        c.Conexion();
    }
     */
}
