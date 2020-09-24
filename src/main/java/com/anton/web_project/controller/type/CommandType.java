package com.anton.web_project.controller.type;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.command.implementation.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    VIEW_USERS(new ViewUsersCommand()),
    VERIFY_ACCOUNT(new AccountVerificationCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
