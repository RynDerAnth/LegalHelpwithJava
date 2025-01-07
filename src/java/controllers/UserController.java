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
import models.Konsultan;
import models.Post;
import models.Transaction;
import models.User;
import models.Article;



@WebServlet(name = "UserController", urlPatterns = {"/user"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)

public class UserController extends HttpServlet {
     

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
            response.sendRedirect("user?menu=home");
            return;
        }

        User userModel = new User();
        Post postModel = new Post();
        Konsultan consultantModel = new Konsultan(); 
        Article articleModel = new Article();

        if ("home".equals(menu)) {
            ArrayList<Post> posts = postModel.mostRecent();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            
        } else if ("edit".equals(menu)) {
            session = request.getSession();
            User user = userModel.find("username", (String)session.getAttribute("user"));
            
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/profile.jsp").forward(request, response);
            } else {
                response.sendRedirect("user?menu=home");
            }

        } else if ("article".equals(menu)) {
            ArrayList<Article> articles = articleModel.mostRecent();
            request.setAttribute("articles", articles);
            request.getRequestDispatcher("/article.jsp").forward(request, response);

        }  else if ("addBalance".equals(menu)) {
            session = request.getSession();
            User user = userModel.find("username", (String)session.getAttribute("user"));
            
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/topup.jsp").forward(request, response);
            } else {
                response.sendRedirect("user?menu=home");
            }

        }
        
        else {
            response.sendRedirect("user?menu=home");
        }
    }
    
    private static final String UPLOAD_DIR = "uploads";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String next = "";
        
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String action = request.getParameter("action");
        User userModel = new User();
        Post postModel = new Post();

        if ("topup".equals(action)) {
            session = request.getSession();
            User user = userModel.find("username", (String)session.getAttribute("user"));
            double amount = user.getBalance() + Double.parseDouble(request.getParameter("amount"));

            user.setBalance(amount);
            user.update();
            
            Transaction transactionModel = new Transaction();
            transactionModel.setSenderId(user.getId());
            transactionModel.setReceiverId(1);
            transactionModel.setSum(Double.parseDouble(request.getParameter("amount")));
            transactionModel.insert();
            next = "user?menu=topup";

        } else if ("edit".equals(action)) {
            User user = userModel.find("username", (String) session.getAttribute("user"));
            if (user != null) {
                int id = user.getId();
                String name = request.getParameter("name");
                String hp = request.getParameter("mobile_number");

                // Path untuk folder uploads (di dalam direktori aplikasi)
                String uploadPath = getServletContext().getRealPath("") + "uploads";
                File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String profilePath = user.getProfile_path(); // Path default
            Part filePart = request.getPart("profile_path");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                String fullPath = uploadPath + File.separator + fileName; 
                filePart.write(fullPath); 
                profilePath = fileName; 
            }

            userModel.updateProfile(id, user.getUsername(), name, hp, profilePath);

            userModel.update();
            next = "user?menu=edit";
            } else {
            response.sendRedirect("user?menu=home");
        }   
 

        } else if (action.equals("delPost")) {
            int id = Integer.parseInt(request.getParameter("id"));
            postModel.setId(id);
            postModel.delete();
            next = "user?menu=delPost";
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