package edu.gatech.cs1332.ratattack.model;
import edu.gatech.cs1332.ratattack.USERTYPE;
/**
 * Created by Brent on 10/1/2017.
 */


/**
 * A user class that represents a user in the database.
 */
public class User {

    private String name;
    private String email;
    private String password;
    private USERTYPE useridentity;

    /**
     * Constructs a User instance and sets the User's name, email and password.
     *
     * @param _name the name of the user.
     * @param _email the email of the user.
     * @param _password the password of the user.
     * @param _useridentity the type of user: admin/user
     */
    public User(String _name, String _email, String _password, USERTYPE _useridentity) {
        name = _name;
        email = _email;
        password = _password;
        useridentity = _useridentity;
    }

    /**
     * Gets the name of the User.
     *
     * @return the name of the User.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the User.
     *
     * @return the email of the User.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password of the User.
     *
     * @return the password of the User.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Gets the identity of the User.
     *
     * @return the identity of user
     */
    public USERTYPE getUseridentity() {return useridentity;}
}
