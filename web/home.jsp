<%-- 
    Document   : home
    Created on : 29 Dec 2024, 22.15.55
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.User"%>
<%@page import="models.Post"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("/index.jsp");
        return;
    }

    User user = new User();
    user = user.find("username", (String) session.getAttribute("user"));
    ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
        <link rel="stylesheet" href="style/user-home.css" />
    </head>
    <jsp:include page="component/user-navsidebar.jsp" flush="true" />
    <body>  
        <div class="content-forum">
            <div class="container">
                <h2 style="font-size: 25px">Tuangkan suaramu, <%= userSession.getAttribute("user")%>!</h2>

                <div class="form mb-4">
                    <form action="post" method="POST">
                        <input type="hidden" name="action" value="add">
                        <div class="form-row">
                            <img src="uploads/<%=user.getProfile_path() %>" alt="unset">
                            <div class="textarea-container">
                                <input type="hidden" name="uId" value="<%= user.getId()%>">
                                <input type="hidden" name="un" value="<%= user.getUsername()%>">
                                <textarea type="text" name="content" placeholder="Ceritakan apa yang terjadi hari ini..." class="form-control"></textarea>
                                <button type="submit">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>

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
                        <%
                                if (post.getUserId() == user.getId()) {
                        %>
                        <form action="user" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delPost">
                            <input type="hidden" name="id" value="<%= post.getId()%>">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this post?');">Delete</button>
                        </form>
                        <%
                                }
                        %>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </body>

</html>

