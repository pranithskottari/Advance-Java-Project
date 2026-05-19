<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Patient" %>

<html>
<head>
<title>Update Patient</title>

<style>
body { font-family: Arial; background: #f2f2f2; }
.container {
    width: 500px; margin: 50px auto;
    padding: 20px; background: white;
    border-radius: 10px;
}
select, input {
    width: 100%; padding: 10px; margin: 10px 0;
}
button {
    padding: 10px; width: 100%;
    background: green; color: white;
    border: none;
}
</style>

<script>
function loadPatient() {
    var id = document.getElementById("patientId").value;
    if (id !== "") {
        window.location = "updatePatient?id=" + id;
    }
}
</script>

</head>

<body>

<div class="container">

<h2>Update Patient</h2>

<%
Patient p = (Patient) request.getAttribute("patient");
List<Patient> list = (List<Patient>) request.getAttribute("patientList");
%>

<form action="updatePatient" method="post">

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
} else {
%>

<option disabled>No patients found</option>

<%
}
%>

</select>

<!-- FORM FIELDS -->

<input type="text" name="name" placeholder="Name"
       value="<%= (p != null) ? p.getName() : "" %>">

<input type="number" name="age" placeholder="Age"
       value="<%= (p != null) ? p.getAge() : "" %>">

<input type="text" name="gender" placeholder="Gender"
       value="<%= (p != null) ? p.getGender() : "" %>">

<input type="date" name="date"
       value="<%= (p != null && p.getDate()!=null) ? p.getDate() : "" %>">

<input type="text" name="ailment" placeholder="Ailment"
       value="<%= (p != null) ? p.getAilment() : "" %>">

<input type="text" name="doctor" placeholder="Doctor"
       value="<%= (p != null) ? p.getDoctor() : "" %>">

<button type="submit">Update Patient</button>

</form>

</div>

</body>
</html>