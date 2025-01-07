
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h2 class="fw-bold text-center mb-4">CREATE ARTICLE</h2>
            <form action="admin?action=addA" method="POST" enctype="multipart/form-data">
            <div class="col-8">
                <input type="file" name="picture_path" required> <br>
                <label for="headline">Headline</label>
                <input type="text" name="headline" id="headline" placeholder="Content Headline"
                    class="form-control mb-2" required>
            </div>
            <div class="col-8">
                <label for="content">Content</label>
                <textarea name="content" id="content" class="form-control mb-2" placeholder="Fill of Content" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
        </div>
        
    </body>
</html>
