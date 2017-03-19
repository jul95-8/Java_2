<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="get" action="newuserServlet">
    <h3> Create new user: </h3>

        Login : <input type="text" name="login"/><br/>
        Password : <input type="text" name="password"/><br/>
        Confirm password : <input type="text" name="confpass"/><br/>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
