package com.anton.web.controller.filter;

import com.anton.web.controller.command.Attribute;
import com.anton.web.model.entity.LanguageType;
import com.anton.web.model.entity.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {("/*")})
public class DefaultLanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (request.getAttribute(Attribute.LANGUAGE) == null) {
            if (session.getAttribute(Attribute.LANGUAGE) == null) {
                request.setAttribute(Attribute.LANGUAGE, Attribute.DEFAULT_ENGLISH_LANGUAGE);
                session.setAttribute(Attribute.LANGUAGE, Attribute.DEFAULT_ENGLISH_LANGUAGE);
            } else {
                request.setAttribute(Attribute.LANGUAGE, session.getAttribute(Attribute.LANGUAGE));
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
