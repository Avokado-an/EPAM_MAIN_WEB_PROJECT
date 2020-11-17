package com.anton.gym.controller;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.CommandType;
import com.anton.gym.controller.command.impl.FailedCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code ActionProvider} class represents ActionProvider.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ActionProvider {
    private ActionProvider() {
    }

    /**
     * returns command from current request
     *
     * @param request the request
     * @return command to execute
     */
    public static Command provideAction(HttpServletRequest request) {
        Command command;
        try {
            if (request == null) {
                command = new FailedCommand();
            } else {
                String actionName = request.getParameter(Attribute.COMMAND);
                command = CommandType.valueOf(actionName.toUpperCase()).getCommand();
            }
        } catch (IllegalArgumentException e) {
            command = new FailedCommand();
        }
        return command;
    }
}
