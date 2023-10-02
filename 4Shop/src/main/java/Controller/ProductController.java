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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MY LAPTOP
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, //1MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 100//100MB
)
public class ProductController extends HttpServlet {

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
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
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
            ProductDAO proDAO = new ProductDAO();
            List<Product> list = proDAO.getAllProduct(); //tao list product tù list cua phuong thúc getAllProduct
            if (path.endsWith("/product/list")) { //neu can danh sach thi tao attribute listP de hien thi va chuyen sang list.jsp
                request.getSession().setAttribute("listP", list);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
            } else if (path.endsWith("/product/addnew")) { //neu càn tao mói san pham thi sang addNewProduct.jsp
                request.getRequestDispatcher("/addNewProduct.jsp").forward(request, response);
            } else if (path.startsWith("/product/delete")) { //neu càn xóa thi xoa và tao moi listP sau khi xoa de hien thi
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                int kq = proDAO.delete(id);

                list = proDAO.getAllProduct(); //tai lai danh sách áo sau khi xóa
                request.getSession().setAttribute("listP", list);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
            } else if (path.endsWith("/product/updatePage")) {
//                request.getSession().setAttribute("listP", list);
                request.getRequestDispatcher("/listToUpdate.jsp").forward(request, response);
            } else if (path.startsWith("/product/update")) {
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                Product proToUpdate = proDAO.getProductById(id);
                request.setAttribute("productToUpdate", proToUpdate);
                request.getRequestDispatcher("/updateProduct.jsp").forward(request, response);
            }else if (path.startsWith("/product/type")) {
                 String[] data = path.split("/");
                String type = data[data.length - 1];
                
                String newType = URLDecoder.decode(type, StandardCharsets.UTF_8.toString()); //loai bo %20
                
                list = proDAO.searchByName(newType);
                request.getSession().setAttribute("listP", list);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
            }else if (path.endsWith("/product/home")) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
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
        String location = "\\images\\";
        String addnew = request.getParameter("btnAddnew");
        String update = request.getParameter("btnUpdate");
        String search = request.getParameter("btnSearch");

        if (addnew != null && addnew.equals("Add New")) {
            try {
                int ketqua = 0;
                String name = request.getParameter("productName");
                String type = request.getParameter("productType");
                double price = Double.parseDouble(request.getParameter("productPrice"));
//                String imgURL = request.getPart("productImg").getSubmittedFileName();               
                ProductDAO proDAO = new ProductDAO();

                String pictureName = proDAO.getNewID() + ".jpg"; //ten anh lay (so san pham+1).jpg
                System.out.println(proDAO.getNewID());
                String addressPicture = location + pictureName; //dia chi luu vao database = \images\(so san pham+1).jpg            
                ketqua = proDAO.addNew(proDAO.getNewID(), name, type, price, addressPicture);
                if (ketqua == 0) {
                    response.sendRedirect("/product/addnew");
                } else {
                    uploadFile(pictureName, request);
                    response.sendRedirect("/product/list");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (update != null && update.equals("Update")) {
            try {
                int ketqua = 0;
                int id = Integer.parseInt(request.getParameter("productID"));
                String name = request.getParameter("productName");
                String type = request.getParameter("productType");
                double price = Double.parseDouble(request.getParameter("productPrice"));
                String imgURL = request.getPart("productImg").getSubmittedFileName();
                ProductDAO proDAO = new ProductDAO();

                String pictureName = id + ".jpg"; //lay anh moi de anh cux
                String addressPicture = location + pictureName;

                if (imgURL.equals("")) { //khi khong chon imgURL tra ve "", update khi khong chon anh
                    ketqua = proDAO.updateWithoutPicture(id, name, type, price);
                } else {//update khi có anh
                    uploadFile(pictureName, request);//co anh thi update anh
                    ketqua = proDAO.update(id, name, type, price, addressPicture);
                }

                if (ketqua == 0) {
                    response.sendRedirect("/product/updatePage");
                } else {
//                    response.sendRedirect("/product/list");
                    response.sendRedirect("/product/list");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (search != null && search.equals("Search")) {
            try {
                ProductDAO proDAO = new ProductDAO();
                String txtSearch = request.getParameter("txtSearch");
                List list = proDAO.searchByName(txtSearch);
                request.getSession().setAttribute("listP", list);
                request.setAttribute("oldSearch", txtSearch);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void uploadFile(String picture, HttpServletRequest request) {
        String relativePath = "images\\"; //ten folder chua anh
        String realPath = request.getServletContext().getRealPath("");
        String[] data = realPath.split(File.separator + "\\");
        int position = 0;//vi tri cua target
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("target")) {
                position = i;
                break;
            }
        }
        String finalPath = "";
        for (int i = 0; i < position; i++) {
            finalPath += data[i] + File.separator;
        }
        finalPath += "src\\main\\webapp\\" + relativePath + picture;

//        String RootDestination = "D:\\FPT_SE1702\\Summer2023\\PRJ301\\LAB03\\lab03_model2\\src\\main\\webapp\\";
//        String finalDestiation = RootDestination + relativePath + picture;
        try ( InputStream inS = request.getPart("productImg").getInputStream()) {
            OutputStream outS, outSTarget;
            outS = new FileOutputStream(finalPath);
            byte[] buffer = new byte[1024];
            int bytes;
            while ((bytes = inS.read(buffer)) != -1) {
                outS.write(buffer, 0, bytes);
            }
            outS.close();
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
