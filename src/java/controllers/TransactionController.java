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
import models.Transaction;
import models.User;


@WebServlet(name = "TransactionController", urlPatterns = {"/transaction"})
public class TransactionController extends HttpServlet {

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
            response.sendRedirect("transaction?menu=view");
            return;
        }

        Transaction transactionModel = new Transaction();

        if ("view".equals(menu)) {
            ArrayList<Transaction> transactions = transactionModel.get();
            
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("/transaction/view.jsp").forward(request, response);

        } else {
            response.sendRedirect("transaction?menu=view");
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
        Transaction transactionModel = new Transaction();

        if ("book".equals(action)) {
            int rId = Integer.parseInt(request.getParameter("rId"));
            double sum = Double.parseDouble(request.getParameter("harga"));
            session = request.getSession();
            User userModel = new User();
            User user = userModel.find("username", (String)session.getAttribute("user"));
            
            double balance = user.getBalance();
            
            if (balance >= sum) {
                
                balance -= sum;
                user.setBalance(balance);
                user.update();
                
                transactionModel.setSenderId(user.getId());
                transactionModel.setReceiverId(rId);
                transactionModel.setSum(sum);
                transactionModel.insert();
            } else {
                response.sendRedirect(request.getContextPath() + "/consultant/detail.jsp?error=1");
            }
        }

        response.sendRedirect("consultant?menu=view");
    }
}