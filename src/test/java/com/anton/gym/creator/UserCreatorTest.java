package com.anton.gym.creator;

import com.anton.gym.model.creator.UserCreator;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.entity.UserType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import static org.testng.Assert.*;

public class UserCreatorTest {
    private UserCreator creator;

    @BeforeClass
    public void setup() {
        this.creator = UserCreator.getInstance();
    }

    @Test
    public void createUserValidTest() {
        int id = 3;
        String username = "qwer";
        String email = "qwer@gmail.com";
        String userType = "admin";
        boolean isActive = true;
        String language = "en_Us";
        String description = "qwer";
        String photoReference= "op.jpg";
        Optional<User> actualOpt = creator.createUser(id, username, email, userType, isActive, language, description, photoReference);
        User actual = actualOpt.get();
        User expected = new User(3, "qwer", "qwer@gmail.com",
                UserType.ADMIN, true, "en_Us", "op.jpg", "qwer");
        assertEquals(actual, expected);
    }

    @Test
    public void createUserInvalidTest() {
        int id = 3;
        String username = "qwer";
        String email = "qwer@gmail.com";
        String userType = "admin";
        boolean isActive = true;
        String language = "en_Us";
        String description = "qwer";
        String photoReference= "op.jpg";
        Optional<User> actualOpt = creator.createUser(id, username, email, userType, isActive, language, description, photoReference);
        User actual = actualOpt.get();
        User expected = new User(3, "qwer", "qwer@gmail.com",
                UserType.TRAINER, true, "en_Us", "op.jpg", "qwer");
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void createUserInvalidDataTest() {
        int id = 3;
        String username = "qwer";
        String email = "qwerweddrgdrcom";
        String userType = "admin";
        boolean isActive = true;
        String language = "en_Us";
        String description = "qwer";
        String photoReference= "opqwerwerreq";
        Optional<User> actualOpt = creator.createUser(id, username, email, userType, isActive, language, description, photoReference);
        User user = actualOpt.get();
    }
}
