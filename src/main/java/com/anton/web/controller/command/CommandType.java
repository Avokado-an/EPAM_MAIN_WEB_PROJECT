package com.anton.web.controller.command;

import com.anton.web.controller.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    VIEW_USERS(new ViewUsersCommand()),
    VERIFY_ACCOUNT(new AccountVerificationCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    VIEW_MAIN_PAGE(new ViewMainPageCommand()),
    GO_TO_REGISTRATION(new GoToRegistrationCommand()),
    GO_TO_LOGIN(new GoToLoginCommand()),
    GO_TO_REDACT_MEMBERSHIP(new GoToMembershipRedactCommand()),
    PURCHASE_MEMBERSHIP(new PurchaseMembershipCommand()),
    REDACT_MEMBERSHIP(new RedactMembershipCommand()),
    VIEW_USER_PROFILE(new ViewUserProfile());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
