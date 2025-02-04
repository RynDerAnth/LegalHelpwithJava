<%-- 
    Document   : user-app
    Created on : 30 Dec 2024, 22.03.52
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User"%>
<%
    HttpSession userSession = request.getSession();
    User user = new User();
    user = user.find("username", (String)session.getAttribute("user"));
%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />

</head>

<body style="background-color: #e3e0cd">
    <!-- Navbar -->
    <div class="navbar flex justify-start items-center bg-[#1b5c60] text-white p-2">
        <div class="menu-icon text-2xl cursor-pointer" onclick="toggleMenu()">&#9776;</div>
        <img src="img/legalhelp.png" alt="LegalHelp" style="height:50px">
    </div>

    <!-- Overlay -->
    <div id="overlay" class="hidden fixed inset-0 bg-black bg-opacity-50 z-10" onclick="toggleMenu()"></div>

    <!-- Side Menu -->
    <div id="sideMenu"
        class="fixed top-0 left-[-100%] w-64 h-full bg-[#1b5c60] text-white transition-all duration-300 transform z-20 p-4">
        <div class="profile-section flex flex-col items-center text-center border-b border-gray-700 pb-4 mb-4">
            <img src="https://via.placeholder.com/80" alt="Profile Picture"
                class="w-20 h-20 rounded-full border-2 border-gray-700 mb-2">
            <h2 class="text-lg font-bold"><%= user.getUsername() %></h2>
        </div>
        <nav class="mt-4">
            <a href="<%= request.getContextPath() %>/admin?menu=view" class="block py-2 px-4 hover:bg-gray-700 rounded">Users Dashboard</a>
            <a href="<%= request.getContextPath() %>/admin?menu=cView" class="block py-2 px-4 hover:bg-gray-700 rounded">Consultant</a>
            <a href="<%= request.getContextPath() %>/admin?menu=pView" class="block py-2 px-4 hover:bg-gray-700 rounded">Feeds</a>
            <a href="<%= request.getContextPath() %>/admin?menu=aView" class="block py-2 px-4 hover:bg-gray-700 rounded">Article</a>
            <a href="<%= request.getContextPath() %>/admin?menu=tView" class="block py-2 px-4 hover:bg-gray-700 rounded">Transaction History</a>
            <a href="<%= request.getContextPath() %>/AuthController?action=logout" class="block py-2 px-4 hover:bg-gray-700 rounded">Logout</a>
            
        </nav>
    </div>


    <script>
        function toggleMenu() {
            const sideMenu = document.getElementById('sideMenu');
            const overlay = document.getElementById('overlay');
            const isOpen = sideMenu.style.left === '0px';

            if (isOpen) {
                sideMenu.style.left = '-100%';
                overlay.classList.add('hidden');
            } else {
                sideMenu.style.left = '0px';
                overlay.classList.remove('hidden');
            }
        }
    </script>
</body>
</html>
