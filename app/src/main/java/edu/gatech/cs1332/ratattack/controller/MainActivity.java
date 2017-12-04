package edu.gatech.cs1332.ratattack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import edu.gatech.cs1332.ratattack.R;

/*
 * The main activity that directs the user to sign-up and login activities,
 * and serves as the default redirect for other activities
 */
public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button signUpButton;
    Animation uptodown;
    private ImageView logodisplay;
    @Override
    /*
     * Creates a main activity, adding button listeners for signup and login,
     * allowing the user to access the login and signup activities
     *
     * @param savedInstanceState the Bundle used for onCreate()
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logodisplay = (ImageView)findViewById(R.id.logodisplay);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        logodisplay.setAnimation(uptodown);
        loginButton.setAnimation(uptodown);
        signUpButton.setAnimation(uptodown);
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
