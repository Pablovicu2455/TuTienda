/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ARQJH
 */
public class _loginDao {
    
    
    
    //conexxion sql
    private Connection com;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion cn;

    public _loginDao() {
        this.cn = new Conexion();
    }
    
    public user_Login log(String correo, String pass){
        user_Login l = new user_Login();
        String sql = "SELECT * FROM usuario WHERE correo = ? AND pass = ?";
        try {
            setCom(getCn().getConnection());
            setPs(getCom().prepareStatement(sql));
            getPs().setString(1, correo );
            getPs().setString(2, pass);
            getPs().executeQuery();
            setRs(getPs().executeQuery()); 
            if (getRs().next()) {
                l.setId(getRs().getInt("id"));
                l.setNombre(getRs().getNString("nombre"));
                l.setCorreo(getRs().getString("correo"));
                l.setPass(getRs().getString("pass"));
            } 
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
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

    /**
     * @return the ps
     */
    public PreparedStatement getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    /**
     * @return the cn
     */
    public Conexion getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Conexion cn) {
        this.cn = cn;
    }
}
