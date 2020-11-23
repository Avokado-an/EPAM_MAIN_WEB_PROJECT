package com.anton.gym.controller.filter;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.model.service.MoneyAccountService;
import com.anton.gym.model.service.impl.MoneyAccountServiceImplementation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * The {@code BalanceRepresentationFilter} class represents Balance Representation Filter.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
@WebFilter(urlPatterns = "/*")
public class BalanceRepresentationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute(Attribute.BALANCE) == null) {
            MoneyAccountService moneyAccountService = MoneyAccountServiceImplementation.getInstance();
            UserType currentRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
            if (currentRole == UserType.CLIENT) {
                String username = (String) session.getAttribute(Attribute.USERNAME);
                try {
                    BigDecimal currentBalance = moneyAccountService.checkMoneyOnAccount(username);
                    session.setAttribute(Attribute.BALANCE, currentBalance);
                } catch (ServiceException e) {
                    int zero = 0;
                    session.setAttribute(Attribute.BALANCE, zero);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
