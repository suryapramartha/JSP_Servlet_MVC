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
<title>New Competency Assignment Form</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("currEmployeeAttribute");
	String id = list.get(0);
	String name = list.get(1);
	
%>
<%
	Boolean isEmpty=false;
	List<String> data = (List<String>)request.getAttribute("listSkillAttribute");
	if(data==null||data.isEmpty()){
		isEmpty=true;
	}else{
		isEmpty=false;
	}
%>
<body>
<div class="container">
<h1>New Competency Assignment Form</h1>
	<form action="AssignedSkillController" method="POST">
		<div class="form-group">
		<table border="1" width="75%">
			<tr>
				<td width="40%">Employee ID</td>
				<td><input type="text" name="emplIdForm" required="true" maxlength="5" class="form-control" value="<%=id %>" readonly="true"></input></td>
			</tr>
			<tr>
				<td width="40%">Employee Name</td>
				<td><input type="text" name="emplNameForm" required="true" maxlength="50" class="form-control" value="<%=name%>" readonly="true"></input></td>
			</tr>
			<tr>
				<td width="40%">Competency Name</td>
				<td><select name="ListSkill">
					<c:forEach var="x" items="${listSkillAttribute}">	
					<option value="${x.skill_id}">${x.skill_name}</option>	
  					</c:forEach>
  					</select>
  					<c:choose>
  							<c:when test="<%=isEmpty%>">
    							<c:out value="${'List Skills not found or You already Assigned all available Skills !!'}"></c:out>
  							</c:when>
					</c:choose>
				</td>
			</tr>
		</table>
		<input name="command" value="CREATE" type="hidden"></input>	
		
		
		<br><input type="submit" value="Create" class="btn btn-success" >
		</div>
	</form>
		<c:url var="tempCancel" value="AssignedSkillController">
			<c:param name="command" value="LOAD_ALL"></c:param>
			<c:param name="employeeId" value="<%=id%>"></c:param>
		</c:url>
	<a href="${tempCancel}">
 	<button class="btn btn-primary">Cancel</button>
 </a> 
</div>	

</body>
</html>