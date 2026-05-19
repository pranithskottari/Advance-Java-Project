package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import Dao.HospitalDao;
import model.Patient;

@WebServlet("/AddPatientServlet")
public class AddPatientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String gender = req.getParameter("gender");
            String date = req.getParameter("date");
            String ailment = req.getParameter("ailment");
            String doctor = req.getParameter("doctor");

            Patient p = new Patient(
                    0,
                    name,
                    age,
                    gender,
                    date,
                    ailment,
                    doctor
            );

            HospitalDao dao = new HospitalDao();
            dao.insertPatient(p);

            // ✅ REDIRECT TO VIEW PAGE
            res.sendRedirect("DisplayPatientsServlet");

        } catch (Exception e) {

            e.printStackTrace();

            res.setContentType("text/html");
            res.getWriter().println("<h2>Error Adding Patient</h2>");
            res.getWriter().println("<pre>");
            e.printStackTrace(res.getWriter());
            res.getWriter().println("</pre>");
        }
    }
}