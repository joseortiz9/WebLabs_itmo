package ru.students.lab.servlets;

import ru.students.lab.models.Point;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long start = System.currentTimeMillis();

        HttpSession httpSession = request.getSession();

        double[] x = Arrays.stream(request.getParameterValues("x[]")).mapToDouble(Double::parseDouble).toArray();
        double y = Double.parseDouble(request.getParameter("y"));
        double r = Double.parseDouble(request.getParameter("r"));

        List<Point> savedPoints = (List<Point>) httpSession.getAttribute("savedPoints");
        if (savedPoints == null)
            savedPoints = new ArrayList<>();

        List<Point> newPoints = new ArrayList<>();
        for (double valX: x) {
            LocalDateTime createdAt = millsToLocalDateTime(start);
            String computedTime = ((double) (System.currentTimeMillis() - start) / 1e3) + "S";
            newPoints.add(new Point(valX, y, r, createdAt, computedTime));
        }

        savedPoints.addAll(newPoints);
        httpSession.setAttribute("savedPoints", savedPoints);
        sendViewResponse(newPoints, response);
    }


    private void sendViewResponse(List<Point> points, HttpServletResponse response) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringBuilder HTMLResponse = new StringBuilder("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>WebLab2</title>\n" +
                "    <link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"./assets/images/favicon/apple-touch-icon.png\">\n" +
                "    <link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"./assets/images/favicon/favicon-32x32.png\">\n" +
                "    <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"./assets/images/favicon/favicon-16x16.png\">\n" +
                "    <link rel=\"manifest\" href=\"./assets/images/favicon/site.webmanifest\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background: #1874d2;\n" +
                "        }\n" +
                "        main {\n" +
                "            margin-top: 30px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<nav class=\"navbar navbar-expand-sm navbar-dark bg-dark\">\n" +
                "    <a class=\"navbar-brand\" href=\"#\">\n" +
                "        <img src=\"./assets/images/favicon/favicon-32x32.png\" width=\"30\" height=\"30\"\n" +
                "             class=\"d-inline-block align-top\" alt=\"brans-logo\"\n" +
                "        >\n" +
                "        It's my brand\n" +
                "    </a>\n" +
                "    <a class=\"nav-item nav-link\" href=\"index.jsp\">Home</a>\n" +
                "    <a class=\"nav-item nav-link\" href=\"https://github.com/joseortiz9/WebLabs_itmo\">GitHub</a>\n" +
                "</nav>\n" +
                "<main role=\"main\" class=\"container\">\n" +
                "    <div class=\"row\">\n");

        for(Point point: points) {
            String title = (point.isResult()) ? "Inside!" : "Outside!";
            String bgStyle = (point.isResult()) ? "success" : "danger";
            HTMLResponse.append(
                    "<div class=\"col-sm-3\">" +
                    "<div class=\"card text-white bg-" + bgStyle + " mb-3\" style=\"max-width: 18rem;\">\n" +
                    "    <div class=\"card-header\">" + title + "</div>\n" +
                    "    <div class=\"card-body\">\n" +
                    "        <h5 class=\"card-title\">Point:</h5>\n" +
                    "        <p class=\"card-text\">\n" +
                    "            <span><b>X:</b> "+ point.getX() + "</span><br>\n" +
                    "            <span><b>Y:</b> "+ point.getY() + "</span><br>\n" +
                    "            <span><b>R:</b> "+ point.getR() + "</span><br>\n" +
                    "            <span><b>CurrentTime:</b> " + point.getCreatedTime().format(formatter) + "</span><br>\n" +
                    "            <span><b>ComputedTime:</b> " + point.getComputedTime() + "</span>\n" +
                    "        </p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</div>\n"
            );
        }

        HTMLResponse.append("<div class=\"col-sm-12\">\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-sm-4\"></div>\n" +
                "                <div class=\"col-sm-4\">\n" +
                "                    <form method=\"POST\" action=\"ControllerServlet\">" +
                "                       <input type=\"hidden\" name=\"go_home\" value=\"1\">" +
                "                       <button type=\"submit\" class=\"btn btn-dark btn-lg btn-block\">Go back</button>" +
                "                    </form>\n" +
                "                </div>\n" +
                "                <div class=\"col-sm-4\"></div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(HTMLResponse.toString());
        out.close();
    }

    public LocalDateTime millsToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
