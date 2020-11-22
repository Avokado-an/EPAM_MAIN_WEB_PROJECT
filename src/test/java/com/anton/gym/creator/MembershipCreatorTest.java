package com.anton.gym.creator;

import com.anton.gym.model.creator.MembershipCreator;
import com.anton.gym.model.entity.Membership;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class MembershipCreatorTest {
    private MembershipCreator creator;

    @BeforeClass
    public void setup() {
        creator = MembershipCreator.getInstance();
    }

    @Test
    public void createMembershipValidTest() {
        String id = "1";
        String name = "qwer";
        String cost = "40";
        String amountOfVisits = "12";
        String months = "2";
        String isActive = "true";
        Optional<Membership> membershipOpt = creator.createMembership(id, name, cost, amountOfVisits, months, isActive);
        Membership actual = membershipOpt.get();
        Membership expected = new Membership(1, "qwer", 40, "12", 2, true);
        assertEquals(actual, expected);
    }

    @Test
    public void createMembershipInvalidTest() {
        String id = "1";
        String name = "qwer";
        String cost = "40";
        String amountOfVisits = "8";
        String months = "2";
        String isActive = "true";
        Optional<Membership> membershipOpt = creator.createMembership(id, name, cost, amountOfVisits, months, isActive);
        Membership actual = membershipOpt.get();
        Membership expected = new Membership(1, "qwer", 40, "12", 2, true);
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void createMembershipInvalidDataTest() {
        String id = "1";
        String name = "qwer";
        String cost = "4000000";
        String amountOfVisits = "8";
        String months = "2";
        String isActive = "true";
        Optional<Membership> membershipOpt = creator.createMembership(id, name, cost, amountOfVisits, months, isActive);
        Membership actual = membershipOpt.get();
    }
}
