<%-- 
    Document   : transaction
    Created on : 5 Jan 2025, 14.05.25
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Transaction"%>
<% 
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("/index.jsp");
        return;
    }

    ArrayList<Transaction> transactions = (ArrayList<Transaction>) request.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Transaction</title>
        <jsp:include page="../component/admin-navsidebar.jsp" flush="true" />
        <link rel="stylesheet" href="style/user-transaction.css">
    </head>
    <body>
        <div class="content-transaction">
            <h2 class="text-2xl text-bold">Revenue</h2>
            <hr style="margin-top:10px"></hr>
            <br/>
            <table class="table-minimalist">
                <thead class="bg-[#1b5c60] text-white">
                    <tr>
                        <th>Timestamp</th>
                        <th>User</th>
                        <th>Keterangan</th>
                        <th>Balance</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    int count = 0;
                    if (transactions != null && !transactions.isEmpty()) {
                        for (Transaction transaction : transactions) {
                            if (transaction.getId() != 0) {
                                count++;
                    %>
                    <tr>
                        <td><%= transaction.getCreated_at() %></td>
                        <td><%= transaction.getUserName() %></td>
                        <% 
                            if (transaction.getReceiverId() != 0) {
                        %>
                        <td>Book <%= transaction.getConsultantName() %></td>
                        <% 
                            }else {
                        %>
                            <td>Top Up</td>
                        <% 
                            }
                        %>
                        <td class="text-success"> <%= transaction.getSum() %></td>
                    </tr>
                    <% 
                            } 
                        } 
                    } 
                    if (count == 0) {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">Belum ada transaksi apapun!</td>
                    </tr>
                    <% 
                    } 
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
