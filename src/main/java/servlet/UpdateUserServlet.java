package servlet;

import model.User;
import util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UpdateUserServlet extends HttpServlet {

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
        final String id = request.getParameter("id");

        if (Utils.idIsInvalid(id, users)) {
            response.sendRedirect(request.getContextPath() + "/");
        }

        final User user = users.get(Integer.parseInt(id));
        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        final String id = request.getParameter("id");
        final String name = request.getParameter("name");
        final String age = request.getParameter("age");

        users.get(Integer.parseInt(id)).setName(name);
        users.get(Integer.parseInt(id)).setAge(Integer.parseInt(age));

        response.sendRedirect(request.getContextPath() + "/");

    }


}
