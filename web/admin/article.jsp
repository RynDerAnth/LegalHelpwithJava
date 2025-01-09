
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Article"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("/index.jsp");
        return;
    }

    ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articles");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/articlecrud.css">
        
        
    </head>
    <jsp:include page="../component/admin-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-article">
                 <a href="admin?menu=addA">
                    <button class="btn btn-success">Tambah Artikel</button>
                 </a>
                <%
                    if (articles != null && !articles.isEmpty()) {
                        for (Article article : articles) {
                %>
                <a>
                        <div class="article">
                            <img src="uploads/<%= article.getPicture_path()%>" alt="<%= article.getHeadline()  %>">
                            <div class="article-contents">
                                <p class="article-title"><%= article.getHeadline()  %></p>
                                <div class="article-date"><%= article.getCreated_at()  %></div>
                                <p><%= article.getContent()  %></p>
                            </div>

                                <form action="admin" method="POST" >
                                    <input type="hidden" name="action" value="deleteA">
                                    <input type="hidden" name="id" value="<%= article.getId()%>">
                                    <div class="action">
                                    <a href="admin?menu=editA&id=<%= article.getId()%>">Edit</a>
                                    <button type="submit" class="delete-button btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this article?');">Delete</button>
                                    </div>
                                </form>
                                
                        </div>
                <%
                    }
                  }
                %>
              
        </div>
        
    </body>
</html>
