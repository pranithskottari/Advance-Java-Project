package Dao;

import java.sql.*;
import java.util.*;
import model.Patient;

public class HospitalDao {

    // ==========================
    // DATABASE CONFIGURATION
    // ==========================
    private String jdbcURL =
            "jdbc:mysql://localhost:3306/hospitaldb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    // ==========================
    // DATABASE CONNECTION
    // ==========================
    private Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                jdbcURL,
                jdbcUsername,
                jdbcPassword
        );
    }

    // ==========================
    // INSERT PATIENT
    // ==========================
    public void insertPatient(Patient p) throws Exception {

        String sql =
                "INSERT INTO patients " +
                "(PatientName, Age, Gender, AdmissionDate, Ailment, AssignedDoctor) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setDate(4, java.sql.Date.valueOf(p.getDate()));
            ps.setString(5, p.getAilment());
            ps.setString(6, p.getDoctor());

            ps.executeUpdate();
        }
    }

    // ==========================
    // GET ALL PATIENTS
    // ==========================
    public List<Patient> getAllPatients() throws Exception {

        List<Patient> list = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Patient p = new Patient(
                        rs.getInt("PatientID"),
                        rs.getString("PatientName"),
                        rs.getInt("Age"),
                        rs.getString("Gender"),
                        rs.getDate("AdmissionDate").toString(),
                        rs.getString("Ailment"),
                        rs.getString("AssignedDoctor")
                );

                list.add(p);
            }
        }

        return list;
    }

    // ==========================
    // GET PATIENT BY ID
    // ==========================
    public Patient getPatientById(int id) throws Exception {

        Patient p = null;

        String sql = "SELECT * FROM patients WHERE PatientID=?";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    p = new Patient(
                            rs.getInt("PatientID"),
                            rs.getString("PatientName"),
                            rs.getInt("Age"),
                            rs.getString("Gender"),
                            rs.getDate("AdmissionDate").toString(),
                            rs.getString("Ailment"),
                            rs.getString("AssignedDoctor")
                    );
                }
            }
        }

        return p;
    }

    // ==========================
    // UPDATE PATIENT
    // ==========================
    public void updatePatient(Patient p) throws Exception {

        String sql =
                "UPDATE patients SET " +
                "PatientName=?, " +
                "Age=?, " +
                "Gender=?, " +
                "AdmissionDate=?, " +
                "Ailment=?, " +
                "AssignedDoctor=? " +
                "WHERE PatientID=?";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setDate(4, java.sql.Date.valueOf(p.getDate()));
            ps.setString(5, p.getAilment());
            ps.setString(6, p.getDoctor());
            ps.setInt(7, p.getId());

            ps.executeUpdate();
        }
    }

    // ==========================
    // DELETE PATIENT
    // ==========================
    public void deletePatient(int id) throws Exception {

        String sql = "DELETE FROM patients WHERE PatientID=?";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    // ==========================
    // GET NEXT PATIENT ID
    // ==========================
    public int getNextPatientId() {

        int id = 1;

        String sql =
                "SELECT IFNULL(MAX(PatientID), 0) + 1 FROM patients";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    // ==========================
    // REPORT BY DATE
    // ==========================
    public List<Patient> getPatientsByDate(String from, String to)
            throws Exception {

        List<Patient> list = new ArrayList<>();

        String sql =
                "SELECT * FROM patients " +
                "WHERE AdmissionDate BETWEEN ? AND ?";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDate(1, java.sql.Date.valueOf(from));
            ps.setDate(2, java.sql.Date.valueOf(to));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Patient p = new Patient(
                            rs.getInt("PatientID"),
                            rs.getString("PatientName"),
                            rs.getInt("Age"),
                            rs.getString("Gender"),
                            rs.getDate("AdmissionDate").toString(),
                            rs.getString("Ailment"),
                            rs.getString("AssignedDoctor")
                    );

                    list.add(p);
                }
            }
        }

        return list;
    }

    // ==========================
    // REPORT BY AILMENT
    // ==========================
    public List<Patient> getPatientsByAilment(String ailment)
            throws Exception {

        List<Patient> list = new ArrayList<>();

        String sql =
                "SELECT * FROM patients " +
                "WHERE Ailment LIKE ?";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + ailment + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Patient p = new Patient(
                            rs.getInt("PatientID"),
                            rs.getString("PatientName"),
                            rs.getInt("Age"),
                            rs.getString("Gender"),
                            rs.getDate("AdmissionDate").toString(),
                            rs.getString("Ailment"),
                            rs.getString("AssignedDoctor")
                    );

                    list.add(p);
                }
            }
        }

        return list;
    }

    // ==========================
    // REPORT BY DOCTOR
    // ==========================
    public List<Patient> getPatientsByDoctor(String doctor)
            throws Exception {

        List<Patient> list = new ArrayList<>();

        String sql =
                "SELECT * FROM patients " +
                "WHERE AssignedDoctor LIKE ?";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + doctor + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Patient p = new Patient(
                            rs.getInt("PatientID"),
                            rs.getString("PatientName"),
                            rs.getInt("Age"),
                            rs.getString("Gender"),
                            rs.getDate("AdmissionDate").toString(),
                            rs.getString("Ailment"),
                            rs.getString("AssignedDoctor")
                    );

                    list.add(p);
                }
            }
        }

        return list;
    }
}