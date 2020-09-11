package ru.students.lab.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("go_home").equals("1"))
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double[] x = Arrays.stream(request.getParameterValues("x[]")).mapToDouble(Double::parseDouble).toArray();
            double y = Double.parseDouble(request.getParameter("y"));
            double r = Double.parseDouble(request.getParameter("r"));
            getServletContext().getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        } catch (NumberFormatException | NullPointerException e) {
            String errors = "<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "           <b>Errors:</b> problems validating data, you trying something weird, please check that: <br>\n" +
                    "           <b>=></b> X in [-3;5] &emsp;<b>=></b> Y in [-3;3] &emsp;<b>=></b> R in [1;3]\n" +
                    "       </div>";
            request.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            PrintWriter writer = response.getWriter();
            writer.write("=======> ERROR <======= \n" + e.getMessage());
            writer.close();
        }
    }
}
