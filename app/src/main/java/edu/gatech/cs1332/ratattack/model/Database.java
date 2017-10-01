package edu.gatech.cs1332.ratattack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brent on 10/1/2017.
 */

public class Database {

    private static Database instance = new Database();
    public static Database getInstance() {return instance;}

    private List<User> users;

    Database() {
        users = new ArrayList<>();
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

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
