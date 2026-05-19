<%@ page contentType="text/html;charset=UTF-8" %>
<%
String type = (String) request.getAttribute("type");
%>

<!DOCTYPE html>
<html>
<head>
<title>Criteria</title>
<%@ include file="style.jsp" %>
</head>
<body>

<div class="container">
<h2>Enter Criteria</h2>

<form action="ReportServlet" method="post">
<input type="hidden" name="type" value="<%=type%>">

<% if("date".equals(type)) { %>
<input type="date" name="from" required>
<input type="date" name="to" required>

<% } else if("ailment".equals(type)) { %>
<input name="ailment" placeholder="Ailment">

<% } else if("doctor".equals(type)) { %>
<input name="doctor" placeholder="Doctor">

<% } %>

<button>Generate</button>
</form>

<a href="index.jsp">Back</a>
</div>

</body>
</html>