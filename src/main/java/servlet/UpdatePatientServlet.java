package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import Dao.HospitalDao;
import model.Patient;

@WebServlet("/updatePatient")
public class UpdatePatientServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            HospitalDao dao = new HospitalDao();

            List<Patient> list = dao.getAllPatients();

            // ✅ FIXED NAME
            req.setAttribute("patientList", list);

            String id = req.getParameter("id");

            if (id != null && !id.isEmpty()) {
                Patient p = dao.getPatientById(Integer.parseInt(id));
                req.setAttribute("patient", p);
            }

            RequestDispatcher rd = req.getRequestDispatcher("patientupdate.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            Patient p = new Patient(
                Integer.parseInt(req.getParameter("patientId")), // ✅ FIXED
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("age")),
                req.getParameter("gender"),
                req.getParameter("date"),
                req.getParameter("ailment"),
                req.getParameter("doctor")
            );

            HospitalDao dao = new HospitalDao();
            dao.updatePatient(p);

            res.sendRedirect("updatePatient");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}