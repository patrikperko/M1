package edu.gatech.cs1332.ratattack.controller;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
//import android.support.design.internal.BottomNavigationItemView;
//import android.support.design.widget.BottomNavigationView;
//import  android.support.design.widget.NavigationView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Rat;
import android.support.annotation.NonNull;
import android.view.MenuItem;

/**
 * The loginsuccessful activity that serves as a landing activity for the login activity.
 * It is not accessible from other activities
 */
public class activity_loginsuccessful extends AppCompatActivity {

    /**
     * Creates a loginsuccessful activity and adds a navigation fragment to the activity
     *
     * @param savedInstanceState the bundle used for onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccessful);
//        NavigationFragment fr = new NavigationFragment();
//        //fr.setArguments(args);
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.fragment, fr);
//        ft.commit();
        BottomNavigationView bottomnavi = (BottomNavigationView)findViewById(R.id.navigation);
        bottomnavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.homebutton:
                        selectedFragment = Home_Fragment.newInstance();
                        break;
                    case R.id.reportbutton:
                        selectedFragment = Report_Fragment.newInstance();
                        break;
                    case R.id.viewbutton:
                        selectedFragment = view_fragment.newInstance();
                        break;
                    case R.id.locationbutton:
                        selectedFragment = location_fragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginsuccessful_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });


        Button ratDataButton = (Button) findViewById(R.id.ratDataButton);

        ratDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity_loginsuccessful.this, RatDataActivity.class);
                activity_loginsuccessful.this.startActivity(i);
            }
        });

        readratdata();
    }
    private List<Rat> rats = new ArrayList<>();
    Database data = Database.getInstance();
    private void readratdata() {
        InputStream is = getResources().openRawResource(R.raw.ratsightings);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line ="";
        try {
            //get rid of the header
            reader.readLine();
            while ((line = reader.readLine())!= null) {
                //debug the line
                //Log.d("Myactivity","Line" + line);
                //split column
                String[] tokens = line.split(",");
                line = line.replaceAll(",,",",NotAvailable,");
                //read data
                Rat newrat = new Rat(tokens[0], tokens[1], tokens[7], tokens[8], tokens[9], tokens[16], tokens[23], tokens[35], tokens[36]);
                data.addRat(newrat);
                rats.add(newrat);
                Log.d("after login", "created:" + newrat);
            }
        } catch(IOException e) {
            Log.wtf("LoginSuccessfully", "Error reading data on line" + line, e);
            e.printStackTrace();
        }

    }



    public void onFragmentInteraction(Uri uri) {

    }
}