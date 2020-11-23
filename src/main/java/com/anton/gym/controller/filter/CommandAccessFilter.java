package com.anton.gym.controller.filter;

import com.anton.gym.controller.ActionProvider;
import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.CommandRoleAccess;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.model.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The {@code CommandAccessFilter} class represents Command Access Filter.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
@WebFilter(urlPatterns = "/userServlet")
public class CommandAccessFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        UserType currentUserRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        Command currentCommand = ActionProvider.provideAction(request);
        if (currentUserRole == null) {
            currentUserRole = UserType.GUEST;
        }
        List<Command> availableCommands = CommandRoleAccess.valueOf(currentUserRole.toString()).getAllowedCommands();
        if (!availableCommands.contains(currentCommand)) {
            request.getRequestDispatcher(PagePath.PAGE_NOT_FOUND_ERROR).forward(servletRequest, servletResponse);
            illegalAccessDetails(session);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * logs info about user who tried to do illegal action
     *
     * @param session the session
     */
    private void illegalAccessDetails(HttpSession session) {
        String username = (String) session.getAttribute(Attribute.USERNAME);
        if (username != null) {
            LOGGER.warn(username + " - user tried to use illegal command");
        } else {
            LOGGER.warn("unregistered user tried to use illegal command");
        }
    }
}
