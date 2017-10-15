
<%@page import="check.CheckTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная страница</title>
    </head>
    <body>
        <h1><%= CheckTime.getTimesOfDay() %><%= session.getAttribute("username") %> 
        !</h1> 
        <form action="ExitServlet">
            <input type="submit" value="Выйти">
        </form>
    </body>
</html>
