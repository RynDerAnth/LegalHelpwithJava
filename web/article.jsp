<%-- 
    Document   : article
    Created on : 6 Jan 2025, 17.00.39
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.User"%>
<%@page import="models.Article"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("/index.jsp");
        return;
    }

    User user = new User();
    user = user.find("username", (String) session.getAttribute("user"));
    ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articles");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/article.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <jsp:include page="component/user-navsidebar.jsp" flush="true" />
    </head>
    <body>
        <div class="content-legalpedia">
        <div class="container">
            <h2 class="fw-bold text-center text-2xl">LEGALPEDIA</h2>

            <div class="container">

                <%
                   if (articles != null && !articles.isEmpty()) {
                %>
                       
                <%
                    for (Article article : articles) {
                %>
                       <div class="article">
                            <img src="uploads/<%= article.getPicture_path() %>" alt="Thumbnail">
                            <div class="article-contents">
                                <a href="#" class="article-title"><%= article.getHeadline() %></a>
                                <div class="article-date">Posted on <%= article.getCreated_at() %></div>
                                <p><%= article.getContent() %>
                                </p>
                            </div>
                        </div>
                <%
                    } 
                    }else {
                %>
                <h2 class="text-center">No data found</h2>
                <%
                    }
                %>

            </div>


        </div>
    </div>
    </body>
</html>
