<%-- 
    Document   : consultantdetail
    Created on : 31 Dec 2024, 00.48.23
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="models.Konsultan"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp");
        return;
    }

    Konsultan consultant = (Konsultan) request.getAttribute("consultant");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultant Detail</title>
        <link rel="stylesheet" href="style/detailconsultant.css">
    </head>

    <jsp:include page="component/user-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-detailofconsultant">
            <div class=" overflow-hidden max-w-4xl w-full flex flex-col md:flex-row">
                <!-- Profile Image -->
                <div class="flex items-center justify-center md:w-1/3 h-64 md:h-auto">
                    <div class="rounded-full w-40 h-40 md:w-48 md:h-48 flex items-center justify-center"
                         style="background-image: url('uploads/<%= consultant.getProfile_path() == null ? "https://via.placeholder.com/200" : consultant.getProfile_path() %>'); background-size:cover">
                    </div>
                </div>

                <!-- Profile Details -->
                <div class="flex-1 p-6 md:p-8">
                    <h1 class="text-2xl md:text-3xl font-bold text-gray-800"><%= consultant.getName()%></h1>
                    <p class="text-lg md:text-xl text-gray-800"><%= consultant.getAddress()%></p>
                    <div class="mt-4">
                        <p class="text-lg md:text-3xl text-red-500 font-semibold">
                            <span
                                class="text-lg">Rp <%= NumberFormat.getInstance(Locale.getDefault()).format(consultant.getPrice()) %>
                            </span>
                        </p>
                    </div>

                    <% if (request.getParameter("error") != null) { %>
                    <div class="alert alert-danger mt-3 text-center">
                        Saldo anda tidak cukup!
                    </div>
                    <% }%>
                    <form action="transaction?action=book&rId=<%= consultant.getId()%>" method="POST">
                        <input type="hidden" name="harga" value="<%= consultant.getPrice()%>">
                        <button
                            class="mt-6 w-full bg-emerald-600 text-white font-semibold py-2 px-4 rounded-md shadow-md hover:bg-emerald-700 focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50 transition-all" type="submit">
                            Make a booking!
                        </button>
                    </form>
                </div>
                
            </div>
            <div class="desc-consultant mt-3">
                <h2 class="text-[20px] mb-2">From consultant:</h2>         
                <p><%= consultant.getBio()%></p>
            </div>
        </div>
    </div>
</body>
</html>
