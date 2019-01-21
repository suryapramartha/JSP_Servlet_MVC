<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Employee List</title>
</head>
<body>
<div class="container">
<h1 align="left">Employee List</h1>
<table border="1" class="table">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Status</th>
		<th>Age</th>
		<th>Address</th>
		<th>Action</th>
	</tr>
	<c:forEach var="x" items="${employeeAttribute}">
		<c:url var="tempUpdate" value="EmployeeController">
			<c:param name="command" value="EDIT"></c:param>
			<c:param name="employeeId" value="${x.empl_id}"></c:param>
		</c:url>
		<c:url var="tempDelete" value="EmployeeController">
			<c:param name="command" value="DELETE"></c:param>
			<c:param name="employeeId" value="${x.empl_id}"></c:param>
		</c:url>
		<c:url var="tempCompetency" value="AssignedSkillController">
			<c:param name="command" value="LOAD_ALL"></c:param>
			<c:param name="employeeId" value="${x.empl_id}"></c:param>
		</c:url>
		
		<tr>
			<td>${x.empl_id}</td>
			<td>${x.empl_name}</td>
			<td>${x.empl_status}</td>
			<td>${x.empl_age}</td>
			<td>${x.empl_address}</td>
			<td>
				<a href="${tempUpdate}">Edit</a> | 
				<a href="${tempDelete}" onclick="if(!(confirm('Are you sure?'))) return false">Delete</a> | 			
				<a href="${tempCompetency}">View Competency</a>
			</td>
		</tr>
	</c:forEach>
</table>
<input type="button" class="btn btn-primary" value="Back to Home" onclick="window.location='index.html';"></input> 
<input type="button" class="btn btn-success" value="Add New Employee" onclick="window.location='newEmployee.html';"></input>
 
</div>

</body>
</html>