package edu.gatech.cs1332.ratattack.model;

/**
 * Created by Brent on 10/1/2017.
 */

public class User {

    private String name;
    private String email;
    private String password;

    public User(String _name, String _email, String _password) {
        name = _name;
        email = _email;
        password = _password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
