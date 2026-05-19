<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Hospital</title>
<%@ include file="style.jsp" %>
</head>
<body>

<div class="container">
<h2>Hospital Management System</h2>

<a href="patientadd.jsp">Add Patient</a>

<a href="DisplayPatientsServlet">View Patients</a>

<a href="updatePatient">Update Patient</a>

<!-- ✅ FIXED -->
<a href="deletePatient">Delete Patient</a>

<a href="reports.jsp">Reports</a>

</div>

</body>
</html>