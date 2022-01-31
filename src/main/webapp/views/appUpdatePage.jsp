<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application Update form.</title>
</head>
<body>
<h2>Pls update Application details.</h2>
<form:form method="post" action="/appUpdating"  modelAttribute="app">
Enter Application name:<form:input type="text" path="appName"></form:input><br/><br/>
Enter Application url:<form:input type="text" path="appUrl"></form:input><br/><br/>
Enter Number of Departments:<form:input type="text" path="deptNo"></form:input><br/><br/>

<button  type="submit">Update</button>
<button >Cancel</button>
</form:form> <br> 

</body>
</html>