<%-- 
    Document   : consultantlist.jsp
    Created on : 31 Dec 2024, 02.31.55
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Konsultan"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("/index.jsp");
        return;
    }

    ArrayList<Konsultan> consultants = (ArrayList<Konsultan>) request.getAttribute("consultants");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="style/admin-dashboard.css">
    </head>
    <jsp:include page="../component/admin-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-dashboard">

            <h2 class="text-2xl font-bold mb-4">Consultant List</h2>
            <a href="admin?menu=addC">
                <button class="btn btn-success mb-2">Tambah Konsultan</button>
            </a>
            <div class="overflow-x-auto">
                <table class="table-minimalist">
                    <thead class="bg-[#1b5c60] text-white">
                        <tr>
                            <th>Profile</th>
                            <th>Fullname</th>
                            <th>Price</th>
                            <th>Address</th>
                            <th>Bio</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (consultants != null && !consultants.isEmpty()) {
                                for (Konsultan consultant : consultants) {
                        %>

                        <tr>
                                <td>
                                    <img style="width: 40px;height: 40px;border-radius: 40px;object-fit: cover; margin:auto;" src="uploads/<%= consultant.getProfile_path()%>" alt="No Profile">
                                </td>
                                <td><%= consultant.getName()%></td>
                                <td><%= consultant.getPrice()%></td>
                                <td><%= consultant.getAddress()%></td>
                                <td><%= consultant.getBio()%></td>
                                <td>
                                    <a href="admin?menu=editC&id=<%= consultant.getId()%>" class=" btn btn-warning btn-sm">Edit</a>
                                    <form action="admin" method="POST">
                                        <input type="hidden" name="action" value="deleteC">
                                        <input type="hidden" name="id" value="<%= consultant.getId()%>">
                                        <button type="submit" class=" btn btn-danger btn-sm" onclick=" return confirm('Are you sure you want to delete this consultant?')">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        <%
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

