package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IndexServlet extends HttpServlet {

    final String index = "/WEB-INF/view/index.jsp";
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("users", users.values());
        request.getRequestDispatcher(index).forward(request, response);
    }
}

