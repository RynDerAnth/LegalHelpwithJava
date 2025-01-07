<%-- 
    Document   : dashboard
    Created on : 31 Dec 2024, 02.26.12
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.User"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp");
        return;
    }

    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="style/admin-dashboard.css">
    </head>
    <jsp:include page="../component/admin-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-dashboard">
            <!-- Main Content -->

            <!-- Stats Section -->
            <div class="grid grid-cols-2 md:grid-cols-4 gap-4 mb-6 ">
                <div class="bg-white shadow-md rounded-lg p-6 text-center">
                    <h3 class="text-xl font-semibold text-gray-700">Total Users</h3>
                    <p class="text-3xl font-bold text-green-500 mt-2"><%= request.getAttribute("uCount")%></p>
                </div>
                <div class="bg-white shadow-md rounded-lg p-6 text-center">
                    <h3 class="text-xl font-semibold text-gray-700">Forum Activities</h3>
                    <p class="text-3xl font-bold text-blue-500 mt-2"><%= request.getAttribute("pCount")%></p>
                </div>
                <div class="bg-white shadow-md rounded-lg p-6 text-center">
                    <h3 class="text-xl font-semibold text-gray-700">Total Consultant</h3>
                    <p class="text-3xl font-bold text-red-500 mt-2"><%= request.getAttribute("cCount")%></p>
                </div>
            </div>

            <h2 class="text-2xl font-bold mb-4">Data Users</h2>
            <div class="overflow-x-auto">

                <table class="table-minimalist">
                    <thead class="bg-[#1b5c60] text-white">
                        <tr>
                            <th>ID</th>
                            <th>Fullname</th>
                            <th>Username</th>
                            <th>Telephone Number</th>
                                <th>Account Balance</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (users != null && !users.isEmpty()) {
                                for (User user : users) {
                                    if (user.getRole().equals("user")) {
                        %>
                        <tr>
                            <td><%= user.getId()%></td>
                            <td><%= user.getName()%></td>
                            <td><%= user.getUsername()%></td>
                            <td><%= user.getHandphone()%></td>
                            <td><%= user.getBalance()%></td>
                            <td>
                                <form action="admin" method="POST" style="display:inline;">
                                    <input type="hidden" name="action" value="deleteU">
                                    <input type="hidden" name="id" value="<%= user.getId()%>">
                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this user?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                        <%
                                    }
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
