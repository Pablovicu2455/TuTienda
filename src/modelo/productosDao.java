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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ARQJH
 */
public class productosDao {

    Conexion cn = new Conexion();
    Connection com;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar_Productos(Productos pr) {
        String sql = "INSERT INTO productos (codigo, nombre,proveedor,stock, precio)VALUES (?,?,?,?,?)";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            ps.setString(1, pr.getCodigo());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getProveedor());
            ps.setInt(4, pr.getStock());
            ps.setDouble(5, pr.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
        /*finally {
            try {
                com.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
    }

    public void consultarProveedor(JComboBox proveedor) {
        String sql = "SELECT nombre FROM proveedores";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    //listar productos
    public List listar_Productos() {
        List<Productos> lista_pdr = new ArrayList();
        String sql = "SELECT * FROM productos";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pdr = new Productos();
                pdr.setId(rs.getInt("id"));
                pdr.setCodigo(rs.getString("Codigo"));
                pdr.setNombre(rs.getString("nombre"));
                pdr.setProveedor(rs.getString("proveedor"));
                pdr.setStock(rs.getInt("stock"));
                pdr.setPrecio(rs.getDouble("precio"));
                lista_pdr.add(pdr);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista_pdr;
    }

    //eliminar producto
    public boolean eliminar_Producto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try {
            ps = com.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                com.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    //modificar producto
    public boolean modificar_Producto(Productos pr) {
        String sql = "UPDATE productos SET codigo = ?, nombre = ?, proveedor = ?, stock = ?, precio = ? WHERE id = ? ";
        try {
            ps = com.prepareStatement(sql);
            ps.setString(1, pr.getCodigo());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getProveedor());
            ps.setInt(4, pr.getStock());
            ps.setDouble(5, pr.getPrecio());
            ps.setInt(6, pr.getId());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                com.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public Productos buscar_Productos(String cod) {
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo =?";
        try {
            com = cn.getConnection();
            ps = com.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if(rs.next()){
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }

}
