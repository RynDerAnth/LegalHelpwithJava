/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import models.Konsultan;
import models.Post;
import models.Transaction;
import models.User;


@WebServlet(name = "UserController", urlPatterns = {"/user"})
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

        if ("home".equals(menu)) {
            ArrayList<Konsultan> consultants = consultantModel.mostExpensive();
            ArrayList<Post> posts = postModel.mostRecent();
            
            request.setAttribute("consultants", consultants);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("home.jsp").forward(request, response);
            
        } else if ("edit".equals(menu)) {
            session = request.getSession();
            User user = userModel.find("username", (String)session.getAttribute("user"));
            
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/user/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("user?menu=home");
            }

        } else if ("addBalance".equals(menu)) {
            session = request.getSession();
            User user = userModel.find("username", (String)session.getAttribute("user"));
            
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/user/topup.jsp").forward(request, response);
            } else {
                response.sendRedirect("user?menu=home");
            }

        } else {
            response.sendRedirect("user?menu=home");
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
        User userModel = new User();

        if ("topup".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            double amount = userModel.getBalance() + Double.parseDouble(request.getParameter("amount"));

            userModel.setId(id);
            userModel.setBalance(amount);
            userModel.update();
            
            Transaction transactionModel = new Transaction();
            transactionModel.setSenderId(id);
            transactionModel.setSum(amount);
            transactionModel.insert();

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String hp = request.getParameter("hp");

            userModel.updateProfile(id, name, hp);
            userModel.update();

        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            userModel.setId(id);
            userModel.delete();
            response.sendRedirect("index.jsp");
        }

        response.sendRedirect("user?menu=home");
    }
}