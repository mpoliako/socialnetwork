<%-- 
    Document   : login
    Created on : 21.02.2013, 21:16:54
    Author     : Artem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Login</h3>
        <hr/>
        <form name="loginForm" method="POST" action="Controller">
            <input type="hidden" name="command" value ="login"/>
            Login:<br/>
            <input type="text" name="login" value =""> <br/>
            Password:<br/>
            <input type="password" name ="password" value="">
            <br/>
            <input type ="submit" value="Enter">
        </form>
        <hr/>
    </body>
</html>
