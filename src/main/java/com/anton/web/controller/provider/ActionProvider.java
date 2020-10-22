package com.anton.web.controller.provider;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.impl.FailedCommand;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.CommandType;

import javax.servlet.http.HttpServletRequest;

public class ActionProvider {
    private ActionProvider() {
    }

    public static Command provideAction(HttpServletRequest action) {
        Command command;
        try {
            if (action == null) {
                command = new FailedCommand();
            } else {
                String actionName = action.getParameter(Attribute.COMMAND);
                command = CommandType.valueOf(actionName.toUpperCase()).getCommand();
            }
        } catch (IllegalArgumentException e) {
            command = new FailedCommand();
        }
        return command;
    }
}
