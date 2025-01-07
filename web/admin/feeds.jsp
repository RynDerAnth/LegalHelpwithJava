<%-- 
    Document   : threads
    Created on : 31 Dec 2024, 02.32.32
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Post"%>
<%
    ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/user-home.css">
    </head>
    <jsp:include page="../component/admin-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-forum">
            <h2 class="text-lg text-bold mb-3">PEOPLE FEEDS</h2>
            <div class="threads">
                <%
                    if (posts != null && !posts.isEmpty()) {
                        for (Post post : posts) {

                %>
                <div class="thread">
                    <img src="<%= post.getProfile_path() %>" alt=" Profile">
                    <div class="thread-content">
                        <div class="thread-title"><%= post.getUserName()%></div>
                        <div class="thread-text"><%= post.getContent()%></div>
                    </div>
                    <form action="admin" method="POST">
                        <input type="hidden" name="action" value="deleteP">
                        <input type="hidden" name="id" value="<%= post.getId()%>">
                        <button type="submit"> Hapus </button>
                    </form>
                </div>
                <%
                        }
                    }else {
                %>
                    <h2 class="text-xl text-center text-bold mt-20">Belum ada Post apapun!</h2>
                <%
                        }
                %>
            </div>
        </div>
    </div>
</body>
</html>
