
package servlets;

import check.CheckUser;
import db.HSQLDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HSQLDB.startHSQLDB();
        request.getSession().setAttribute("message", "");
        request.getSession().setAttribute("success", "");
        request.getSession().setAttribute("newUserMessage", "");
        request.getSession().setAttribute("exit", "");
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/sign-in");
        } else {
            response.sendRedirect(request.getContextPath() + "/welcome");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String message = new String();
 
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            message = "Необходимо ввести учётные данные";
        } else if(!(CheckUser.existsUser(username, password))) {
            message = "Имя пользователя и пароль не подходят";
        }
        
        if(message.isEmpty()) {
            HttpSession session = request.getSession(true);
            Cookie theCookie = new Cookie("username", username);
            response.setContentType("test/html");
            response.addCookie(theCookie);
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/welcome");
        }
        else {
            request.getSession().setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/sign-in");
            }
    }
}

