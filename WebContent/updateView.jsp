<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Employee</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("employeeSelectedAttribute");
	String id = list.get(0);
	String name = list.get(1);
	String status = list.get(2);
	String age = list.get(3);
	String address = list.get(4);
%>
<body>
	<h1>Update Employee</h1>
	<form action="EmployeeController" method="POST">
		<table border="1">
			<tr>
				<td>Employee ID</td>
				<td><input type="text" disabled="true" value="<%=id%>"></input></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td><input type="text" name="emplNameForm" value="<%=name%>" required="true"></input></td>
			</tr>
			<tr>
				<td>Employee Status</td>
				<td><input type="text" disabled="true" value="<%=status%>"></input></td>
			</tr>
			<tr>
				<td>Employee Age</td>
				<td><input type="number" name="emplAgeForm" min="0" value="<%=age%>" required="true"></input></td>
			</tr>
			<tr>
				<td>Employee Address</td>
				<td><input type="text" name="emplAddressForm" value="<%=address%>" required="true" ></input></td>
			</tr>
		</table>
		<input name="command" value="UPDATE" type="hidden"></input>
		<input name="emplIdForm" value="<%=id%>" type="hidden"></input>
		<br><input type="submit" value="Update">
	</form>
	<h2><a href="EmployeeController">Back</a></h2>
</body>
</html>