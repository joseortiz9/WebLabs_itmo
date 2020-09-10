<%--
  Created by IntelliJ IDEA.
  User: joseortiz09
  Date: 10.09.2020
  Time: 3:20
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" type="text/css" href="./assets/styles.css">
</head>

<body>
<main>
    <table class="main-table">
        <tbody>
        <tr class="header-tr">
            <th>
                <h1 class="header-text">
                    <span data-melo="ig round-left">Ортис</span>
                    <span data-melo="ig round-left1">Корреа</span>
                    <span data-melo="ig round-right">Хосе</span>
                    <span data-melo="ig round-right1">Давид</span>
                    - Р3232 -
                    <span data-melo="ig weird-title">Вар. 2813</span>
                </h1>
            </th>
        </tr>

        <tr>
            <td><canvas id="graph-canvas"></canvas></td>
        </tr>

        <tr>
            <td>
                <table class="form-table">
                    <form id="request-form">
                        <tr>
                            <td class="form-control-title"><span>X: </span></td>
                            <td><div id="options_x"></div></td>
                        </tr>
                        <tr>
                            <td class="form-control-title"><span>Y: </span></td>
                            <td>
                                <input type="text" id="y" name="y"
                                       class="form-control" form="request-form"
                                       placeholder="value in [-5..3]" oninput="validateInput()"
                                       onchange="validateInput()"
                                >
                            </td>
                        </tr>
                        <tr>
                            <td class="form-control-title"><span>R: </span></td>
                            <td><div id="options_r"></div></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" id="submit-btn" value="send" disabled></td>
                        </tr>
                    </form>
                </table>
            </td>
        </tr>

        <tr>
            <td>
                <table class="results-table">
                    <thead>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>RESULT</th>
                    <th>Current Time</th>
                    <th>Computation Time</th>
                    </thead>
                    <tbody id="results_table_body">
                    </tbody>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./assets/canvas.js"></script>
<script src="./assets/scripts.js"></script>
</body>
</html>