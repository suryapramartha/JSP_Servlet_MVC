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
<title>New PIC Assignment Form</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("projectSelectedAttribute");
	String id = list.get(0);
	String name = list.get(1);
	String start_date = list.get(2);
	String end_date = list.get(3);
	String status = list.get(4);
	String req = list.get(5);
	String pic = list.get(6);
%>
<%
	Boolean isEmpty=false;
	List<String> data = (List<String>)request.getAttribute("listPICAttribute");
	if(data==null||data.isEmpty()){
		isEmpty=true;
	}else{
		isEmpty=false;
	}
%>
<body>
<div class="container">
<h1>New PIC Assignment  Form</h1>
	<form action="ProjectController" method="POST" id="inputForm">
		<div class="form-group">
		<table border="1" width="75%">
			<tr>
				<td width="40%">Project ID</td>
				<td><input type="text" name="projectIdForm" readonly="readonly" value="<%=id %>" class="form-control"></input></td>
			</tr>
			<tr>
				<td width="40%">Project Name</td>
				<td><input type="text" name="projectNameForm" required="true" readonly="readonly" value="<%=name %>" maxlength="200" class="form-control"></input></td>
			</tr>
			<tr>
				<td width="40%">Project Start Date</td>
				<td><input type="date" id="projectStartForm" name="projectStartForm" readonly="readonly" value="<%=start_date %>" required="true"  class="form-control"></input></td>
			</tr>
			<tr>
				<td width="40%">Project End Date</td>
				<td><input type="date" id="projectEndForm" name="projectEndForm" readonly="readonly" value="<%=end_date %>" required="true"  class="form-control"></input></td>
			</tr>
			<tr>
				<td width="40%">Project Requirement</td>
				<td><select name="ListSkill" disabled="disabled">
					<c:forEach var="x" items="${listSkillAttribute}">	
					<option value="${x.skill_name}">${x.skill_name}</option>	
  					</c:forEach>
  					</select>
				</td>
			</tr>
			<tr>
				<td width="40%">PIC</td>
				<td><select name="ListPIC" >
					<c:forEach var="x" items="${listPICAttribute}">	
					<option value="${x.empl_name}">${x.empl_name}</option>	
  					</c:forEach>
  					</select>
  					<c:choose>
  						<c:when test="<%=isEmpty%>">
    						<c:out value="${'There are currently no PIC that associated with the Project requirement !!'}"></c:out>
  						</c:when>
					</c:choose>	
				</td>
			</tr>
		</table>
		<input name="command" value="CREATEPIC" type="hidden"></input>	
			
		<br><input type="submit" value="Assign PIC" class="btn btn-success" >
		</div>
	</form>
		<c:url var="tempCancel" value="ProjectController">
			<c:param name="command" value="LOAD_ALL"></c:param>
		</c:url>
	<a href="${tempCancel}">
 	<button class="btn btn-primary">Cancel</button>
 </a> 
</div>	

</body>
</html>