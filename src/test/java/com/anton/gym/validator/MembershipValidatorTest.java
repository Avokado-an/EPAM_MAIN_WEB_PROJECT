package com.anton.gym.validator;

import com.anton.gym.model.validator.MembershipValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MembershipValidatorTest {
    @Test
    public void validateMembershipValidTest() {
        String name = "qwer";
        int cost = 100;
        String amountOfVisits = "8";
        int months = 1;
        boolean isValidMembershipData = MembershipValidator.getInstance().
                validateMembership(name, cost, amountOfVisits, months);
        assertTrue(isValidMembershipData);
    }

    @Test
    public void overloadedValidateMembershipValidTest() {
        String name = "qwer";
        String cost = "100";
        String amountOfVisits = "8";
        String months = "1";
        boolean isValidMembershipData = MembershipValidator.getInstance().
                validateMembership(name, cost, amountOfVisits, months);
        assertTrue(isValidMembershipData);
    }

    @Test
    public void validateMembershipNameValidTest() {
        String name = "qwer";
        boolean isValidMembershipData = MembershipValidator.getInstance().validateName(name);
        assertTrue(isValidMembershipData);
    }

    @DataProvider(name = "invalidStringMembershipData")
    public Object[][] createInvalidStringMembershipData() {
        return new Object[][]{
                {"qwer<>$#", "1", "8", "1"}, {"qwerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "1", "8", "1"},
                {"q", "1", "8", "1"}, {null, "1", "8", "1"},
                {"qwerr", "qwerwerrwr", "8", "1"}, {"qwrer", "-1", "8", "1"},
                {"qwrer", "10000", "8", "1"}, {"qwrer", null, "8", "1"},
                {"qwrer", null, "8", "1"},
                {"qwrer", "10000", "8", "qwerw"}, {"qwrer", "10", null, "1"},
                {"qwrer", "10", "36hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", "1"}, {"qwrer", "10", "8", "qwer"},
                {"qwrer", "10", "8", "100"}, {"qwrer", "10", "8", "-1"},
                {"qwrer", "10", "8", "1wreweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrr"},
                {"qwrer", "10", "8", null}
        };
    }

    @Test(dataProvider = "invalidStringMembershipData")
    public void validateMembershipInvalidTest(String name, String cost, String amountOfVisits, String months) {
        boolean isValidMembershipData = MembershipValidator.getInstance().
                validateMembership(name, cost, amountOfVisits, months);
        assertFalse(isValidMembershipData);
    }

    @DataProvider(name = "invalidIntMembershipData")
    public Object[][] createInvalidIntMembershipData() {
        return new Object[][]{
                {"qwer<>$#", 1, "8", 1}, {"qwerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", 1, "8", 1},
                {"q", 1, "8", 1}, {null, 1, "8", 1},
                {"qwrer", -1, "8", 1}, {"qwrer", 100000, "8", 1},
                {"qwrer", 1, null, 1}, {"qwrer", 1, "36ffffffffffffffffffffffffffffffffffffffffffffffffffff", 1},
                {"qwrer", 1, "8", 100}, {"qwrer", 1, "8", -1}
        };
    }

    @Test(dataProvider = "invalidIntMembershipData")
    public void overloadedInvalidateMembershipValidTest(String name, int cost, String attendees, int months) {
        boolean isValidMembershipData = MembershipValidator.getInstance().
                validateMembership(name, cost, attendees, months);
        assertFalse(isValidMembershipData);
    }

    @DataProvider(name = "invalidName")
    public Object[][] createInvalidName() {
        return new Object[][]{
                {"qwer@#$"}, {"1"}, {"111111111111111112222222222222222222222222222222222222222222222222"}, {null}
        };
    }

    @Test(dataProvider = "invalidName")
    public void validateMembershipNameInvalidTest(String name) {
        boolean isValidMembershipData = MembershipValidator.getInstance().validateName(name);
        assertFalse(isValidMembershipData);
    }
}
