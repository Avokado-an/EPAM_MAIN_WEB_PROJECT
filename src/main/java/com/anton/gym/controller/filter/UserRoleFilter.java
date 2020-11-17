package com.anton.gym.controller.filter;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.model.entity.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code UserRoleFilter} class represents UserRoleFilter.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
@WebFilter(urlPatterns = {("/*")})
public class UserRoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        UserType type = (UserType) session.getAttribute(Attribute.USER_ROLE);
        if (type == null) {
            type = UserType.GUEST;
            session.setAttribute(Attribute.USER_ROLE, type);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
