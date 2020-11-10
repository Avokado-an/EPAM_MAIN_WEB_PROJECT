package com.anton.gym.controller.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CommandRoleAccess {
    GUEST(Stream.of(
            CommandType.VIEW_MAIN_PAGE, CommandType.REGISTRATION, CommandType.LOGIN,
            CommandType.CHANGE_LANGUAGE, CommandType.GO_TO_LOGIN, CommandType.GO_TO_REGISTRATION,
            CommandType.VERIFY_ACCOUNT, CommandType.VIEW_USER_PROFILE
    ).map(CommandType::getCommand).collect(Collectors.toList())),
    CLIENT(Stream.of(
            CommandType.VIEW_MAIN_PAGE, CommandType.LOGOUT,
            CommandType.CHANGE_LANGUAGE, CommandType.GO_TO_REDACT_PROFILE,
            CommandType.PURCHASE_MEMBERSHIP, CommandType.VIEW_USER_PROFILE,
            CommandType.SIGN_FOR_COACH, CommandType.REDACT_USER_PROFILE
    ).map(CommandType::getCommand).collect(Collectors.toList())),
    TRAINER(Stream.of(
            CommandType.VIEW_MAIN_PAGE, CommandType.LOGOUT,
            CommandType.CHANGE_LANGUAGE, CommandType.GO_TO_REDACT_PROFILE,
            CommandType.VIEW_USER_PROFILE, CommandType.VIEW_TRAINER_CUSTOMERS,
            CommandType.FIND_USERS, CommandType.SORT_USERS,
            CommandType.REDACT_USER_PROFILE
    ).map(CommandType::getCommand).collect(Collectors.toList())),
    ADMIN(Stream.of(
            CommandType.VIEW_MAIN_PAGE, CommandType.LOGOUT,
            CommandType.CHANGE_LANGUAGE, CommandType.VIEW_USER_PROFILE,
            CommandType.VIEW_USERS, CommandType.FIND_USERS,
            CommandType.SORT_USERS, CommandType.BLOCK_USER,
            CommandType.UNBLOCK_USER, CommandType.MARK_TRAINER_AS_USER,
            CommandType.MARK_USER_AS_TRAINER, CommandType.GO_TO_ADD_MEMBERSHIP,
            CommandType.GO_TO_REDACT_MEMBERSHIP, CommandType.ADD_MEMBERSHIP,
            CommandType.REDACT_MEMBERSHIP
    ).map(CommandType::getCommand).collect(Collectors.toList()));

    private List<Command> allowedCommands;

    CommandRoleAccess(List<Command> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    public List<Command> getAllowedCommands() {
        return allowedCommands;
    }
}
