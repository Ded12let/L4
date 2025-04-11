import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DB;

import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        String role = DB.checkUserAndGetRole(name, pass);
        if (role == null) {
            response.sendRedirect("index.jsp"); // неверный логин/пароль
        } else if (role.equals("admin")) {
            response.sendRedirect("admin.jsp");
        } else if (role.equals("user")) {
            response.sendRedirect("user.jsp");
        } else {
            response.sendRedirect("index.jsp"); // неизвестная роль
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}