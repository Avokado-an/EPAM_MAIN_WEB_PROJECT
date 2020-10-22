package com.anton.web.controller.filter;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.impl.AccountVerificationCommand;
import com.anton.web.model.entity.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/account_verification.jsp"})
public class AccountVerificationFilter implements Filter {
    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String usernameToVerify = servletRequest.getParameter(Attribute.USERNAME);
        session.setAttribute(Attribute.USERNAME, usernameToVerify);
        String language = servletRequest.getParameter(Attribute.LANGUAGE);
        session.setAttribute(Attribute.LANGUAGE, language);
        Command command = new AccountVerificationCommand();
        String page = command.execute(request);
        session.setAttribute(Attribute.USER_ROLE, UserType.GUEST);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
