<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show all Applications</title>

</head>

<body>
    <h1>All Application List</h1>
    <span style="color:red;">${error}</span><br/>


<c:if test="${not empty allAppList}">
    
    <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">appId</th>
      <th scope="col">Application Name</th>
      <th scope="col">Applicattion Url</th>
      <th scope="col">isActive</th>
      <th scope="col">modify</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${allAppList}" var="appList"><br>
    <tr>
   
      <td></td>
      <td>${appList.getAppId()}</td>
      <td>${appList.getAppName()}</td>
      <td>${appList.getAppUrl()}</td>
      <td>${appList.getIsActive()}</td>
      <td><a href="/appUpationForm/${appList.getAppId()}">Edit</a> <a href="/deleteApp/${appList.getAppId()}">Delete</a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
    

</c:if>
<br>
<div><a href="views/adminDashboard.jsp">Got to Home</a></div>

</body>
</html>