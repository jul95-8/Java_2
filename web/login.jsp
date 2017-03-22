<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="get" action="loginServlet">
        <h3> Enter your login and password! </h3>

        Login : <label>
        <input type="text" name="login"/>
    </label><br/>
        Password : <label>
        <input type="password" name="password"/>
    </label><br/>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
