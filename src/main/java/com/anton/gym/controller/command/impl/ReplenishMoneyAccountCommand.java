package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.Message;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.MoneyAccountService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.MoneyAccountServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.PropertiesReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ReplenishMoneyAccountCommand implements Command {
    private MoneyAccountService moneyAccountService = MoneyAccountServiceImplementation.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();
    private PropertiesReader propertiesReader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.USER_PROFILE;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Attribute.USERNAME);
        String money = request.getParameter(Attribute.MONEY);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse = propertiesReader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
        try {
            moneyAccountService.increaseMoneyOnAccount(username, money);
            fillRequestAttributes(username, request);
        } catch (ServiceException e) {
            serverResponse = propertiesReader.readUserTextProperty(language, Message.OPERATION_FAILED);
        }
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        return pagePath;

    }

    private void fillRequestAttributes(String username, HttpServletRequest request) throws ServiceException {
        Optional<User> user =  userService.findByUsername(username);
        if(user.isPresent()) {
            request.setAttribute(Attribute.USERNAME, username);
            request.setAttribute(Attribute.DESCRIPTION, user.get().getDescription());
            request.setAttribute(Attribute.PHOTO_REFERENCE, user.get().getPhotoReference());
        }
    }
}
