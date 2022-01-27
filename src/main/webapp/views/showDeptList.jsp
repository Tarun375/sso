<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show all Departments</title>

</head>

<body>
    <h1>Department List</h1>
    <span style="color:red;">${error}</span><br/>


<c:if test="${not empty deptList}">
    
    <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">deptId</th>
      <th scope="col">Department Name</th>
      <th scope="col">Department Code</th>
      <th scope="col">isActive</th>
      <th scope="col">modify</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${deptList}" var="dept"><br>
    <tr>
   
      <td></td>
      <td>${dept.getDeptId()}</td>
      <td>${dept.getDeptName()}</td>
      <td>${dept.getDeptCode()}</td>
      <td>${dept.getIsActive()}</td>
      <td><a href="/userUpdationForm/${dept.getDeptId()}">Edit</a> <a href="/deleteUser/${dept.getDeptId()}">Delete</a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
    

</c:if>
<br>
<div><a href="views/adminDashboard.jsp">Got to Home</a></div>

</body>
</html>