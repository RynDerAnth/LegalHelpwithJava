<%-- 
    Document   : consultantdetail
    Created on : 31 Dec 2024, 00.48.23
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/detailconsultant.css">
    </head>
    
    <jsp:include page="component/user-navsidebar.jsp" flush="true" />
    <body>
        <div class="content-detailofconsultant">
        <div class=" overflow-hidden max-w-4xl w-full flex flex-col md:flex-row">
            <!-- Profile Image -->
            <div class="flex items-center justify-center md:w-1/3 h-64 md:h-auto">
                <div class="rounded-full w-40 h-40 md:w-48 md:h-48 flex items-center justify-center"
                    style="background-image: url('https://via.placeholder.com/200.com'); background-size:cover">
                </div>
            </div>

            <!-- Profile Details -->
            <div class="flex-1 p-6 md:p-8">
                <h1 class="text-2xl md:text-3xl font-bold text-gray-800">Nama Konsultan</h1>
                <p class="text-sm md:text-base text-gray-600 mt-1">Email Konsultan - Based in
                    Alamat</p>

                <div class="mt-4">
                    <p class="text-lg md:text-3xl text-red-500 font-semibold"><span
                            class="text-lg">$99.49/days</span></p>
                </div>

                <div class="mt-6">
                    <label for="schedule" class="block text-sm font-medium text-gray-700">Choose the schedule:</label>
                    <div class="relative mt-2">
                        <input type="date" id="schedule"
                            class="block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-emerald-500 focus:border-emerald-500 sm:text-sm"
                            placeholder="mm / dd / yyyy" />
                    </div>
                </div>

                <button
                    class="mt-6 w-full bg-emerald-600 text-white font-semibold py-2 px-4 rounded-md shadow-md hover:bg-emerald-700 focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50 transition-all">
                    Make a Schedule!
                </button>
            </div>
        </div>

    </div>
    </div>
    <script>
        const today = new Date();
        const formattedToday = today.toISOString().split('T')[0];

        document.getElementById('schedule').setAttribute('min', formattedToday);
    </script>
    </body>
</html>
