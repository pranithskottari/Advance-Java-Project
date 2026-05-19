package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import Dao.HospitalDao;
import model.Patient;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    // LOAD PAGE + SELECT PATIENT
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            HospitalDao dao = new HospitalDao();

            // Load all patients for dropdown
            List<Patient> list = dao.getAllPatients();
            req.setAttribute("patientList", list);

            // If user selected a patient
            String id = req.getParameter("id");

            if (id != null && !id.isEmpty()) {
                Patient p = dao.getPatientById(Integer.parseInt(id));
                req.setAttribute("patient", p);
            }

            RequestDispatcher rd = req.getRequestDispatcher("patientdelete.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            int id = Integer.parseInt(req.getParameter("patientId"));

            HospitalDao dao = new HospitalDao();
            dao.deletePatient(id);

            res.sendRedirect("DisplayPatientsServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}