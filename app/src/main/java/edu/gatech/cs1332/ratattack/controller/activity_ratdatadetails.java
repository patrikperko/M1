package edu.gatech.cs1332.ratattack.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Rat;

public class activity_ratdatadetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratdatadetails);

        Rat details = (Rat) getIntent().getParcelableExtra("key");


        TextView ratNumber = (TextView) findViewById(R.id.ratnumber);
        ratNumber.setText(details.getUniquekey());
        TextView createdDate = (TextView) findViewById(R.id.created);
        createdDate.setText(details.getCreate_date());
        TextView loc = (TextView) findViewById(R.id.location);
        loc.setText(details.getLocation_type());
        TextView address = (TextView) findViewById(R.id.address);
        address.setText(details.getIncident_address() + ", " + details.getIncident_zip() + ", " + details.getCity());
        TextView borough = (TextView) findViewById(R.id.borough);
        borough.setText(details.getBorough());
        TextView coord = (TextView) findViewById(R.id.coordinates);
        coord.setText("Longitude: " + details.getLongtitude() + "Latitude: " + details.getLatitude());
    }
}
