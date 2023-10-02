/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MY LAPTOP
 */
public class ProductDAO {

    DecimalFormat decimalFormat = new DecimalFormat("###.##"); //dung de format gia tien khi lay tu database
    private Connection conn;

    public ProductDAO() throws SQLException {
        conn = DB.DBConnection.getConnection();
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();

        ResultSet rs = null;
        String sql = "select * from Product";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), Double.parseDouble(decimalFormat.format(rs.getFloat(4))), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getProductById(int id) {
        ResultSet rs = null;
        String sql = "select * from Product where ProductID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), Double.parseDouble(decimalFormat.format(rs.getFloat(4))), rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int addNew(int id, String name, String type, double price, String imgURL) {
        String sql = "Insert into Product values(?,?,?,?,?)";
        int kq = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setFloat(4, (float) price);
            ps.setString(5, imgURL);
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public int delete(int id) {
        String sql = "delete from Product where ProductID=?";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

//    public int getNewID(){
//        String sql = "select count(ProductID) from Product";
//        ResultSet rs = null;
//        int kq=0;
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                kq = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return kq;
//    }
    public int getNewID() {
        List<Product> list = getAllProduct();
        int idMax = list.get(list.size() - 1).getId();
        return ++idMax;
    }

    public int update(int id, String name, String type, double price, String imgURL) {
        String sql = "Update Product set Name=?,Type=?,Price=?,ImgURL=? where ProductID=?";
        int kq = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(5, id);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setFloat(3, (float) price);
            ps.setString(4, imgURL);
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public int updateWithoutPicture(int id, String name, String type, double price) {
        String sql = "Update Product set Name=?,Type=?,Price=? where ProductID=?";
        int kq = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(4, id);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setFloat(3, (float) price);
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where Name like ? or Type like ?";
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), Double.parseDouble(decimalFormat.format(rs.getFloat(4))), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<String> listType() {
        List<String> list = new ArrayList<>();
        String sql = "select distinct [Type] from Product";
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new String(rs.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> listCombo() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where Name like ? or Type like ?";
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%Combo%");
            ps.setString(2, "%Combo%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), Double.parseDouble(decimalFormat.format(rs.getFloat(4))), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
