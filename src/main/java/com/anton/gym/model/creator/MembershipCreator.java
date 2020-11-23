package com.anton.gym.model.creator;

import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.validator.MembershipValidator;

import java.util.Optional;

/**
 * The {@code MembershipCreator} class represents membership creator.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class MembershipCreator {
    public static MembershipCreator instance;

    /**
     * get instance
     *
     * @return the instance
     */
    public static MembershipCreator getInstance() {
        if (instance == null) {
            instance = new MembershipCreator();
        }
        return instance;
    }

    private MembershipCreator() {
    }

    /**
     * creates membership
     *
     * @param id             the id
     * @param name           the name
     * @param cost           the cost
     * @param amountOfVisits the amount of visits
     * @param months         the months amount
     * @param isActive       is membership active
     * @return the optional of membership
     */
    public Optional<Membership> createMembership
    (String id, String name, String cost, String amountOfVisits, String months, String isActive) {
        MembershipValidator validator = MembershipValidator.getInstance();
        Optional<Membership> result = Optional.empty();
        if (validator.validateMembership(name, cost, amountOfVisits, months)) {
            int idNumber = Integer.parseInt(id);
            int costNumber = Integer.parseInt(cost);
            int monthsNumber = Integer.parseInt(months);
            boolean isActiveMembership = Boolean.parseBoolean(isActive);
            result = Optional.of(new Membership(
                    idNumber, name, costNumber, amountOfVisits, monthsNumber, isActiveMembership));
        }
        return result;
    }

    /**
     * creates membership
     *
     * @param name           the name
     * @param cost           the cost
     * @param amountOfVisits the amount of visits
     * @param months         the months amount
     * @param isActive       is membership active
     * @return the optional of membership
     */
    public Optional<Membership> createMembership
    (String name, String cost, String amountOfVisits, String months, String isActive) {
        MembershipValidator validator = MembershipValidator.getInstance();
        Optional<Membership> result = Optional.empty();
        if (validator.validateMembership(name, cost, amountOfVisits, months)) {
            int costNumber = Integer.parseInt(cost);
            int monthsNumber = Integer.parseInt(months);
            boolean isActiveMembership = Boolean.parseBoolean(isActive);
            result = Optional.of(new Membership(name, costNumber, amountOfVisits, monthsNumber, isActiveMembership));
        }
        return result;
    }

    /**
     * creates membership
     *
     * @param id             the id
     * @param name           the name
     * @param cost           the cost
     * @param amountOfVisits the amount of visits
     * @param months         the months amount
     * @param isActive       is membership active
     * @return the optional of membership
     */
    public Optional<Membership> createMembership
    (int id, String name, int cost, String amountOfVisits, int months, boolean isActive) {
        MembershipValidator validator = MembershipValidator.getInstance();
        Optional<Membership> result = Optional.empty();
        if (validator.validateMembership(name, cost, amountOfVisits, months)) {
            result = Optional.of(new Membership(id, name, cost, amountOfVisits, months, isActive));
        }
        return result;
    }
}
