package com.anton.web_project.controller.provider;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.command.implementation.FailedCommand;
import com.anton.web_project.controller.exception.ControllerException;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.controller.type.CommandType;

import javax.servlet.http.HttpServletRequest;

public class ActionProvider {
    private ActionProvider() {
    }

    public static Command provideAction(HttpServletRequest action) {
        Command command;
        try {
            if (action == null) {
                throw new ControllerException("No action provided");
            }
            String actionName = action.getParameter(ServletAttribute.COMMAND_ATTRIBUTE);
            command = CommandType.valueOf(actionName.toUpperCase()).getCommand();
        } catch (IllegalArgumentException | ControllerException e) {
            command = new FailedCommand();
        }
        return command;
    }
}
