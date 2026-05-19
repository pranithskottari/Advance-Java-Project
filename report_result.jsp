<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,model.Patient"%>

<!DOCTYPE html>
<html>
<head>
<title>Report Result</title>

<style>
body{
    font-family:Arial;
    background:#f2f2f2;
}

.container{
    width:800px;
    margin:40px auto;
    background:white;
    padding:20px;
    border-radius:10px;
}

table{
    width:100%;
    border-collapse:collapse;
}

th,td{
    border:1px solid black;
    padding:10px;
    text-align:center;
}

th{
    background:#ddd;
}
</style>

</head>
<body>

<div class="container">

<h2>Report Result</h2>

<table>

<tr>
<th>ID</th>
<th>Name</th>
<th>Age</th>
<th>Gender</th>
<th>Date</th>
<th>Ailment</th>
<th>Doctor</th>
</tr>

<%
List<Patient> list =
(List<Patient>) request.getAttribute("patients");

if(list != null && !list.isEmpty()){

    for(Patient p : list){
%>

<tr>
<td><%= p.getId() %></td>
<td><%= p.getName() %></td>
<td><%= p.getAge() %></td>
<td><%= p.getGender() %></td>
<td><%= p.getDate() %></td>
<td><%= p.getAilment() %></td>
<td><%= p.getDoctor() %></td>
</tr>

<%
    }
}
else{
%>

<tr>
<td colspan="7">No Records Found</td>
</tr>

<%
}
%>

</table>

<br>

<a href="reports.jsp">Back</a>

</div>

</body>
</html>