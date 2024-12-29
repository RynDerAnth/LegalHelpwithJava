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
import models.Transaction;
import models.User;


@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
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

        if ("view".equals(menu)) {
            ArrayList<User> users = userModel.get();
            
            request.setAttribute("users", users);
            request.getRequestDispatcher("/admin/view.jsp").forward(request, response);
            
        } else if ("cView".equals(menu)) {
            ArrayList<Konsultan> consultants = consultantModel.get();
            
            request.setAttribute("consultants", consultants);
            request.getRequestDispatcher("/admin/cView.jsp").forward(request, response);
            
        } else if ("tView".equals(menu)) {
            ArrayList<Transaction> transactions = transactionModel.get();
            
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("/admin/tView.jsp").forward(request, response);
            
        } else if ("addC".equals(menu)) {
            request.getRequestDispatcher("/admin/addC.jsp").forward(request, response);

        } else if ("editC".equals(menu)) {
            String id = request.getParameter("id");
            Konsultan consultant = consultantModel.find("id", id);
            if (consultant != null) {
                request.setAttribute("consultant", consultant);
                request.getRequestDispatcher("/admin/editC.jsp").forward(request, response);
            } else {
                response.sendRedirect("admin?menu=cView");
            }

        } else if ("edit".equals(menu)) {
            session = request.getSession();
            User user = userModel.find("username", (String)session.getAttribute("user"));
            
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("admin?menu=view");
            }

        } else {
            response.sendRedirect("admin?menu=view");
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
        Konsultan consultantModel = new Konsultan();

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String hp = request.getParameter("hp");

            userModel.updateProfile(id, name, hp);
            userModel.update();

        } else if ("addC".equals(action)) {
            String name = request.getParameter("nama");
            double price = Double.parseDouble(request.getParameter("harga"));

            consultantModel.setName(name);
            consultantModel.setPrice(price);
            consultantModel.insert();

        } else if ("editC".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));

            consultantModel.setId(id);
            consultantModel.setName(name);
            consultantModel.setPrice(price);
            consultantModel.update();

        } else if (action.equals("deleteC")) {
            int id = Integer.parseInt(request.getParameter("id"));
            consultantModel.setId(id);
            consultantModel.delete();
        }

        response.sendRedirect("admin?menu=view");
    }
}