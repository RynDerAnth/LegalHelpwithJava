<%-- 
    Document   : transaction
    Created on : 5 Jan 2025, 14.05.25
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Transaction"%>
<%@page import="models.User"%>
<%@page import="models.Konsultan"%>
<%
    HttpSession userSession = request.getSession();
    User user = new User();
    user = user.find("username", (String)session.getAttribute("user"));
%>
<%
    HttpSession transactionSession = request.getSession(false);

    ArrayList<Konsultan> consultants = (ArrayList<Konsultan>) request.getAttribute("consultants");
    ArrayList<Transaction> transactions = (ArrayList<Transaction>) request.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Transaction</title>
        <jsp:include page="component/user-navsidebar.jsp" flush="true" />
        <link rel="stylesheet" href="style/user-transaction.css">
    </head>
    <body>
        <div class="content-transaction">
            <h2 class="text-2xl text-bold"><%= user.getName() %>'s transaction History</h2>
            <hr style="margin-top:10px"></hr>
            </br>

                        <%
                            int count = 0;
                            if (transactions != null && !transactions.isEmpty()) {
                        %>
                        <table class="table-minimalist">
                        <thead class="bg-[#1b5c60] text-white">
                        <tr>
                            <th>Timestamp</th>
                            <th>Keterangan</th>
                            <th>Balance</th>
                        </tr>
                        </thead>
                            <tbody>
                        <%
                                for (Transaction transaction : transactions) {
                                    if (user.getId() == transaction.getSenderId()) {
                                    
                        %>
                        <tr>
                            <td><%= transaction.getCreated_at() %></td>
                            
                            <%
                              if (transaction.getReceiverId() == 1) {
                             %>
                                <td>Top Up Balance </td>
                                <td class="text-success">+ <%= transaction.getSum() %></td>
                            <%
                              count++;
                              }else  {
                            %>
                                <td>Booking <%= transaction.getConsultantName() %></td>
                                <td class="text-danger">- <%= transaction.getSum() %></td>
                            <%
                              }
                            %>
                        </tr>
                        
                        <%
                                    }
                                }
                             
                        %>
                        </tbody>
                         </table>
                        <%
                        } if (count == 0) {
                            

                        %>
                            
                            <h2 class="text-center mt-10 text-xl text-bold"> Belum ada transaksi apapun!</h2>
                        <%
    
                        }
                        %>
                    
               
        </div>
    </body>
</html>
