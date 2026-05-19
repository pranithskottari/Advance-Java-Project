package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/ReportCriteriaServlet")

public class ReportCriteriaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String type = req.getParameter("type");

        if (type.equals("date")) {
            req.setAttribute("type", "date");
        } else if (type.equals("ailment")) {
            req.setAttribute("type", "ailment");
        } else if (type.equals("doctor")) {
            req.setAttribute("type", "doctor");
        }

        RequestDispatcher rd = req.getRequestDispatcher("report_form.jsp");
        rd.forward(req, res);
    }
}