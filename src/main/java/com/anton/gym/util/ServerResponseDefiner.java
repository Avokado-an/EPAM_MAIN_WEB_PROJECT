package com.anton.gym.util;

import com.anton.gym.controller.command.Message;

public class ServerResponseDefiner {
    private static PropertiesReader reader = PropertiesReader.getInstance();

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
