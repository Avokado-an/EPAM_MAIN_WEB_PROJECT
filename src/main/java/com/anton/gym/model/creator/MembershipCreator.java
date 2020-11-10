package com.anton.gym.model.creator;

import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.validator.MembershipValidator;

import java.util.Optional;

public class MembershipCreator {
    public static MembershipCreator instance;

    public static MembershipCreator getInstance() {
        if(instance == null) {
            instance = new MembershipCreator();
        }
        return instance;
    }

    private MembershipCreator() {
    }

    public Optional<Membership> createMembership(String id, String name, String cost, String amountOfVisits, String months) {
        MembershipValidator validator = MembershipValidator.getInstance();
        Optional<Membership> result = Optional.empty();
        if (validator.validateMembership(name, cost, amountOfVisits, months)) {
            int idNumber = Integer.parseInt(id);
            int costNumber = Integer.parseInt(cost);
            int monthsNumber = Integer.parseInt(months);
            result = Optional.of(new Membership(idNumber, name, costNumber, amountOfVisits, monthsNumber));
        }
        return result;
    }

    public Optional<Membership> createMembership(String name, String cost, String amountOfVisits, String months) {
        MembershipValidator validator = MembershipValidator.getInstance();
        Optional<Membership> result = Optional.empty();
        if (validator.validateMembership(name, cost, amountOfVisits, months)) {
            int costNumber = Integer.parseInt(cost);
            int monthsNumber = Integer.parseInt(months);
            result = Optional.of(new Membership(name, costNumber, amountOfVisits, monthsNumber));
        }
        return result;
    }

    public Optional<Membership> createMembership(int id, String name, int cost, String amountOfVisits, int months) {
        MembershipValidator validator = MembershipValidator.getInstance();
        Optional<Membership> result = Optional.empty();
        if (validator.validateMembership(name, cost, amountOfVisits, months)) {
            result = Optional.of(new Membership(id, name, cost, amountOfVisits, months));
        }
        return result;
    }
}
