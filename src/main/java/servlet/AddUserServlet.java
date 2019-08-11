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
import java.util.concurrent.atomic.AtomicInteger;

public class AddUserServlet extends HttpServlet {

    private Map<Integer, User> users;
    private AtomicInteger id;

    @Override
    public void init() throws ServletException {
        final Object users = getServletContext().getAttribute("users");

        if(users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("You are repo does not exist!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }

        id = new AtomicInteger(2);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (Utils.requestIsValid(request)) {
            final String name = request.getParameter("name");
            final String age = request.getParameter("age");
            final int id = this.id.getAndIncrement();
            final User user = new User(id, name, Integer.parseInt(age));

            users.put(id, user);
        }

        //клиент делает новый запрос
        response.sendRedirect(request.getContextPath() + "/");
    }
}
