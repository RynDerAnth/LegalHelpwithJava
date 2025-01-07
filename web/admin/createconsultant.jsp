<%-- 
    Document   : createconsultant
    Created on : 31 Dec 2024, 03.33.42
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/admin-create-consultant.css">
    </head>
    <jsp:include page="../component/admin-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-create-consultant">
            <h2 class="text-2xl font-bold mb-4">Create Consultant</h2>
            <form action="admin?action=addC" method="POST" enctype="multipart/form-data">
                 <input type="file" name="profile_path" required> <br>
                <div class="col-8">
                    <label for="nama">Full Name</label>
                    <input type="text" name="nama" id="nama" placeholder="Consultant Name" class="form-control mb-2" required>
                </div>
                <div class="col-8">
                    <label for="harga">Price</label>
                    <input type="number" name="harga" id="harga" class="form-control mb-2" placeholder="0" required>
                </div>
                 <div class="col-8">
                    <label for="harga">Alamat</label>
                    <input type="text" name="alamat" id="address" class="form-control mb-2" placeholder="Alamat Konsultan" required>
                </div>
                 <div class="col-8">
                    <label for="harga">Bio</label>
                    <input type="text" name="bio" id="bio" class="form-control mb-2" placeholder="Deskripsi Konsultan" required>
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>

    </body>
</html>
