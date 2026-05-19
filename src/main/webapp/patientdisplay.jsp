<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Patient" %>

<!DOCTYPE html>
<html>
<head>
<title>Patient List</title>

<style>
body {
    font-family: Arial;
    background: linear-gradient(135deg, #667eea, #764ba2);
    margin: 0;
}

.container {
    width: 70%;
    margin: 50px auto;
    background: white;
    padding: 25px;
    border-radius: 10px;
}

h2 {
    text-align: center;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

th {
    background: #667eea;
    color: white;
}

th, td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

a {
    display: block;
    text-align: center;
    margin-top: 15px;
    text-decoration: none;
    color: #667eea;
}
</style>

</head>
<body>

<div class="container">
<h2>Patient List</h2>

<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>Age</th>
<th>Gender</th>
<th>Ailment</th>
<th>Doctor</th>
</tr>

<%
List<Patient> list = (List<Patient>) request.getAttribute("patients");

if (list != null && !list.isEmpty()) {
    for (Patient p : list) {
%>

<tr>
<td><%= p.getId() %></td>
<td><%= p.getName() %></td>
<td><%= p.getAge() %></td>
<td><%= p.getGender() %></td>
<td><%= p.getAilment() %></td>
<td><%= p.getDoctor() %></td>
</tr>

<%
    }
} else {
%>

<tr>
<td colspan="6">No Patients Found</td>
</tr>

<%
}
%>

</table>

<a href="index.jsp">Back</a>

</div>

</body>
</html>