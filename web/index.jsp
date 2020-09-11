<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="./assets/styles.css">
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
    <div class="jumbotron">
        <h1 class="text-center header-text">
            <span data-melo="ig round-left">Ortiz</span>
            <span data-melo="ig round-left1">Correa</span>
            <span data-melo="ig round-right">Jose</span>
            <span data-melo="ig round-right1">David</span>
            - P3232 -
            <span data-melo="ig weird-title">Var. 2813</span>
        </h1>
    </div>

    <jsp:useBean id="showErrors" scope="request" class="java.lang.String"/>
    <div id="error-banner"
         class="alert alert-danger ${showErrors.equals("1") ? "d-block" : "d-none"}"
         role="alert"
    >
        <b>Errors:</b> problems validating data, you trying something weird, please check that: <br>
        <b>=></b> X in [-3;5] &emsp;<b>=></b> Y in [-3;3] &emsp;<b>=></b> R in [1;3]
    </div>

    <div class="row">
        <div class="col-sm">
            <div class="custom-card">
            <canvas id="graph-canvas"></canvas>
            </div>
        </div>
        <div class="col-sm">
            <div class="custom-card">
                <form id="request-form" method="GET" action="ControllerServlet">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">X:</label>
                        <div class="col-sm-10" id="options_x"></div>
                    </div>
                    <div class="form-group row">
                        <label for="y" class="col-sm-2 col-form-label">Y:</label>
                        <div class="col-sm-10">
                            <input type="text" id="y" name="y"
                                   class="form-control"
                                   placeholder="value in [-3..3]" oninput="validateInput()"
                                   onchange="validateInput()"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="r" class="col-sm-2 col-form-label">R:</label>
                        <div class="col-sm-10">
                            <select name="r" class="form-control" id="r"></select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2 col-form-label"></div>
                        <div class="col-sm-10">
                            <button type="submit" id="submit-btn" class="btn btn-dark" disabled>send</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div>
        <table class="table table-striped table-hover table-dark text-center" id="results-table">
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>RESULT</th>
                <th>Current Time</th>
                <th>Computation Time</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="savedPoints" scope="session" class="java.util.ArrayList"/>
            <c:forEach var="point" items="${savedPoints}">
                <tr>
                    <td>${point.x}</td>
                    <td class='column-limited'>${point.y}</td>
                    <td>${point.r}</td>
                    <td class="${(point.result) ? "green-row" : "red-row"}">${point.result}</td>
                    <td>${point.createdTime}</td>
                    <td class='column-limited'>${point.computedTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./assets/canvas.js"></script>
<script src="./assets/scripts.js"></script>
</body>
</html>