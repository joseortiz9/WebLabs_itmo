<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebLab2</title>
    <link rel="apple-touch-icon" sizes="180x180" href="./assets/images/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="./assets/images/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="./assets/images/favicon/favicon-16x16.png">
    <link rel="manifest" href="./assets/images/favicon/site.webmanifest">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        body {
            background: #1874d2;
        }
        main {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <img src="./assets/images/favicon/favicon-32x32.png" width="30" height="30"
             class="d-inline-block align-top" alt="brans-logo"
        >
        It's my brand
    </a>
    <a class="nav-item nav-link" href="index.jsp">Home</a>
    <a class="nav-item nav-link" href="https://github.com/joseortiz9/WebLabs_itmo">GitHub</a>
</nav>
<main role="main" class="container">
    <div class="row">
        <jsp:useBean id="addedPoint" scope="request" class="ru.students.lab.models.Point"/>
        <div class="col-sm-3">
            <div class="card text-white bg-${(addedPoint.result) ? "success" : "danger"} bgStyle mb-3" style="max-width: 18rem;">
                <div class="card-header">${(addedPoint.result) ? "Inside!" : "Outside!"}</div>
                <div class="card-body">
                    <h5 class="card-title">Point:</h5>
                    <p class="card-text">
                        <span><b>X:</b> ${addedPoint.x}</span><br>
                        <span><b>Y:</b> ${addedPoint.y}</span><br>
                        <span><b>R:</b> ${addedPoint.r}</span><br>
                        <span><b>CurrentTime:</b> ${addedPoint.createdTimeFormatted}</span><br>
                        <span><b>ComputedTime:</b> ${addedPoint.computedTimeFormatted}</span>
                    </p>
                </div>
            </div>
        </div>

        <div class="col-sm-12">
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <a href="index.jsp" class="btn btn-dark btn-lg btn-block">Go back</a>
                </div>
                <div class="col-sm-4"></div>
            </div>
        </div>
    </div>
</main>
</body>
</html>