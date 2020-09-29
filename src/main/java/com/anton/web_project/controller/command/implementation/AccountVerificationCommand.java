package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.AdminService;
import com.anton.web_project.model.service.impl.AdminServiceImplementation;

import javax.servlet.http.HttpServletRequest;

public class AccountVerificationCommand implements Command {
    private static final String VERIFICATION_SUCCEED = "Your account was verified";
    private static final String VERIFICATION_FAILED = "Your account was not verified";

    @Override // TODO: 21.09.2020 java code in jsp 
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(ServletAttribute.USERNAME_ATTRIBUTE);
        AdminService userService = AdminServiceImplementation.getInstance();
        String pagePath = ResponsePagePath.LOGIN;
        try {
            userService.unblockUser(username);
            request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, VERIFICATION_SUCCEED);
        } catch (ServiceException e) {
            request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, VERIFICATION_FAILED);
        }
        request.setAttribute(ServletAttribute.LANGUAGE_ATTRIBUTE, ServletAttribute.DEFAULT_ENGLISH_LANGUAGE);
        return pagePath;
    }
}
