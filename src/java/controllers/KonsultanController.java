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
import models.Konsultan;
import java.util.ArrayList;


@WebServlet(name = "KonsultanController", urlPatterns = {"/consultant"})
public class KonsultanController extends HttpServlet {

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
            response.sendRedirect("consultant?menu=view");
            return;
        }

        Konsultan consultantModel = new Konsultan();

        if ("view".equals(menu)) {
            ArrayList<Konsultan> consultants = consultantModel.showAll();
            
            request.setAttribute("consultants", consultants);
            request.getRequestDispatcher("/consult.jsp").forward(request, response);
            
        } else if ("detail".equals(menu)) {
            String id = request.getParameter("id");
            Konsultan consultant = consultantModel.find("id", id);
            
            if (consultant != null) {
                request.setAttribute("consultant", consultant);
                request.getRequestDispatcher("/consultantdetail.jsp").forward(request, response);
                
            } else {
                response.sendRedirect("consultant?menu=view");
            }

        } else if ("search".equals(menu)) {
            String name = request.getParameter("search");
            consultantModel.where("name LIKE '%" + name + "%'");
            ArrayList<Konsultan> consultants =  consultantModel.get();

            
            request.setAttribute("consultants", consultants);
            request.getRequestDispatcher("/consult.jsp").forward(request, response);

        } else {
            response.sendRedirect("consultant?menu=view");
        }
    }
}