<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<html>
<head>
<title>Login Form</title>
</head>
<body>
<h2>Login Form</h2><br>
<form action="loginProcess" method="post">  
<span style="color:red;">${error}</span><br/>  
Username:<input type="text" name="username"/><br/><br/>  
Password:<input type="password" name="password"/><br/><br/>  
<input type="submit" value="login"/>  
</form> <br> 
<h3>If New User.....<a href="/register"> Sign Up </a> </h3><br>

<h3>Forgot Password..<a href="/resetPassword">Reset Password</a></h3>
</body>
</html>