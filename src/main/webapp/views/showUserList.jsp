<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show all Users</title>

</head>

<body>
    <h1>Users List</h1>
    <span style="color:red;">${error}</span><br/>


<c:if test="${not empty users}">
    
    <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Full Name</th>
      <th scope="col">UserName</th>
      <th scope="col">MobileNo</th>
      <th scope="col">role</th>
      <th scope="col">isActive</th>
      <th scope="col">modify</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${users}" var="user"><br>
    <tr>
   
      <td></td>
      <td>${user.getUserId()}</td>
      <td>${user.getFullName()}</td>
      <td>${user.getUserName()}</td>
      <td>${user.getMobileNo()}</td>
      <td>${user.getRole()}</td>
      <td>${user.getIsActive()}</td>
      <td><a href="/userUpdationForm/${user.getUserId()}">Edit</a> <a href="/deleteUser/${user.getUserId()}">Delete</a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
    

</c:if>
<br>
<div><a href="views/superAdminDashboard.jsp">Got to Home</a></div>

</body>
</html>