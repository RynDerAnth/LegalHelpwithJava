
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link rel="stylesheet" href="style/user-consult.css">
        <script src="https://cdn.tailwindcss.com"></script>

    <jsp:include page="component/user-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-consult">
        <div class="container">
            <h2 class="text-center mb-4" style="font-size: 25px">
                Find out your necessary consultant.
            </h2>
            <div class="container mx-auto p-4">
                <div class="same-based-consultant-grid">
                            <div class="rounded-lg text-center sbc-size">
                                <img src="https://via.placeholder.com/200.com" alt="Profile"
                                    class="sbc-img mx-auto rounded-full object-cover" />
                                <div class="sbc-details">
                                    <h2 class="sbc-title">Nama Consultant</h2>
                                    <p class="sbc-detail text-gray-600">Deskripsi</p>
                                </div>
                                <a href="consultantdetail.jsp">
                                    <button class="bg-teal-600 text-white hover:bg-teal-700 transition ease-in-out">
                                        Consult Now!
                                    </button>
                                </a>

                            </div>

                </div>
            </div>
        </div>
    </div>
    </body>
</html>
