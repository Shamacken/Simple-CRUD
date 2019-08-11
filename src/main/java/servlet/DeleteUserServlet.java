package servlet;

import model.User;
import util.Utils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeleteUserServlet extends HttpServlet {

    private Map<Integer, User> users;

    @Override
    public void init() throws ServletException {
        final Object users = getServletContext().getAttribute("users");

        if(users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("You are repo does not exist!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if (Utils.idIsNumber(request)) {
            final String id = request.getParameter("id");
            users.remove(Integer.parseInt(id));
           //users.remove(Integer.parseInt(request.getParameter("id")));
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
