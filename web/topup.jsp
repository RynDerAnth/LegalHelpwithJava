<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Top Up</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="style/user-topup.css">
</head>
<jsp:include page="component/user-navsidebar.jsp" flush="true" />
<body>
    <!-- Container -->
    <div class="content-topup">
        <h1 class="text-2xl font-bold text-center text-gray-800 mb-4">Top Up</h1>
        <p class="text-center text-gray-500 mb-6">Pilih nominal yang diinginkan</p>
        
        <!-- Cards -->
        <div class="grid grid-cols-2 gap-4">
            <form action="user?action=topup" method="POST" class="cursor-pointer">
                <input type="hidden" name="amount" value="75000">
                <div class="bg-emerald-100 border border-emerald-500 rounded-lg p-4 text-center hover:bg-emerald-200" onclick="this.closest('form').submit();">
                    <h2 class="text-xl font-bold text-emerald-500">Rp 75,000</h2>
                </div>
            </form>
            <form action="user?action=topup" method="post" class="cursor-pointer">
                <input type="hidden" name="amount" value="150000">
                <div class="bg-emerald-100 border border-emerald-500 rounded-lg p-4 text-center hover:bg-emerald-200" onclick="this.closest('form').submit();">
                    <h2 class="text-xl font-bold text-emerald-500">Rp 150,000</h2>
                </div>
            </form>
            <form action="user?action=topup" method="post" class="cursor-pointer">
                <input type="hidden" name="amount" value="300000">
                <div class="bg-emerald-100 border border-emerald-500 rounded-lg p-4 text-center hover:bg-emerald-200" onclick="this.closest('form').submit();">
                    <h2 class="text-xl font-bold text-emerald-500">Rp 300,000</h2>
                </div>
            </form>
            <form action="user?action=topup" method="post" class="cursor-pointer">
                <input type="hidden" name="amount" value="750000">
                <div class="bg-emerald-100 border border-emerald-500 rounded-lg p-4 text-center hover:bg-emerald-200" onclick="this.closest('form').submit();">
                    <h2 class="text-xl font-bold text-emerald-500">Rp 750,000</h2>
                </div>
            </form>
            <form action="user?action=topup" method="post" class="cursor-pointer">
                <input type="hidden" name="amount" value="1500000">
                <div class="bg-emerald-100 border border-emerald-500 rounded-lg p-4 text-center hover:bg-emerald-200" onclick="this.closest('form').submit();">
                    <h2 class="text-xl font-bold text-emerald-500">Rp 1,500,000</h2>
                </div>
            </form>
            <form action="user?action=topup" method="post" class="cursor-pointer">
                <input type="hidden" name="amount" value="3000000">
                <div class="bg-emerald-100 border border-emerald-500 rounded-lg p-4 text-center hover:bg-emerald-200" onclick="this.closest('form').submit();">
                    <h2 class="text-xl font-bold text-emerald-500">Rp 3,000,000</h2>
                </div>
            </form>
        </div>
    </div>
</body>
</html>