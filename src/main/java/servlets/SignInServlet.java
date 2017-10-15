
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            request.getRequestDispatcher("/pages/signIn.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/welcome");
        }   
    }
}
