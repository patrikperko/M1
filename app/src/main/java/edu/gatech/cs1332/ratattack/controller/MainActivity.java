package edu.gatech.cs1332.ratattack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs1332.ratattack.R;

/**
 * The main activity that directs the user to sign-up and login activities,
 * and serves as the default redirect for other activities
 */
public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button signUpButton;
    @Override
    /**
     * Creates a main activity, adding button listeners for signup and login,
     * allowing the user to access the login and signup activities
     *
     * @param savedInstanceState the Bundle used for onCreate()
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(i);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activity_signup.class);
                MainActivity.this.startActivity(i);
            }
        });
    }
}