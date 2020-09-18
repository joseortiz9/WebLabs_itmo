package ru.students.lab.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double x = Double.parseDouble(request.getParameter("x"));
            double y = Double.parseDouble(request.getParameter("y"));
            double r = Double.parseDouble(request.getParameter("r"));
            validateValuesRanges(x, y, r);
            getServletContext().getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        } catch (NumberFormatException | NullPointerException e) {
            request.setAttribute("showErrors", "1");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            PrintWriter writer = response.getWriter();
            writer.write("=======> ERROR <======= \n" + e.getMessage());
            writer.close();
        }
    }

    public void validateValuesRanges(double x, double y, double r) {
        if ((x<-4 || x>4) || (y<-3 || y>5) || (r<1 || r>5))
            throw new NumberFormatException();
    }
}
