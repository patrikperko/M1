package edu.gatech.cs1332.ratattack;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;

public class LoginActivity extends AppCompatActivity {

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
                if(password1.equals("pass") && username1.equals("user")) {
                    Intent i = new Intent(LoginActivity.this,activity_loginsuccessful.class);
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
