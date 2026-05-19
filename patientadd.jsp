<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="Dao.HospitalDao" %>

<!DOCTYPE html>
<html>
<head>
<title>Add Patient</title>

<style>
body{
    font-family: Arial;
    background:#f2f2f2;
}

.container{
    width:500px;
    margin:40px auto;
    background:white;
    padding:20px;
    border-radius:10px;
    box-shadow:0 0 10px gray;
}

h2{
    text-align:center;
}

input, select{
    width:100%;
    padding:10px;
    margin:10px 0;
}

button{
    width:100%;
    padding:10px;
    background:green;
    color:white;
    border:none;
    cursor:pointer;
}

a{
    display:block;
    text-align:center;
    margin-top:15px;
}
</style>

</head>
<body>

<div class="container">

<h2>Add Patient</h2>

<%
int nextId = 1;

try {
    HospitalDao dao = new HospitalDao();
    nextId = dao.getNextPatientId();
}
catch(Exception e){
    nextId = 1;
}
%>

<form action="AddPatientServlet" method="post">

<label>Patient ID</label>
<input type="text" value="<%= nextId %>" readonly>

<label>Patient Name</label>
<input type="text" name="name" required>

<label>Age</label>
<input type="number" name="age" required>

<label>Gender</label>
<select name="gender" required>
    <option value="">Select Gender</option>
    <option>Male</option>
    <option>Female</option>
    <option>Other</option>
</select>

<label>Admission Date</label>
<input type="date" name="date" required>

<label>Ailment</label>
<input type="text" name="ailment" required>

<label>Assigned Doctor</label>
<input type="text" name="doctor" required>

<button type="submit">Add Patient</button>

</form>

<a href="index.jsp">Back to Home</a>

</div>

</body>
</html>