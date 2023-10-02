/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CustomerDAO;
import DAOs.ProductDAO;
import Models.Customer;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MY LAPTOP
 */
public class CustomerController extends HttpServlet {

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
            out.println("<title>Servlet CustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerController at " + request.getContextPath() + "</h1>");
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
            CustomerDAO cusDAO = new CustomerDAO();
            String path = request.getRequestURI(); //lay duong dan
            if (path.endsWith("/customer/login")) { //neu la dang nhap chuyen sang trang index.jsp
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (path.endsWith("/customer/signup")) { //neu la dang ky thi chuyen sang trang signup.jsp
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            } else if (path.endsWith("/customer/logout")) { //neu la dang xuat thi xoa thuoc tinh session role, cookie va tro ve trang dang nhap
                Cookie[] cookies = request.getCookies();
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("customer")) {
                        cooky.setMaxAge(0);
                        cooky.setPath("/");
                        response.addCookie(cooky);
                        break;
                    }
                }
                request.getSession().removeAttribute("role"); //xoa session
                request.getSession().invalidate();

                response.sendRedirect("/customer/login");
            } else if (path.endsWith("/customer/update")) { //neu cap nhat profile thi chuyen sang trang updateCustomer.jsp
                Customer role = (Customer) request.getSession().getAttribute("role");
                request.getSession().setAttribute("roleToUpdate", role);
                request.getRequestDispatcher("/updateCustomer.jsp").forward(request, response);
            } else if (path.endsWith("/customer/delete")) { //neu xoa thi lay thong tin tu role, xoa thanh cong chuyen sang trang dang nhap, that bai chuyen ve trang danh sach san pham
                try {
                    HttpSession session = request.getSession();
                    Customer cus = (Customer) session.getAttribute("role"); //lay thong tin cus dua tren session
                    cusDAO = new CustomerDAO();
                    int kq = cusDAO.deleteCus(cus.getId());
                    if (kq != 0) {
                        request.getSession().removeAttribute("role"); //xoa session
                        request.getSession().invalidate();

                        Cookie[] cookies = request.getCookies();
                        for (Cookie cooky : cookies) {
                            if (cooky.getName().equals("customer")) {
                                cooky.setMaxAge(0);
                                cooky.setPath("/");
                                response.addCookie(cooky);
                                break;
                            }
                        }
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (path.startsWith("/customer/delete")) {
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                cusDAO = new CustomerDAO();
                int kq = cusDAO.deleteCus(id);
                Customer role = (Customer) request.getSession().getAttribute("role");
                if (kq != 0 && id == role.getId()) {
                    request.getSession().removeAttribute("role"); //xoa session
                    request.getSession().invalidate();

                    Cookie[] cookies = request.getCookies();
                    for (Cookie cooky : cookies) {
                        if (cooky.getName().equals("customer")) {
                            cooky.setMaxAge(0);
                            cooky.setPath("/");
                            response.addCookie(cooky);
                            break;
                        }
                    }
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/listCustomerToUpdate.jsp").forward(request, response);
                }
            } else if (path.endsWith("/customer/aboutUs")) {
                request.getRequestDispatcher("/aboutUs.jsp").forward(request, response);
            } else if (path.endsWith("/customer/updateCustomer")) {
                try {
                    cusDAO = new CustomerDAO();
                    List<Customer> listCus = cusDAO.getAllCustomer();
                    request.getSession().setAttribute("listCus", listCus);
                    request.getRequestDispatcher("/listCustomerToUpdate.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (path.startsWith("/customer/update")) {
                try {
                    String[] data = path.split("/");
                    String username = data[data.length - 1];
                    Customer cus = cusDAO.getCus(username);
                    request.getSession().setAttribute("roleToUpdate", cus);
                    request.getRequestDispatcher("/updateCustomer.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (path.startsWith("/customer/admin")) {
                String[] data = path.split("/");
                String username = data[data.length - 1];
                int kq = cusDAO.updateAdmin(username);
                
                cusDAO = new CustomerDAO();
                List<Customer> listCus = cusDAO.getAllCustomer();
                request.getSession().setAttribute("listCus", listCus);
                request.getRequestDispatcher("/listCustomerToUpdate.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
        String login = request.getParameter("btnLogin"); //khi nguoi dung nhan nut dang nhap
        String signup = request.getParameter("btnSignup"); //khi nguoi dung nhan nut dang ki
        String update = request.getParameter("btnUpdate"); //khi nguoi dung nhan nut cap nhat

        if (login != null && login.equals("Log In")) { //khi nguoi dung nhan nut
            try {
                String username = request.getParameter("username"); //lay usernam nguoi dung nhap
                String password = request.getParameter("password"); //lay password nguoi dung nhap
                CustomerDAO cusDAO = new CustomerDAO(); //khoi tao Customer DAO
                ProductDAO proDAO = new ProductDAO();
                Customer cus = cusDAO.login(username, password); //kiem tra co tai khoan nay chua, co thi tra ve 1 doi tuong customer
                if (cus != null) { //khi co tai khoan
                    Cookie cUser = new Cookie("customer", cus.getUserName()); //dat cookie customer voi gia tri username
                    request.getSession().setAttribute("role", cus);
//                    if (cus.isAdmin()) { //neu la admin thi doi ten thanh admin
//                        cUser = new Cookie("admin", cus.getUserName());                       
//                    }
                    String remember = request.getParameter("remember"); //kiem tra nguoi dung co an nut remember me khong
                    if (remember != null && remember.equals("me")) { //neu nguoi dung an
                        cUser.setMaxAge(60 * 60 * 24); //dat tuoi tho cookie la 1 ngay

                    } else {
                        cUser.setMaxAge(0); //xoa cookie khi nguoi dung khong an remember
                    }
                    cUser.setPath("/");
                    response.addCookie(cUser);//them cookie
                    List<Product> combo = proDAO.listCombo();
                    request.getSession().setAttribute("listC", combo);
                    response.sendRedirect("/product/home"); //tro ve trang show san pham
                } else {
                    request.getSession().setAttribute("loginErr", "Username or Password is incorrect!");
                    response.sendRedirect("/customer/login"); //tro ve trang dang nhap
                }

            } catch (SQLException ex) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (signup != null && signup.equals("Sign Up")) {
            try {
                String fullname = request.getParameter("fullname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String gender = request.getParameter("gender");
                String address = request.getParameter("address");
//                Boolean isAdmin = Boolean.parseBoolean(request.getParameter("role"));
                CustomerDAO cusDAO = new CustomerDAO();
                Boolean err = false;
                if (cusDAO.checkUserNameExist(username)) { //kiem tra username dang ki chua
                    request.setAttribute("usernameErr", "this username have been registed");
                    err = true;
                }
                if (cusDAO.checkPhoneExist(phone)) { //kiem tra so dien thoai dang ki chua
                    request.setAttribute("phoneErr", "this phone number have been registed");
                    err = true;
                }
                if (cusDAO.checkEmailExist(email)) { //kiem tra email dang ki chua
                    request.setAttribute("emailErr", "this email have been registed");
                    err = true;
                }
                if (err) { //neu có loi nào thi bat dang ki lai kèm thông báo lõi
                    request.getRequestDispatcher("/signup.jsp").forward(request, response);
                }

                Customer newCus
                        = cusDAO.addCus(fullname, username, password, phone, email, gender, address, false);
                if (newCus == null) { //nêu thêm mói thát bai thi dang kí lai
                    response.sendRedirect("/customer/signup");
                } else { //dang kí thành công thì chuyen sang trang dang nhâp
                    response.sendRedirect("/customer/login");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (update != null && update.equals("Update")) {
            try {
                String fullname = request.getParameter("fullname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String gender = request.getParameter("gender");
                String address = request.getParameter("address");
                Boolean isAdmin = Boolean.parseBoolean(request.getParameter("role"));
                CustomerDAO cusDAO = new CustomerDAO();
                HttpSession session = request.getSession();
                Customer cus = (Customer) session.getAttribute("roleToUpdate"); //lay thong tin cus dua tren session
                Boolean err = false;

                if (!cus.getUserName().equals(username)) {// neu thay doi username thi kiem tra co trung voi username khac khong
                    if (cusDAO.checkUserNameExist(username)) {
                        request.setAttribute("usernameErr", "This username have been registed");//bao trung
                        err = true;
                    }
                }
                if (!cus.getPhone().equals(phone)) {// neu thay doi phone thi kiem tra co trung voi phone khac khong
                    if (cusDAO.checkPhoneExist(phone)) {
                        request.setAttribute("phoneErr", "This phone number have been registed");//bao trung
                        err = true;
                    }
                }

                if (!cus.getEmail().equals(email)) {// neu thay doi email thi kiem tra co trung voi email khac khong
                    if (cusDAO.checkEmailExist(email)) {
                        request.setAttribute("emailErr", "This email have been registed");//bao trung
                        err = true;
                    }
                }
                if (err) {
                    request.getRequestDispatcher("/updateCustomer.jsp").forward(request, response);
                }
                String oldPassFromKey = request.getParameter("oldPassword"); //lay password cu tu nguoi dung nhap de update
                String oldPassInDB = cus.getPassword(); //pass cu khi nguoi dung dang ki
                if (getMd5(oldPassFromKey).equals(oldPassInDB)) { //khi nhap pass cu dung moi update
                    Customer newCus = null;
                    if (password.equals("")) {
                        newCus = cusDAO.updateCus(cus.getId(), fullname, username, oldPassFromKey, phone, email, gender, address, isAdmin);
                    } else {
                        newCus = cusDAO.updateCus(cus.getId(), fullname, username, password, phone, email, gender, address, isAdmin);
                    }

                    if (newCus == null) {
                        response.sendRedirect("/customer/update");//bat update lai neu qua trinh update loi
                    } else {
                        Customer role = (Customer) request.getSession().getAttribute("role");
                        if (role.getUserName().equals(cus.getUserName())) {// neu update in4 cua nguoi dang cap nhat thi set lai thong tin hien thi
                            request.getSession().setAttribute("role", newCus); //tao lai session moi
                        }
                        response.sendRedirect("/product/list"); //tro ve trang dang nhap
                    }
                } else {
                    request.setAttribute("passwordErr", "This old password is not correct");
                    request.getRequestDispatcher("/updateCustomer.jsp").forward(request, response);//update lai khi nhap mat khau cu sai
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }

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

    public String getMd5(String input) { //dung de ma hoa theo md5
        try {
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
