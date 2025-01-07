
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Article"%>
<%
    Article article = (Article) request.getAttribute("article");
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
            <h2 class="fw-bold text-center mb-4">EDIT ARTICLE</h2>
            <form action="admin" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="action" value="editA">
                    <input type="hidden" name="id" value="<%= article.getId()%>">
            <div class="col-8">
                <input type="file" name="picture_path" > <br>
                <label for="headline">Headline</label>
                <input type="text" name="headline" id="headline" placeholder="Content Headline"
                    class="form-control mb-2" >
            </div>
            <div class="col-8">
                <label for="content">Content</label>
                <textarea name="content" id="content" class="form-control mb-2" placeholder="Fill of Content" ></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
        </div>
        
    </body>
</html>
