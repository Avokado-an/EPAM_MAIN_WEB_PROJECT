package com.anton.gym.util;

import com.anton.gym.controller.command.Message;

/**
 * The {@code ServerResponseDefiner} class represents ServerResponseDefiner.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ServerResponseDefiner {
    private static PropertiesReader reader = PropertiesReader.getInstance();

    /**
     * define if server operation was successfully done
     * @param condition
     * @param language
     * @return the response
     */
    public static String defineServerResponse(boolean condition, String language) {
        String serverResponse;
        if (condition) {
            serverResponse = reader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
        } else {
            serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
        }
        return serverResponse;
    }
}
