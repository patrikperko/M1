package edu.gatech.cs1332.ratattack;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs1332.ratattack.USERTYPE;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.User;

import static org.junit.Assert.*;

/**
 * Created by Brent on 11/13/2017.
 */

public class signInUnitTest {

    Database testBase = new Database();


    @Before
    public void setUp() {
        User testUser = new User("testName", "testEmail", "testPassword", USERTYPE.User);
        User user2 = new User("user2", "email2", "password2", USERTYPE.User);
        testBase.addUser(testUser);
        testBase.addUser(user2);
    }


    @Test
    public void correctSignIn() {
        //Tests a correct username and password
        assertEquals(true, testBase.signIn("testName", "testPassword"));
    }

    @Test
    public void wrongPassword() {
        //Tests a correct username but wrong password
        assertEquals(false, testBase.signIn("testName", "false"));
    }

    @Test
    public void noUser() {
        //Tests a incorrect username and password
        assertEquals(false, testBase.signIn("wrong", "false"));
    }

    @Test
    public void wrongUsernamePassword() {
        //Tests a correct username but password for different user
        assertEquals(false, testBase.signIn("user2", "testPassword"));
    }
}
