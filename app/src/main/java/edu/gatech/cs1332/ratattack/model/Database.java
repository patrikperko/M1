package edu.gatech.cs1332.ratattack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brent on 10/1/2017.
 */

/**
 * A database singleton that keeps track of all users
 */
public class Database {

    private static Database instance = new Database();
    public static Database getInstance() {return instance;}

    private List<User> users;

    Database() {
        users = new ArrayList<>();
    }

    /**
     * Adds a user to the list of users
     *
     * @param newUser the user to be added
     */
    public void addUser(User newUser) {
        users.add(newUser);
    }

    /**
     * Checks whether a valid user is attempting to login
     *
     * @param toFind the user attempting to login
     * @param pass the password of the user attempting to login
     * @return true if the user exists in the database
     */
    public boolean signIn(String toFind, String pass) {
        for(User i: users) {
            if (i.getName().equals(toFind)) {
                if (i.getPassword().equals(pass)) {
                    return true;
                }
            }
        }
        return false;
    }

}