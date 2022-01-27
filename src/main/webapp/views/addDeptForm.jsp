<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Department adding form.</title>
</head>
<body>
<h2>Pls add department.</h2>
<form  action="/deptAdding" method="post">
Enter Department Name : <input type="text" name="deptName"><br><br>
Enter Department Code : <input type="text" name="deptCode"><br><br>
<input type="submit" value="submit">
</form>

</body>
</html>