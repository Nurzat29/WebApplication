
<%@page import="check.MyCookies"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход</title>
    </head>
    <body>
        <p style="color: red">
           <%= request.getSession().getAttribute("message") %>
        </p>
           <%= request.getSession().getAttribute("success")%>
        <p style="color: green">
           <%= request.getSession().getAttribute("exit")%>
        </p>
           <% request.getSession().setAttribute("message", "");%>
           <% request.getSession().setAttribute("success", "");%>
           <% request.getSession().setAttribute("exit", "");%>
        
        <form action="StartServlet" method="POST">
            <% Cookie cookies[] = request.getCookies(); %>
            <% System.out.println("cookie name:" + cookies[1].getName()); %>
            <% if(cookies[1].getName().equals("username")) { MyCookies.setCookie(cookies[1].getValue()); System.out.println("MyCookie:" + MyCookies.getCookie()); } %>
            <% System.out.println("This cookie: " + cookies[1].getValue()); %>
            Имя пользователя <input type="text" name="username" value="<%= MyCookies.getCookie() %>">
            <a href="<%= request.getContextPath() %>/sign-up">Регистрация</a>
            <p>Пароль <input type="password" name="password"></p>
            <input type="submit" value="Войти">
        </form>
    </body>
</html>
