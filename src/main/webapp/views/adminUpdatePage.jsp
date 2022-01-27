<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<title>Update admin data</title>
<style>  
.error{color:red}  
</style> 
</head>
<body>
<h2>Admin Updatation Form</h2><br>
<form:form method="post" action="/updateAdminData"  modelAttribute="user">  
<div>
<form:errors path="userUniqueError" cssClass="error"></form:errors> <br/><br/>
</div>
Full name:<form:input type="text" path="fullName"></form:input>  <form:errors path="fullName" cssClass="error"></form:errors> <br/><br/>  
user Name :<form:input type="text" path="userName"></form:input> <form:errors path="userName" cssClass="error"></form:errors><br/><br/>  
Password:<form:input type="password" path="password"></form:input> <form:errors path="password" cssClass="error"></form:errors><br/><br/> 
Mobile No.:<form:input type="text" path="mobileNo"></form:input> <form:errors path="mobileNo" cssClass="error"></form:errors><br/><br/> 
Email:<form:input type="text" path="email"></form:input> <form:errors path="email" cssClass="error"></form:errors> <br/><br/> 
<label for="Department">Choose a department:</label>

<form:select path="departmentName" id="dept">
  <form:option value="dept1">dept1</form:option>
  <form:option value="dept2">dept2</form:option>
  <form:option value="dept3">dept3</form:option>
  <form:option value="dept4">dept4</form:option>
</form:select><br><br>
Role : <form:radiobutton id="admin" path="role" value="admin"/>
<label for="admin">Admin</label>
<form:radiobutton id="user" path="role" value="user"/>
<label for="user">User</label><br> <br>
<button  type="submit">Update</button>
<button >Cancel</button>
<%-- <form:input type="submit" value="register"/>   --%>
</form:form> <br> 
</body>
</html>