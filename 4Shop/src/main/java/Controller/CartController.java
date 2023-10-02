/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CustomerDAO;
import DAOs.ProductDAO;
import Models.Cart;
import Models.Customer;
import Models.Item;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MY LAPTOP
 */
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getRequestURI();
            CustomerDAO cusDAO = new CustomerDAO();
            ProductDAO proDAO = new ProductDAO();
            if (path.endsWith("/cart/list")) {
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            } else if (path.startsWith("/cart/add")) {
                String[] data = path.split("/");
                int productId = Integer.parseInt(data[data.length - 1]); //lay id san pham
                Customer role = (Customer) request.getSession().getAttribute("role"); //lay tai khoan nguoi dung hien tai
                Cart cart = null; //khoi tao gio hang
                Object o = request.getSession().getAttribute("cart");
                if (o != null) {
                    cart = (Cart) o; //có giỏ hàng thì dùng lại giỏ hàng
                } else {
                    cart = new Cart(); //không có giỏ hàng thì tạo mới
                }
                Product p = proDAO.getProductById(productId);
                Item t = new Item(p, 1, p.getPrice());
                cart.addItem(t);
                List<Item> listItem = cart.getItems();
                request.getSession().setAttribute("cart", cart);
                request.getSession().setAttribute("size", listItem.size());
                List<Product> list = proDAO.getAllProduct();
                request.getSession().setAttribute("listP", list);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
            } else if (path.startsWith("/cart/down")) {
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                Cart cart = null; //khoi tao gio hang
                Object o = request.getSession().getAttribute("cart");
                if (o != null) {
                    cart = (Cart) o;
                } else {
                    cart = new Cart();
                }
                if (cart.getQuantityById(id) <= 1) {
                    cart.removeItem(id);
                } else {
                    Product p = proDAO.getProductById(id);
                    double price = p.getPrice() * 1.2;
                    Item t = new Item(p, -1, price);
                    cart.addItem(t);
                }
                List<Item> list = cart.getItems();
                request.getSession().setAttribute("cart", cart);
                request.getSession().setAttribute("size", list.size());
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            } else if (path.startsWith("/cart/up")) {
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                Cart cart = null; //khoi tao gio hang
                Object o = request.getSession().getAttribute("cart");
                if (o != null) {
                    cart = (Cart) o;
                } else {
                    cart = new Cart();
                }
                Product p = proDAO.getProductById(id);
                double price = p.getPrice() * 1.2;
                Item t = new Item(p, 1, price);
                cart.addItem(t);
                List<Item> list = cart.getItems();
                request.getSession().setAttribute("cart", cart);
                request.getSession().setAttribute("size", list.size());
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Return = request.getParameter("btnReturn");
        if (Return != null && Return.equals("Return Item")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Cart cart = null; //khoi tao gio hang
            Object o = request.getSession().getAttribute("cart");
            if (o != null) {
                cart = (Cart) o;
            } else {
                cart = new Cart();
            }
            cart.removeItem(id);
            List<Item> list = cart.getItems();
            request.getSession().setAttribute("cart", cart);
            request.getSession().setAttribute("size", list.size());
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
