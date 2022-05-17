/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ARQJH
 */
public class clienteDao {

    //requerimos la conexion 
    Conexion cn = new Conexion();
    Connection com;
    PreparedStatement ps;
    ResultSet rs;
    //creamos nuestro metodo para registrar 
    public boolean registro_Cliente(Cliente cl) {
        String sql = "INSERT INTO clientes (ruc, nombre , telefono , direccion , razon)VALUES (?,?,?,?,?)";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            ps.setString(1, cl.getRuc());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                com.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    //listar clientes 
    public List listar_Cliente(){
        List <Cliente> lista_cl = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setRuc(rs.getString("ruc"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
                lista_cl.add(cl);
            }
                    
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista_cl;
    }
    
    //eliminar cliente
    public boolean eliminar_Cliente(int id ){
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            ps = com.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                com.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    //modificar cliente
    public boolean modificar_Cliente(Cliente cl){
        String sql = "UPDATE clientes SET ruc = ?, nombre = ?, telefono = ?, direccion = ?, razon = ? WHERE id = ? ";
        try {
            ps = com.prepareStatement(sql);
            ps.setString(1, cl.getRuc());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.setInt(6, cl.getId());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                com.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
