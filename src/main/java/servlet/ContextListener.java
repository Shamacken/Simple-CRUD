package servlet;

import model.User;
import util.Utils;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, User> users;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();
        users = new ConcurrentHashMap<>();
        servletContext.setAttribute("users", users);

        final User user = new User(1, "Dmit", 19);
        this.users.put(user.getId(), user);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //close resources
        users = null;
    }
}
