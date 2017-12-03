package edu.gatech.cs1332.ratattack.controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Databasestore;
import edu.gatech.cs1332.ratattack.model.User;

/**
 * The login activity used to allow an existing user to access the
 * login successful activity
 */
public class LoginActivity extends AppCompatActivity {
    static int count = 0;
    static boolean locked = false;

    Databasestore search = new Databasestore(this);
    /**
     * Creates a LoginActivity, adding login text fields and button listeners
     * so that a user can login
     *
     * @param savedInstanceState the Bundle used for onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username =(EditText) findViewById(R.id.editText);
        final EditText password = (EditText) findViewById(R.id.editText4);
        final Button signin = (Button) findViewById(R.id.button2);
        final Button cancel = (Button) findViewById(R.id.button3);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                String pass = search.searchPass(username1);
//                Database data = Database.getInstance();
                if (locked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Too many login attempts");
                    builder.setMessage("After three loggin attempts, you're now locked out for a while");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.show();
                    return;
                }
                if(pass.equals(password1)) {
                    if (Database.getInstance().userIsBanned(username1)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Banned Account");
                        builder.setMessage("Your account has been marked as banned. Please contact customer support for further details.");
                        builder.setPositiveButton("OK", null);
                        AlertDialog dialog = builder.show();
                        return;
                    }
                    Intent i = new Intent(LoginActivity.this,activity_loginsuccessful.class);
                    i.putExtra("Username",username1);
                    LoginActivity.this.startActivity(i);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Incorrect Username or Password");
                    builder.setMessage("try again :(");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.show();
                    LoginActivity.count++;
                    if (count >= 3) {
                        count = 0;
                        locked = true;
                        Timer thing = new Timer();
                        TimerTask task = new TimerTask() {
                            public void run() {
                                locked = false;
                            }
                        };
                        thing.schedule(task, 900000);
                    }
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });
    }
}