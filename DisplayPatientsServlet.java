package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import Dao.HospitalDao;
import model.Patient;
@WebServlet("/DisplayPatientsServlet")
public class DisplayPatientsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        try {
            HospitalDao dao = new HospitalDao();
            List<Patient> list = dao.getAllPatients();

            System.out.println("Patients fetched: " + list.size()); // DEBUG

            req.setAttribute("patients", list);

            RequestDispatcher rd = req.getRequestDispatcher("patientdisplay.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();

            res.setContentType("text/html");
            res.getWriter().println("<h2>Error:</h2><pre>");
            e.printStackTrace(res.getWriter());
            res.getWriter().println("</pre>");
        }
    }
}