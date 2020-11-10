package com.anton.gym.validator;

import com.anton.gym.model.validator.UserValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {
    @Test
    public void validateDescriptionValidTest() {
        String validDescription = "1234qwer";
        boolean isValidDescription = UserValidator.getInstance().validateDescription(validDescription);
        assertTrue(isValidDescription);
    }

    @DataProvider(name = "invalidDescriptions")
    private Object[][] createInvalidDescriptions() {
        return new Object[][]{
                {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"},
                {null}
        };
    }

    @Test(dataProvider = "invalidDescriptions")
    public void validateDescriptionInvalidTest(String description) {
        boolean isValidDescription = UserValidator.getInstance().validateDescription(description);
        assertFalse(isValidDescription);
    }

    @Test
    public void validateStringCharactersValidTest() {
        String validLine = "1234qwer";
        boolean isValidLine = UserValidator.getInstance().validateStringCharacters(validLine);
        assertTrue(isValidLine);
    }

    @DataProvider(name = "invalidLines")
    private Object[][] createInvalidLines() {
        return new Object[][]{
                {"qwer_&&&&$#@$@$<><>"}, {"a"}, {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"},
                {null}
        };
    }

    @Test(dataProvider = "invalidLines")
    public void validateStringCharactersInvalidTest(String line) {
        boolean isValidLine = UserValidator.getInstance().validateStringCharacters(line);
        assertFalse(isValidLine);
    }

    @DataProvider(name = "validReferences")
    private Object[][] createValidPhotoReferences() {
        return new Object[][]{
                {"Polina.jpg"}, {"Polina.png"}
        };
    }

    @Test(dataProvider = "validReferences")
    public void validatePhotoReferenceValidTest(String validPhotoReference) {
        boolean isValidReference = UserValidator.getInstance().validatePhotoReference(validPhotoReference);
        assertTrue(isValidReference);
    }

    @DataProvider(name = "invalidReferences")
    private Object[][] createInvalidPhotoReferences() {
        return new Object[][]{
                {"qwer_&&&&$#@$@$<><>"}, {"a"}, {null}
        };
    }

    @Test(dataProvider = "invalidReferences")
    public void validatePhotoReferenceInvalidTest(String reference) {
        boolean isValidReference = UserValidator.getInstance().validatePhotoReference(reference);
        assertFalse(isValidReference);
    }

    @Test
    public void validateEmailValidTest() {
        String validEmail = "tonibogdanov46@gmail.com";
        boolean isValidEmail = UserValidator.getInstance().validateEmail(validEmail);
        assertTrue(isValidEmail);
    }

    @DataProvider(name = "invalidEmails")
    private Object[][] createInvalidEmails() {
        return new Object[][]{
                {"a"}, {null}, {"qwerwre@fddofgkodfgcokofks"}, {"qwerwre.fddofgkodfgcokofks"}
        };
    }

    @Test(dataProvider = "invalidEmails")
    public void validateEmailInvalidTest(String email) {
        boolean isValidEmail = UserValidator.getInstance().validateEmail(email);
        assertFalse(isValidEmail);
    }

    @Test
    public void validateUser() {
        String username = "123qewr";
        String email = "qwer@gmail.com";
        assertTrue(UserValidator.getInstance().validateUser(username, email));
    }

    @DataProvider(name = "invalidUsernameAndEmail")
    private Object[][] createInvalidUsernameAndEmail() {
        return new Object[][]{
                {null, null}, {"null<>%$#%$#", "qwer@gmail.com"},
                {"1234qwer", "qwer@fsdffr"}, {"r3wrer", "qwerwre.fddofgkodfgcokofks"}
        };
    }

    @Test(dataProvider = "invalidUsernameAndEmail")
    public void validateUser(String username, String email) {
        assertFalse(UserValidator.getInstance().validateUser(username, email));
    }
}
