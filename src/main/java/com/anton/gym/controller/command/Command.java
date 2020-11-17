package com.anton.gym.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code Сщььфтв} class represents command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface Command {
    /**
     * Executes currentCommand
     *
     * @param request the request
     * @return the string which contains path to jsp
     */
    String execute(HttpServletRequest request);
}