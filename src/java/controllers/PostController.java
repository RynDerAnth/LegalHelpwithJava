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
import models.Post;
import models.User;


@WebServlet(name = "PostController", urlPatterns = {"/post"})
public class PostController extends HttpServlet {

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

        Post postModel = new Post();

        if ("view".equals(menu)) {
            ArrayList<Post> posts = postModel.get();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/home.jsp").forward(request, response);

        } else {
            response.sendRedirect("post?menu=view");
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
        Post postModel = new Post();

        if ("add".equals(action)) {
            String content = request.getParameter("content");
            int id = Integer.parseInt(request.getParameter("uId"));
            String userName = request.getParameter("un");


            postModel.setContent(content);
            postModel.setUserId(id);
            postModel.setUserName(userName);
            postModel.insert();

        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            postModel.setId(id);
            postModel.delete();
        }

        response.sendRedirect("user?menu=home");
    }
}