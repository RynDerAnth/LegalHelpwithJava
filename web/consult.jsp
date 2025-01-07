<%-- 
    Document   : consult.jsp
    Created on : 31 Dec 2024, 00.38.14
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Konsultan"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp");
        return;
    }

    ArrayList<Konsultan> consultants = (ArrayList<Konsultan>) request.getAttribute("consultants");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consult</title>

        <link rel="stylesheet" href="style/user-consult.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <jsp:include page="component/user-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-consult">
            <div class="container">
                <h2 class="text-center mb-4" style="font-size: 25px">
                    Find out your necessary consultant.
                </h2>
                <div>
                    <form action="<%= request.getContextPath() %>/consultant">
                        <input type="hidden" name="menu" value="search">
                        <input name="search" class="form-control mr-sm-2 mb-5" type="search" placeholder="Search for a consultant" aria-label="Search">
                    </form>
                </div>
                <div class="container mx-auto p-4">
                    
                        <%
                            if (consultants != null && !consultants.isEmpty()) {
                        %>
                            <div class="same-based-consultant-grid">
                        <%
                                for (Konsultan consultant : consultants) {
                        %>
                        <div class="rounded-lg text-center sbc-size">
                            <img src="uploads/<%= consultant.getProfile_path() == null ? "https://via.placeholder.com/200" : consultant.getProfile_path() %>" alt="Profile"
                                 class="sbc-img mx-auto rounded-full object-cover" />
                            <div class="sbc-details">
                                <h2 class="sbc-title"><%= consultant.getName() %></h2>
                                <p class="sbc-detail text-gray-600"><%= consultant.getAddress() %></p>
                            </div>
                            <form action="<%= request.getContextPath() %>/consultant">
                                <input type="hidden" name="menu" value="detail">
                                <input type="hidden" name="id" value="<%= consultant.getId() %>">
                                <button class="bg-teal-600 text-white hover:bg-teal-700 transition ease-in-out" type="submit">
                                    Consult Now!
                                </button>
                            </form>
                            </div>
                            <%
                                    }
                                }else {
                            %>
                            <div class="mx-auto text-lg text-center">
                                Consultant Not Found!
                            </div>
                            <%
                                    }
                            %>
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
