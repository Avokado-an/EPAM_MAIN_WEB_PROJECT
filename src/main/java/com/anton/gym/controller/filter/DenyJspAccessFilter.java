package com.anton.gym.controller.filter;

import com.anton.gym.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The {@code DenyJspAccessFilter} class represents Deny Jsp Access Filter.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
@WebFilter(urlPatterns = {("/jsp/*")})
public class DenyJspAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String pagePath = PagePath.PAGE_NOT_FOUND_ERROR;
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(pagePath);
        dispatcher.forward(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
