<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>LegalHelp</title>
        <link rel="stylesheet" href="style/login.css" />
        <link rel="stylesheet" href="register.css" />
        <link rel="stylesheet" href="https://rsms.me/inter/inter.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    </head>

    <body>

        <div class="container">
            <div class="content">
                <div class="slogan">
                    Protect your life from <span id="element"></span>
                </div>
            </div>
            <div class="register-content">
                <div class="reg" id="reg">
                    <h3 class="text-center">Fill out your identity</h3>
                    <br />
                    <form method="POST" action="<%= request.getContextPath()%>/AuthController">
                        <input type="hidden" name="action" value="register">
                        <div class="col-12">
                            <label for="name">Name</label>
                            <input type="text" class="form-control mb-2" name="name" placeholder="Name" required>
                        </div>
                        <div class="col-12">
                            <label for="username">Username</label>
                            <input type="text" class="form-control mb-2" name="username" placeholder="Username" required>
                        </div>
                        <div class="col-12">
                            <label for="password">Password</label>
                            <input type="password" class="form-control mb-2" name="password" placeholder="Password" required>
                        </div>
                        <br>
                        <button type="submit" id="submit-reg" class="submit-btn">
                            Submit
                        </button>
                    </form>
                    <div class="card-footer text-center" style="margin-top: 20px">
                        <a href="index.jsp" id="haveAcc">Already have an account?</a>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://unpkg.com/typed.js@2.1.0/dist/typed.umd.js"></script>
        <script src="jquery.js"></script>
        <script>
            var typed = new Typed("#element", {
                strings: ["legals violation", "jail"],
                typeSpeed: 50,
                backSpeed: 50,
                loop: true,
                loopCount: Infinity,
            });
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
        </script>
    </body>

</html>
