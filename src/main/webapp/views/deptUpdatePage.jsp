<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Department Update form.</title>
</head>
<body>
<h2>Pls update department details.</h2>
<form:form method="post" action="/deptUpdating"  modelAttribute="dept">
Enter Department name:<form:input type="text" path="deptName"></form:input><br/><br/>
Enter Department code:<form:input type="text" path="deptCode"></form:input><br/><br/>

<button  type="submit">Update</button>
<button >Cancel</button>
</form:form> <br> 

</body>
</html>