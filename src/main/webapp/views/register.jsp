<html>
<head>
<title>Register From</title>
</head>
<body>
<h2>Registration Form</h2><br>
<form action="/registration" method="post">  
Full name:<input type="text" name="fullName"/><br/><br/>  
username :<input type="text" name="userName"/><br/><br/>  
Password:<input type="password" name="password"/><br/><br/> 
Mobile No.:<input type="text" name="mobno"/><br/><br/> 
Email:<input type="text" name="email"/><br/><br/>  
<label for="Department">Choose a deepartment:</label>

<select name="departmentName" id="dept">
  <option value="dept1">dept1</option>
  <option value="dept2">dept2</option>
  <option value="dept3">dept3</option>
  <option value="dept4">dept4</option>
</select><br><br>
Role : <input type="radio" id="admin" name="role" value="admin">
<label for="admin">Admin</label>
<input type="radio" id="user" name="role" value="user">
<label for="user">User</label><br> <br>
<input type="submit" value="register"/>  
</form> <br> 

</body>
</html>