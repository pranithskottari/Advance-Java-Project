package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import Dao.HospitalDao;
import model.Patient;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        List<Patient> list = new ArrayList<>();

        try {

            String type = req.getParameter("type");

            HospitalDao dao = new HospitalDao();

            if ("date".equals(type)) {

                String from = req.getParameter("from");
                String to = req.getParameter("to");

                list = dao.getPatientsByDate(from, to);

            }
            else if ("ailment".equals(type)) {

                String ailment = req.getParameter("ailment");

                list = dao.getPatientsByAilment(ailment);

            }
            else if ("doctor".equals(type)) {

                String doctor = req.getParameter("doctor");

                list = dao.getPatientsByDoctor(doctor);
            }

            // send data to JSP
            req.setAttribute("patients", list);

            RequestDispatcher rd =
                    req.getRequestDispatcher("report_result.jsp");

            rd.forward(req, res);

        }
        catch (Exception e) {

            e.printStackTrace();

            res.setContentType("text/html");

            res.getWriter().println("<h2>Error in Report Servlet</h2>");

            res.getWriter().println("<pre>");
            e.printStackTrace(res.getWriter());
            res.getWriter().println("</pre>");
        }
    }
}