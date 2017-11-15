package edu.gatech.cs1332.ratattack.controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Databasestore;
import edu.gatech.cs1332.ratattack.model.User;
import edu.gatech.cs1332.ratattack.USERTYPE;
/**
 * The sign up activity used to add a user to the database.
 */
public class activity_signup extends AppCompatActivity {

    Databasestore store = new Databasestore(this);
    /**
     * Creates a sign up activity, allowing a new user to create login credentials
     * by filling out EditText fields and setting listeners for the Sign-up and
     * Cancel Buttons.
     *
     * @param savedInstanceState the Bundle used for onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final Spinner identityspinner = (Spinner) findViewById(R.id.spinner2);
        final EditText userText = (EditText) findViewById(R.id.usernameButton);
        final EditText emailText = (EditText) findViewById(R.id.emailButton);
        final EditText passText = (EditText) findViewById(R.id.passButton);
        final EditText confirmText = (EditText) findViewById(R.id.confirmButton);

        final Button sign = (Button) findViewById(R.id.signButton);
        final Button cancel = (Button) findViewById(R.id.cancelButton);
        identityspinner.setAdapter(new ArrayAdapter<USERTYPE>(this, android.R.layout.simple_spinner_item, USERTYPE.values()));
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity_signup.this, MainActivity.class);
                activity_signup.this.startActivity(i);
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userText.getText().toString().trim().length() > 0 && emailText.getText().toString().trim().length() > 0 &&
                        passText.getText().toString().trim().length() > 0 && confirmText.getText().toString().trim().length() > 0 &&
                        identityspinner.getSelectedItem()!= null ) {
                    if (passText.getText().toString().equals(confirmText.getText().toString())) {
                        User user = new User(userText.getText().toString(), emailText.getText().toString(), passText.getText().toString(),(USERTYPE) identityspinner.getSelectedItem());
                        store.insertuser(user);
                        Database data = Database.getInstance();
                        data.addUser(user);
                        //insert to database
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity_signup.this);
                        builder.setTitle("Congratulations");
                        builder.setMessage("Sign up Successfully! Will go to main page in 3 seconds");
                        builder.setCancelable(true);
                        final AlertDialog dialog = builder.create();
                        dialog.show();
                        final Timer fiveseconds = new Timer();
                        fiveseconds.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                fiveseconds.cancel();
                                Intent i = new Intent(activity_signup.this, MainActivity.class);
                                activity_signup.this.startActivity(i);
                            }
                        },3000);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity_signup.this);
                        builder.setTitle("Passwords do not match");
                        builder.setMessage("try again  ¯\\_(ツ)_/¯:(");
                        builder.setPositiveButton("OK", null);
                        AlertDialog dialog = builder.show();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity_signup.this);
                    builder.setTitle("Missing required element");
                    builder.setMessage("try again  ¯\\_(ツ)_/¯");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.show();
                }
            }
        });
    }
}