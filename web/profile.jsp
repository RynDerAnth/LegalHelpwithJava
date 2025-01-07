
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User"%>
<%
    User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="style/user-profile.css"
    </head>
    <jsp:include page="component/user-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-profile">
            <div class="max-w-3xl mx-auto py-8 px-4">
                <div class="text-center">
                    <div class="relative">
                        <img src="uploads/<%= user.getProfile_path() == null ? "https://via.placeholder.com/200" : user.getProfile_path() %>"
                             alt="Profile Picture"
                             class="w-32 h-32 mx-auto rounded-full border-4 border-emerald-600 object-cover">
                    </div>
                    <h2 class="text-2xl font-semibold mt-4"><%= user.getUsername()%></h2>
                    <p class="text-gray-500"><%= user.getName()%></p>
                </div>
                <form action="user" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="<%= user.getId()%>">
                    
                    <label for="profile_path">Profile Picture:</label>
                    <input type="file" name="profile_path" id="profile_path" accept="image/*">
                    
                    
                    <div>
                        <label for="name" class="block text-sm font-medium text-gray-700">Fullname</label>
                        <input type="text" id="name" name="name"
                               class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-lg py-2 px-3"
                               value="<%= user.getName()%>">
                    </div>
                    
                    
                    <div>
                        <label for="mobile_number" class="block text-sm font-medium text-gray-700">Mobile Number</label>
                        <input type="text" id="mobile_number" name="mobile_number"
                               class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-lg py-2 px-3"
                               value="<%= user.getHandphone()%>">
                    </div>
                    

                    <br><br>
                    
                    
                    <button type="submit"
                            class=" w-full py-3 px-4 bg-emerald-600 text-white font-medium rounded-md shadow hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-emerald-500">
                        Save Changes
                    </button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
