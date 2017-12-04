package edu.gatech.cs1332.ratattack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

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
    LoginButton login_facebook;
    CallbackManager callbackManager;
    @Override
    /*
     * Creates a main activity, adding button listeners for signup and login,
     * allowing the user to access the login and signup activities
     *
     * @param savedInstanceState the Bundle used for onCreate()
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        logodisplay = (ImageView)findViewById(R.id.logodisplay);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        startrotatingImage();
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
        initializeControls();
        loginWithFB();

    }
    public void startrotatingImage() {
        Animation startrotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_rotate);
        logodisplay.startAnimation(startrotate);
    }
    private void initializeControls() {
        callbackManager = CallbackManager.Factory.create();
        login_facebook = (LoginButton)findViewById(R.id.login_button);
    }
    private void loginWithFB() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent i = new Intent(MainActivity.this, activity_loginsuccessful.class);
                MainActivity.this.startActivity(i);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
