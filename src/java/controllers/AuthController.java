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
import models.User;

@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        String action = request.getParameter("action");

        if (action.equals("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user.login(username, password);

            if (user.find("username", username) != null) {
                String un = (user.find("username", username)).getUsername();
                String pw = (user.find("username", username)).getPassword();

                if (un.equals(username) && pw.equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", username);

                    if (user.roleCheck().equals("user")) {
                        response.sendRedirect(request.getContextPath() + "/user?menu=home");
                    } else if (user.roleCheck().equals("admin")) {
                        response.sendRedirect(request.getContextPath() + "/admin?menu=view");
                    }

                } else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
                }
                
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
            }

        } else if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }

            response.sendRedirect(request.getContextPath() + "/index.jsp");

        } else if (action.equals("register")) {
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            user.register(name, username, password);
            user.insert();

            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect(request.getContextPath() + "/user?menu=home");

        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
