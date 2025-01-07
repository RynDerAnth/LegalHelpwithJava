        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import models.Article;
import models.Konsultan;
import models.Post;
import models.Transaction;
import models.User;


@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if (session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String menu = request.getParameter("menu");
        
        if (menu == null || menu.isEmpty()) {
            response.sendRedirect("admin?menu=view");
            return;
        }

        User userModel = new User();
        Transaction transactionModel = new Transaction();
        Konsultan consultantModel = new Konsultan();
        Post postModel = new Post();
        Article articleModel = new Article();

        if ("view".equals(menu)) {
            ArrayList<User> users = userModel.get();
            int userCount = userModel.userCount();
            int postCount = postModel.postCount();
            int consultCount = consultantModel.konsultanCount();
            int articleCount = articleModel.articleCount();
            
            request.setAttribute("users", users);
            request.setAttribute("uCount", userCount);
            request.setAttribute("pCount", postCount);
            request.setAttribute("cCount", consultCount);
            request.setAttribute("aCount", articleCount);
            request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
            
        } else if ("cView".equals(menu)) {
            ArrayList<Konsultan> consultants = consultantModel.showAll();
            
            request.setAttribute("consultants", consultants);
            request.getRequestDispatcher("/admin/consultantlist.jsp").forward(request, response);
            
        } else if ("pView".equals(menu)) {
            ArrayList<Post> posts = postModel.get();
            
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/admin/feeds.jsp").forward(request, response);
            
        } else if ("tView".equals(menu)) {
            ArrayList<Transaction> transactions = transactionModel.get();
            
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("/admin/transaction.jsp").forward(request, response);
            
        } else if ("aView".equals(menu)) {
            ArrayList<Article> articles = articleModel.get();
            
            request.setAttribute("articles", articles);
            request.getRequestDispatcher("/admin/article.jsp").forward(request, response);
        
        }else if ("addC".equals(menu)) {
            request.getRequestDispatcher("/admin/createconsultant.jsp").forward(request, response);

        } else if ("addA".equals(menu)) {
            request.getRequestDispatcher("/admin/createarticle.jsp").forward(request, response);

        } else if ("editC".equals(menu)) {
            String id = request.getParameter("id");
            Konsultan consultant = consultantModel.find("id", id);
            if (consultant != null) {
                request.setAttribute("consultant", consultant);
                request.getRequestDispatcher("/admin/editconsultant.jsp").forward(request, response);
            } else {
                response.sendRedirect("admin?menu=cView");
            }

        } else if ("editA".equals(menu)) {
            String id = request.getParameter("id");
            Article article = articleModel.find("id", id);
            if (article != null) {
                request.setAttribute("article", article);
                request.getRequestDispatcher("/admin/editarticle.jsp").forward(request, response);
            } else {
                response.sendRedirect("admin?menu=aView");
            }
        } else if ("tView".equals(menu)) {
            String id = request.getParameter("id");
            Transaction transaction = transactionModel.find("id", id);
            if (transaction != null) {
                request.setAttribute("transaction", transaction);
                request.getRequestDispatcher("/admin/transaction.jsp").forward(request, response);
            } else {
                response.sendRedirect("admin?menu=tView");
            }
        }
        else {
            response.sendRedirect("admin?menu=aView");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String action = request.getParameter("action");
        String next = "";
        User userModel = new User();
        Konsultan consultantModel = new Konsultan();
        Post postModel = new Post();
        Article articleModel = new Article();

        if ("addC".equals(action)) {
            String name = request.getParameter("nama");
            double price = Double.parseDouble(request.getParameter("harga"));
            String address = request.getParameter("alamat");
            String bio = request.getParameter("bio");
            
            String uploadPath = getServletContext().getRealPath("") + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String profilePath = consultantModel.getProfile_path(); // Path default
            Part filePart = request.getPart("profile_path");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                String fullPath = uploadPath + File.separator + fileName; // Path absolut
                filePart.write(fullPath); // Simpan file ke folder uploads
                profilePath = fileName; // Path relatif untuk disimpan ke DB
            }

            consultantModel.setName(name);
            consultantModel.setPrice(price);
            consultantModel.setAddress(address);
            consultantModel.setBio(bio);
            consultantModel.setProfile_path(profilePath);
            consultantModel.setAddress(address);
            consultantModel.insert();
            next = "admin?menu=cView";

        } else if ("editC".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("nama");
            double price = Double.parseDouble(request.getParameter("harga"));
            String address = request.getParameter("alamat");
            String bio = request.getParameter("bio");
            
            String uploadPath = getServletContext().getRealPath("") + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String profilePath = consultantModel.getProfile_path(); // Path default
            Part filePart = request.getPart("profile_path");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                String fullPath = uploadPath + File.separator + fileName; // Path absolut
                filePart.write(fullPath); // Simpan file ke folder uploads
                profilePath = fileName; // Path relatif untuk disimpan ke DB
            }

            consultantModel.setId(id);
            consultantModel.setName(name);
            consultantModel.setPrice(price);
            consultantModel.setAddress(address);
            consultantModel.setBio(bio);
            consultantModel.setProfile_path(profilePath);
            consultantModel.update();
            next = "admin?menu=cView";

        } else if (action.equals("deleteC")) {
            int id = Integer.parseInt(request.getParameter("id"));
            consultantModel.setId(id);
            consultantModel.delete();
            next = "admin?menu=cView";
            
        } else if ("addA".equals(action)) {
            String picture_path = request.getParameter("picture_path");
            String headline = request.getParameter("headline");
            String content = request.getParameter("content");

                // Path untuk folder uploads (di dalam direktori aplikasi)
                String uploadPath = getServletContext().getRealPath("") + "uploads";
                File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String picturePath = articleModel.getPicture_path() ; // Path default
            Part filePart = request.getPart("picture_path");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                String fullPath = uploadPath + File.separator + fileName; // Path absolut
                filePart.write(fullPath); // Simpan file ke folder uploads
                picturePath = fileName; // Path relatif untuk disimpan ke DB
            }
                      
            articleModel.setPicture_path(picturePath);
            articleModel.setHeadline(headline);
            articleModel.setContent(content);
            
            articleModel.insert();
            next = "admin?menu=aView";

        } else if ("editA".equals(action)) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            String picture_path = request.getParameter("picture_path");
            String headline = request.getParameter("headline");
            String content = request.getParameter("content");

                // Path untuk folder uploads (di dalam direktori aplikasi)
                String uploadPath = getServletContext().getRealPath("") + "uploads";
                File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String picturePath = articleModel.getPicture_path() ; // Path default
            Part filePart = request.getPart("picture_path");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                String fullPath = uploadPath + File.separator + fileName; // Path absolut
                filePart.write(fullPath); // Simpan file ke folder uploads
                picturePath = fileName; // Path relatif untuk disimpan ke DB
            }
            
            articleModel.setId(id);
            articleModel.setPicture_path(picturePath);
            articleModel.setHeadline(headline);
            articleModel.setContent(content);

            articleModel.update();
            next = "admin?menu=aView";

        } else if (action.equals("deleteA")) {
            int id = Integer.parseInt(request.getParameter("id"));
            articleModel.setId(id);
            articleModel.delete();
            next = "admin?menu=aView";
            
        } else if (action.equals("deleteU")) {
            int id = Integer.parseInt(request.getParameter("id"));
            userModel.setId(id);
            userModel.delete();
            next = "admin?menu=view";
            
        } else if (action.equals("deleteP")) {
            int id = Integer.parseInt(request.getParameter("id"));
            postModel.setId(id);
            postModel.delete();
            next = "admin?menu=pView";
        }

        response.sendRedirect(next);
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
    return null;
    }
}
