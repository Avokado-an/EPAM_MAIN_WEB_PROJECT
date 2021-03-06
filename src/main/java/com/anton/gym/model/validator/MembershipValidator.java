package com.anton.gym.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code MembershipValidator} class represents membership validator.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class MembershipValidator {
    private static final MembershipValidator instance = new MembershipValidator();
    private static final String NON_LETTER_NUMBER_REGEX = "[\\W]";
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 29;
    private static final int MAX_ATTENDEES_LENGTH = 30;
    private static final int MAX_COST = 1000;
    private static final int MAX_MONTHS_AMOUNT = 36;
    private static final int ZERO = 0;

    /**
     * get instance
     *
     * @return the instance
     */
    public static MembershipValidator getInstance() {
        return instance;
    }

    private MembershipValidator() {

    }

    /**
     * validate membership
     *
     * @param name           the name
     * @param cost           the cost
     * @param amountOfVisits the visits amount
     * @param months         the months amount
     * @return if all data valid
     */
    public boolean validateMembership(String name, String cost, String amountOfVisits, String months) {
        return name != null && validateName(name) &&
                cost != null && validateCost(cost) &&
                amountOfVisits != null && amountOfVisits.length() < MAX_ATTENDEES_LENGTH &&
                months != null && validateMonths(months);
    }

    /**
     * validate membership
     *
     * @param name           the name
     * @param cost           the cost
     * @param amountOfVisits the visits amount
     * @param months         the months amount
     * @return if all data valid
     */
    public boolean validateMembership(String name, int cost, String amountOfVisits, int months) {
        return name != null && validateName(name) &&
                cost > ZERO && cost < MAX_COST &&
                amountOfVisits != null && amountOfVisits.length() < MAX_ATTENDEES_LENGTH &&
                months > ZERO && months < MAX_MONTHS_AMOUNT;
    }

    /**
     * validate name
     *
     * @param line the line
     * @return if name is valid
     */
    public boolean validateName(String line) {
        boolean isValidName = true;
        if (line != null) {
            Pattern pattern = Pattern.compile(NON_LETTER_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find() || line.length() > MAX_NAME_LENGTH || line.length() < MIN_NAME_LENGTH) {
                isValidName = false;
            }
        } else {
            isValidName = false;
        }
        return isValidName;
    }

    private boolean validateCost(String cost) {
        boolean isValid = true;
        try {
            int price = Integer.parseInt(cost);
            if (price > MAX_COST || price <= ZERO) {
                isValid = false;
            }
        } catch (NumberFormatException e) {
            isValid = false;
        }
        return isValid;
    }

    private boolean validateMonths(String months) {
        boolean isValid = true;
        try {
            int monthsLength = Integer.parseInt(months);
            if (monthsLength > MAX_MONTHS_AMOUNT || monthsLength <= ZERO) {
                isValid = false;
            }
        } catch (NumberFormatException e) {
            isValid = false;
        }
        return isValid;
    }
}
