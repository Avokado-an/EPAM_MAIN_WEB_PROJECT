package com.anton.gym.model.dao.query;

public class SqlMoneyAccountQuery {
    public static final String ADD_MONEY_ACCOUNT =
            "INSERT money_account(id, money_amount) VALUES (?, ?)";//todo transaction when creating new user
    public static final String UPDATE_BALANCE =
            "UPDATE money_account SET money_account.money_amount = ? WHERE money_account.id = ?";
    public static final String SELECT_MONEY_AMOUNT =
            "SELECT money_amount FROM money_account WHERE id = ?";
}
