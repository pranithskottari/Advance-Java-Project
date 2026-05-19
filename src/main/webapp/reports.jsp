<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>Reports</title>

<style>
body{
    font-family:Arial;
    background:#f2f2f2;
}

.container{
    width:500px;
    margin:40px auto;
    background:white;
    padding:20px;
    border-radius:10px;
}

input, select, button{
    width:100%;
    padding:10px;
    margin:10px 0;
}

button{
    background:green;
    color:white;
    border:none;
}
</style>

</head>
<body>

<div class="container">

<h2>Generate Reports</h2>

<form action="ReportServlet" method="post">

<label>Report Type</label>

<select name="type" required>

<option value="">Select Report</option>

<option value="date">Patients By Date</option>

<option value="ailment">Patients By Ailment</option>

<option value="doctor">Patients By Doctor</option>

</select>

<label>From Date</label>
<input type="date" name="from">

<label>To Date</label>
<input type="date" name="to">

<label>Ailment</label>
<input type="text" name="ailment">

<label>Doctor</label>
<input type="text" name="doctor">

<button type="submit">Generate Report</button>

</form>

<a href="index.jsp">Back</a>

</div>

</body>
</html>