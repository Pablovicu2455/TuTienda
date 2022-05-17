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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ARQJH
 */
public class proveedorDao {
    Conexion cn = new Conexion();
    Connection com;
    PreparedStatement ps;
    ResultSet rs;
    public boolean registrar_Proveedor(Proveedor pr){
    String sql = "INSERT INTO proveedores (ruc, nombre,telefono,direccion, razon)VALUES (?,?,?,?,?)";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            ps.setInt(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
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
    
    //listar proveedor
    public List listar_Proveedor(){
        List <Proveedor> lista_pr = new ArrayList();
        String sql = "SELECT * FROM proveedores";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getInt("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                lista_pr.add(pr);
            }
                    
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista_pr;
    }
    
    //eliminar proveedor
    public boolean eliminar_Proveedor(int id ){
        String sql = "DELETE FROM proveedores WHERE id = ?";
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
    
    //modificar proveedor
    public boolean modificar_Proveedor(Proveedor pr){
        String sql = "UPDATE proveedores SET ruc = ?, nombre = ?, telefono = ?, direccion = ?, razon = ? WHERE id = ? ";
        try {
            ps = com.prepareStatement(sql);
            ps.setInt(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.setInt(6, pr.getId());
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
