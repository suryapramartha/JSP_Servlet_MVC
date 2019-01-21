<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Project List</title>
</head>
<body>
<div class="container">
<h1 align="left">Project List</h1>
<table border="1" class="table">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Status</th>
		<th>Requirements</th>
		<th>PIC</th>
		<th>Action</th>
	</tr>
	<c:forEach var="x" items="${projectAttribute}">
		<c:url var="tempUpdate" value="ProjectController">
			<c:param name="command" value="EDIT"></c:param>
			<c:param name="projectId" value="${x.project_id}"></c:param>
		</c:url>
		<c:url var="tempDelete" value="ProjectController">
			<c:param name="command" value="DELETE"></c:param>
			<c:param name="projectId" value="${x.project_id}"></c:param>
		</c:url>
			<c:url var="tempNewPIC" value="ProjectController">
			<c:param name="command" value="NEWPIC"></c:param>
			<c:param name="projectId" value="${x.project_id}"></c:param>
			<c:param name="empl_name" value="${x.empl_name}"></c:param>
		</c:url>
		<tr>
			<td>${x.project_id}</td>
			<td>${x.project_name}</td>
			<td>${x.project_start_date}</td>
			<td>${x.project_end_date}</td>
			<td>${x.project_status}</td>
			<td>${x.project_requirement}</td>
			<td>${x.empl_name}</td>
			<td>
				<a href="${tempUpdate}">Edit</a> | 
				<a href="${tempDelete}" onclick="if(!(confirm('Are you sure?'))) return false">Delete</a> |
				<a href="${tempNewPIC}">Assign PIC</a> 
			</td>
		</tr>
	</c:forEach>
</table>
<input type="button" class="btn btn-primary" value="Back to Home" onclick="window.location='index.html';"></input> 
<c:url var="tempNewProject" value="ProjectController">
		<c:param name="command" value="NEW"></c:param>
</c:url>
 <a href="${tempNewProject}">
 	<button class="btn btn-success"> New Project </button>
 </a>   
 
</div>

</body>
</html>