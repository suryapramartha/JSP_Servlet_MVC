<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ page import="java.util.*" %>    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Competency List</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("currEmployeeAttribute");
	String id = list.get(0);
	String name = list.get(1);
	String age = list.get(2);
	String address = list.get(3);
%>
<body>
<div class="container">
<h1 align="left">Competency List</h1>
<div class="jumbotron">
<p>Employee ID 		: <%=id%></p>
<p>Employee Name    : <%=name%></p>
<p>Employee Age     : <%=age%></p>
<p>Employee Address : <%=address%></p><br>
</div>
<table border="1" class="table">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Competency</th>
		<th>Action</th>
	</tr>
	<c:forEach var="x" items="${assignedSkillAttribute}">
		<c:url var="tempDelete" value="AssignedSkillController">
			<c:param name="command" value="DELETE"></c:param>
			<c:param name="emplId" value="${x.empl_id}"></c:param>
			<c:param name="skillId" value="${x.skill_id}"></c:param>
			<c:param name="employeeId" value="${x.empl_id}"></c:param>
		</c:url>
		<tr>
			<td>${x.empl_id}</td>
			<td>${x.empl_name}</td>
			<td>${x.skill_name}</td>
			<td><a href="${tempDelete}" onclick="if(!(confirm('Are you sure?'))) return false">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<input type="button" class="btn btn-primary" value="Home" onclick="window.location='index.html';"></input>
<input type="button" class="btn btn-primary" value="Back to Employee List" onclick="window.location='EmployeeController';"></input> 
	<c:url var="tempEmployee" value="AssignedSkillController">
			<c:param name="command" value="NEW"></c:param>
			<c:param name="employeeId" value="<%=id%>"></c:param>
		</c:url>
 <a href="${tempEmployee}">
 	<button class="btn btn-success"> Assign New Competency </button>
 </a>   

</div>

</body>
</html>