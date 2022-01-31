<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application adding form.</title>
</head>
<body>
<h2>Pls add New Application.</h2>
<form  action="/addingApp" method="post">
Enter Application Name : <input type="text" name="appName"><br><br>
Enter Application Url : <input type="text" name="appUrl"><br><br>
Enter Number of Departments : <input type="text" name="deeptNo"><br><br>
<input type="submit" value="submit">
</form>

</body>
</html>