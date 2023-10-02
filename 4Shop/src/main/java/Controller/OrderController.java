/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.OrderDAO;
import Models.Cart;
import Models.Customer;
import Models.Item;
import Models.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MY LAPTOP
 */
public class OrderController extends HttpServlet {

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
            out.println("<title>Servlet OrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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
        String path = request.getRequestURI();
        OrderDAO ordDAO = new OrderDAO();
        if (path.endsWith("/order/checkout")) {
            Customer role = (Customer) request.getSession().getAttribute("role");
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (role != null) {
                if (cart != null) {
                    ordDAO.addOrder(role, cart);
                    request.getSession().removeAttribute("cart");
                    request.getSession().setAttribute("size", 0);
                    response.sendRedirect("/product/home");
                } else {
                    response.sendRedirect("/product/home");
                }
            } else {
                response.sendRedirect("/customer/login");
            }

        }else if(path.endsWith("/order/list")){
            Customer role = (Customer) request.getSession().getAttribute("role");
            if(role!=null){
                List<Order> listOrd = ordDAO.getOrderByCustomer(role);
            request.getSession().setAttribute("listOrd", listOrd);
            request.getRequestDispatcher("/order.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            
        }else if(path.startsWith("/order/detail")){
            try {
                String[] data = path.split("/");
                int orderID = Integer.parseInt(data[data.length-1]);
                List<Item> listDetail = ordDAO.getOrderDetail(orderID);
                request.getSession().setAttribute("orderID", orderID);
                request.getSession().setAttribute("listDetail", listDetail);
                request.getRequestDispatcher("/orderDetail.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(path.endsWith("/order/allList")){
            List<Order> listAll = ordDAO.getAllOrder();
            request.getSession().setAttribute("listOrd", listAll);
            request.getRequestDispatcher("/order.jsp").forward(request, response);
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
        processRequest(request, response);
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
