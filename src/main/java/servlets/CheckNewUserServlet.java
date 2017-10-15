
package servlets;

import JavaBeans.User;
import check.CheckUser;
import db.HSQLDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckNewUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String repeatPassword = request.getParameter("repeatPassword").trim();
        String newUserMessage = new String();
   
        if(username.length() < 5 || CheckUser.IsRussian(username) || !(CheckUser.haveNumber(username))) {
            newUserMessage = "Имя пользователя должно быть длиннее 4 символов и состоять из цифр и букв английского алфавита";
        } else if(password.isEmpty()) {
            newUserMessage = "Заполните поле \"Пароль\"";
        } else if(!(password.equals(repeatPassword))) {
            newUserMessage = "Пароль и повтор пороля не совпадают";
        } else if(password.length() < 8 || !(CheckUser.haveNumber(password)) || !(CheckUser.haveUpperCase(password)) || 
                !(CheckUser.haveLowerCase(password))) {
            newUserMessage = "Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов";
        } else if(CheckUser.existsUsername(username.toLowerCase())) {
            newUserMessage = "Пользователь с таким именем уже зарегистрирован";
        }
        
        if(newUserMessage.isEmpty()) {
            Cookie theCookie = new Cookie("username", username);
            response.setContentType("test/html");
            response.addCookie(theCookie);
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            User newUser = new User(username, password);
            HSQLDB db = new HSQLDB();
            db.fillTable(newUser);
            request.getSession().setAttribute("message", "");
            response.sendRedirect(request.getContextPath() + "/welcome");
        } else {
            request.getSession().setAttribute("newUserMessage", newUserMessage);
            response.sendRedirect(request.getContextPath() + "/sign-up");
        }
    }
}
