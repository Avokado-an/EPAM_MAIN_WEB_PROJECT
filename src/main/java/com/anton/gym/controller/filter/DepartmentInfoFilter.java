package com.anton.gym.controller.filter;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Department;
import com.anton.gym.model.service.DepartmentService;
import com.anton.gym.model.service.impl.DepartmentServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code DepartmentInfoFilter} class represents Department Info Filter.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
@WebFilter(urlPatterns = {("/*")})
public class DepartmentInfoFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute(Attribute.DEPARTMENT_EMAIL) == null) {
            fillSessionWithDepartmentData(session);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void fillSessionWithDepartmentData(HttpSession session) {
        DepartmentService service = DepartmentServiceImplementation.getInstance();
        try {
            Department department = service.showDepartment();
            session.setAttribute(Attribute.DEPARTMENT_EMAIL, department.getEmail());
            session.setAttribute(Attribute.DEPARTMENT_PHONE_NUMBER, department.getPhoneNumber());
            session.setAttribute(Attribute.DEPARTMENT_PICTURE_REFERENCE, department.getPhotoReference());
            session.setAttribute(Attribute.DEPARTMENT_CITY, department.getCity());
            session.setAttribute(Attribute.DEPARTMENT_STREET, department.getStreet());
            session.setAttribute(Attribute.DEPARTMENT_HOUSE, department.getHouse());
        } catch (ServiceException e) {
            LOGGER.warn("cant read department data", e);
        }
    }
}
