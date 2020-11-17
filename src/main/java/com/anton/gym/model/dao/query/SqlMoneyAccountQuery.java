package com.anton.gym.model.dao.query;

/**
 * The {@code SqlMoneyAccountQuery} class represents Sql Money Account Query.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class SqlMoneyAccountQuery {
    public static final String ADD_MONEY_ACCOUNT =
            "INSERT money_account(id, money_amount) VALUES (?, ?)";
    public static final String UPDATE_BALANCE =
            "UPDATE money_account SET money_account.money_amount = ? WHERE money_account.id = ?";
    public static final String SELECT_MONEY_AMOUNT =
            "SELECT money_amount FROM money_account WHERE id = ?";
}
