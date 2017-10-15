
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация</title>
    </head>
    <body> 
        <p style="color:red"><%= request.getSession().getAttribute("newUserMessage") %>
           <% request.getSession().setAttribute("newUserMessage", ""); %>
        </p>
        <form action="CheckNewUserServlet" method="POST">
            Имя пользователя <input type="text" name="username">
            <p>Пароль <input type="password" name="password"</p>
            <p>Повтор пароля <input type="password" name="repeatPassword"></p>
            <p><input type="submit" value="Зарегистрироваться"</p>
        </form>
    </body>
</html>
