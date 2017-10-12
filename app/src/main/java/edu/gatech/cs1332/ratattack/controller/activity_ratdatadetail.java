package edu.gatech.cs1332.ratattack.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Rat;

public class activity_ratdatadetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratdatadetails);
        Rat details = (Rat) getIntent().getParcelableExtra("key");
        TextView ratNumber = (TextView) findViewById(R.id.ratnumber);
        ratNumber.setText(details.getUniquekey());
    }
}
