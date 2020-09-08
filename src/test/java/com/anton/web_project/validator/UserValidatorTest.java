package com.anton.web_project.validator;

import com.anton.web_project.model.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserValidatorTest {
    @Test
    public void validateUserValidTest() {
        String text1 = "1111111";
        String text2 = "1111111";
        boolean isValid = UserValidator.getInstance().validateUser(text1,text2);
        Assert.assertTrue(isValid);
    }

    @Test
    public void validateUserInvalidTest() {
        String text1 = "1,asd%%";
        String text2 = "1234^";
        boolean isValid = UserValidator.getInstance().validateUser(text1,text2);
        Assert.assertFalse(isValid);
    }
}
