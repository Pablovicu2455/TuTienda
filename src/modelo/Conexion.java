/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ARQJH
 */
public class Conexion {
    
    //conectamos a sql
    private Connection com;
    public Connection getConnection(){
        try {
            String myDB = "jdbc:mysql://localhost:3306/sistemadeventas?serverTimezone=UTC";
            setCom(DriverManager.getConnection(myDB,"rekcah","rekcah"));
            return getCom();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * @return the com
     */
    public Connection getCom() {
        return com;
    }

    /**
     * @param com the com to set
     */
    public void setCom(Connection com) {
        this.com = com;
    }
}
