<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .warning{
            color: red;
            font-size: 120%;
        }
    </style>
</head>
<body>
    <form method="get" action="newuserServlet">
    <h3> Create new user: </h3>

        Login : <label>
        <input type="text" name="login"/>
    </label><br/>
        Password : <label>
        <input type="password" name="password"/>
    </label><br/>
        Confirm password : <label>
        <input type="password" name="confpass"/>
    </label><br/>
    <input type="submit" value="Create"/><br/>

        <p><span class="warning"><%= request.getAttribute("warning")%></span></p>
</form>
</body>
</html>
