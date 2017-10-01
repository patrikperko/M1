package edu.gatech.cs1332.ratattack.controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.User;

public class activity_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText userText = (EditText) findViewById(R.id.usernameButton);
        final EditText emailText = (EditText) findViewById(R.id.emailButton);
        final EditText passText = (EditText) findViewById(R.id.passButton);
        final EditText confirmText = (EditText) findViewById(R.id.confirmButton);

        final Button sign = (Button) findViewById(R.id.signButton);
        final Button cancel = (Button) findViewById(R.id.cancelButton);

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
                if (userText.getText().toString() != null && emailText.getText().toString() != null && passText.getText().toString()
                        != null && confirmText.getText().toString() != null) {
                    if (passText.getText().toString().equals(confirmText.getText().toString())) {
                        User user = new User(userText.getText().toString(), emailText.getText().toString(), passText.getText().toString());
                        Database data = Database.getInstance();
                        data.addUser(user);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity_signup.this);
                        builder.setTitle("Passwords do not match");
                        builder.setMessage("try again :(");
                        builder.setPositiveButton("OK", null);
                        AlertDialog dialog = builder.show();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity_signup.this);
                    builder.setTitle("Missing required element");
                    builder.setMessage("try again :(");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.show();
                }
            }
        });
    }
}
