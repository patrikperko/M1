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
import edu.gatech.cs1332.ratattack.model.Databasestore;

/**
 * The login activity used to allow an existing user to access the
 * login successful activity
 */
public class LoginActivity extends AppCompatActivity {
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
                if(pass.equals(password1)) {
                    Intent i = new Intent(LoginActivity.this,activity_loginsuccessful.class);
                    i.putExtra("Username",username1);
                    LoginActivity.this.startActivity(i);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Incorrect Username or Password");
                    builder.setMessage("try again :(");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.show();
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