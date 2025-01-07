
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="style/user-home.css" />
</head>
<jsp:include page="component/user-navsidebar.jsp" flush="true" />
<body>  
        <div class="content-forum">
        <div class="container">
            <h2 style="font-size: 25px">Tuangkan suaramu, user!</h2>

            <div class="form mb-4">
        <form action="" method="POST">
            <div class="form-row">
                <img src="https://via.placeholder.com/200" alt="Profile Picture">
                <div class="textarea-container">
                    <textarea name="" placeholder="Ceritakan apa yang terjadi hari ini..."></textarea>
                    <button>Submit</button>
                </div>
            </div>
        </form>
    </div>

            <div class="threads">
                    <div class="thread">
                        <img src="https://via.placeholder.com/50" alt=" Profile">
                        <div class="thread-content">
                            <div class="thread-title">Nama User</div>
                            <div class="thread-text">Isi Content</div>
                            <div class="thread-time">Tanggal Buat</div>
                        </div>
                    </div>
                    <div class="thread">
                        <img src="https://via.placeholder.com/50" alt="Profile">
                        <div class="thread-content">
                            <div class="thread-title">Nama User</div>
                            <div class="thread-text">Isi Content</div>
                            <div class="thread-time">Tanggal Buat</div>
                        </div>
                    </div>
                <div class="thread">
                        <img src="https://via.placeholder.com/50" alt="Profile">
                        <div class="thread-content">
                            <div class="thread-title">Nama User</div>
                            <div class="thread-text">Isi Content</div>
                            <div class="thread-time">Tanggal Buat</div>
                        </div>
                    </div>
                <div class="thread">
                        <img src="https://via.placeholder.com/50" alt="Profile">
                        <div class="thread-content">
                            <div class="thread-title">Nama User</div>
                            <div class="thread-text">Isi Content</div>
                            <div class="thread-time">Tanggal Buat</div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</body>

</html>

