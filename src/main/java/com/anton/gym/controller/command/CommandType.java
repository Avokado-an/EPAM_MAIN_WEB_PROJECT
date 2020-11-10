package com.anton.gym.controller.command;

import com.anton.gym.controller.command.impl.*;
import com.anton.gym.controller.command.impl.page.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    VIEW_USERS(new ViewUsersCommand()),
    VERIFY_ACCOUNT(new AccountVerificationCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    VIEW_MAIN_PAGE(new ViewMainPageCommand()),
    GO_TO_REGISTRATION(new GoToRegistrationCommand()),
    GO_TO_LOGIN(new GoToLoginCommand()),
    GO_TO_REDACT_MEMBERSHIP(new GoToMembershipRedactCommand()),
    PURCHASE_MEMBERSHIP(new PurchaseMembershipCommand()),
    REDACT_MEMBERSHIP(new RedactMembershipCommand()),
    VIEW_USER_PROFILE(new ViewUserProfile()),
    GO_TO_REDACT_PROFILE(new GoToRedactProfileCommand()),
    REDACT_USER_PROFILE(new RedactUserProfileCommand()),
    MARK_TRAINER_AS_USER(new MarkTrainerAsUserCommand()),
    MARK_USER_AS_TRAINER(new MarkUserAsTrainerCommand()),
    VIEW_TRAINER_CUSTOMERS(new ViewTrainerCustomersCommand()),
    ADD_MEMBERSHIP(new AddMembershipCommand()),
    GO_TO_ADD_MEMBERSHIP(new GoToAddMembershipCommand()),
    SIGN_FOR_COACH(new SignForCoachCommand()),
    FIND_USERS(new FindUserCommand()),
    SORT_USERS(new SortUsersCommand()),
    REPLENISH_MONEY_ACCOUNT(new ReplenishMoneyAccountCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
