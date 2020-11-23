package com.anton.gym.controller.filter;

import com.anton.gym.controller.command.Attribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code DefaultLanguageFilter} class represents Default Language Filter.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
@WebFilter(urlPatterns = {("/*")})
public class DefaultLanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        if (language == null || language.isEmpty()) {
            session.setAttribute(Attribute.LANGUAGE, Attribute.DEFAULT_ENGLISH_LANGUAGE);
        } else {
            request.setAttribute(Attribute.LANGUAGE, session.getAttribute(Attribute.LANGUAGE));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
