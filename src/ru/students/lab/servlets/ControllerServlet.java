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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //try {
            if (request.getParameter("go_home").equals("1"))
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

            double[] x = Arrays.stream(request.getParameterValues("x[]")).mapToDouble(Double::parseDouble).toArray();
            double y = Double.parseDouble(request.getParameter("y"));
            double r = Double.parseDouble(request.getParameter("r"));
            getServletContext().getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        /*} catch (Exception e) {
            PrintWriter writer = response.getWriter();
            writer.write("=======> ERROR <======= \n" + Arrays.toString(e.getStackTrace()));
            writer.close();
        }*/
    }
}
