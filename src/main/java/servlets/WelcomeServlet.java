
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/sign-in");
        } else {
            request.getRequestDispatcher("/pages/welcome.jsp").forward(request, response);
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        request.getSession().setAttribute("exit", "Вы вышли из приложения");
        response.sendRedirect(request.getContextPath() + "/sign-in");
    }
}
