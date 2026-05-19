<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Patient" %>

<!DOCTYPE html>
<html>
<head>
<title>Delete Patient</title>
<%@ include file="style.jsp" %>

<script>
function loadPatient() {
    var id = document.getElementById("patientId").value;
    if (id !== "") {
        window.location = "deletePatient?id=" + id;
    }
}

function confirmDelete() {
    return confirm("Are you sure you want to delete this patient?");
}
</script>

</head>
<body>

<div class="container">

<h2>Delete Patient</h2>

<%
Patient p = (Patient) request.getAttribute("patient");
List<Patient> list = (List<Patient>) request.getAttribute("patientList");
%>

<form action="deletePatient" method="post" onsubmit="return confirmDelete()">

<label>Select Patient</label>

<select name="patientId" id="patientId" onchange="loadPatient()" required>
    <option value="">-- Select Patient --</option>

<%
if (list != null && !list.isEmpty()) {
    for (Patient pt : list) {
%>

<option value="<%= pt.getId() %>"
    <%= (p != null && pt.getId() == p.getId()) ? "selected" : "" %>>
    ID: <%= pt.getId() %> - <%= pt.getName() %>
</option>

<%
    }
}
%>

</select>

<% if (p != null) { %>

<h3>Patient Details</h3>

<p><b>Name:</b> <%= p.getName() %></p>
<p><b>Age:</b> <%= p.getAge() %></p>
<p><b>Gender:</b> <%= p.getGender() %></p>
<p><b>Ailment:</b> <%= p.getAilment() %></p>
<p><b>Doctor:</b> <%= p.getDoctor() %></p>

<button type="submit">Delete Patient</button>

<% } %>

</form>

<a href="index.jsp">Back</a>

</div>

</body>
</html>