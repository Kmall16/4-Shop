/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Cart;
import Models.Customer;
import Models.Item;
import Models.Order;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MY LAPTOP
 */
public class OrderDAO {

    DecimalFormat decimalFormat = new DecimalFormat("###.##"); //dung de format gia tien khi lay tu database
    private Connection conn;

    public OrderDAO() {
        this.conn = DB.DBConnection.getConnection();
    }

    public int newOrderID() {
        String sql = "select Max(OrderID) from [Order]";
        int maxID = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++maxID;
    }

    public void addOrder(Customer cus, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now(); //lay ngay hien tai
//        Date date = java.sql.Date.valueOf(curDate);
        String date = curDate.toString();
        String sql = "Insert into [Order] values(?,?,?,?)";
        ResultSet rs = null;
        int kq = 0;
        try {
            //add vao Order
            PreparedStatement ps = conn.prepareStatement(sql);
            int id = newOrderID();
            BigDecimal total = new BigDecimal("0");
            ps.setInt(1, id);
            ps.setInt(2, cus.getId());
            ps.setFloat(3, (float) cart.getTotalMoney());
            ps.setString(4, date);
            kq = ps.executeUpdate();
            //lay ra id cua Order vua add
            String sql1 = "select top 1 OrderID from [Order] order by OrderID desc";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            rs = ps1.executeQuery();
            //add vao OrderDetail
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "Insert into OrderDetail values(?,?,?,?)";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1, oid);
                    ps2.setInt(2, i.getProduct().getId());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setFloat(4, (float) i.getPrice() * i.getQuantity());
                    ps2.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from [Order]";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), Double.parseDouble(decimalFormat.format(rs.getFloat(3))), rs.getDate(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Order> getOrderByCustomer(Customer cus) {
        List<Order> list = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from [Order] where CustomerID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cus.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), Double.parseDouble(decimalFormat.format(rs.getFloat(3))), rs.getDate(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Item> getOrderDetail(int orderID) throws SQLException {
        List<Item> list = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from OrderDetail where OrderID=?";
        ProductDAO proDAO = new ProductDAO();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Item(proDAO.getProductById(rs.getInt(2)), rs.getInt(3), Double.parseDouble(decimalFormat.format(rs.getFloat(4)))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
