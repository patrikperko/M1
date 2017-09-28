package edu.gatech.cs1332.ratattack;

/**
 * Created by Andy on 9/28/2017.
 */

public class Account {
    private String username;
    private String password;
    private USERTYPE access;

    public Account(String name, String pass) {
        this(name, pass, USERTYPE.User);
    }

    public Account(String name, String pass, USERTYPE type) {

    }
}
