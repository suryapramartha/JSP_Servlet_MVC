<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Competency List</title>
</head>
<body>
<div class="container">
<h1 align="left">Competency List</h1>
<table border="1" class="table">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Description</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach var="x" items="${skillAttribute}">
		<c:url var="tempUpdate" value="SkillController">
			<c:param name="command" value="EDIT"></c:param>
			<c:param name="skillId" value="${x.skill_id}"></c:param>
		</c:url>
		<c:url var="tempDelete" value="SkillController">
			<c:param name="command" value="DELETE"></c:param>
			<c:param name="skillId" value="${x.skill_id}"></c:param>
		</c:url>
		<tr>
			<td>${x.skill_id}</td>
			<td>${x.skill_name}</td>
			<td>${x.skill_desc}</td>
			<td>${x.skill_status}</td>
			<td><a href="${tempUpdate}">Edit</a> | 
			<a href="${tempDelete}" onclick="if(!(confirm('Are you sure?'))) return false">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<input type="button" class="btn btn-primary" value="Back to Home" onclick="window.location='index.html';"></input> 
<input type="button" class="btn btn-success" value="Add New Skill" onclick="window.location='newSkill.html';"></input>
 
</div>

</body>
</html>