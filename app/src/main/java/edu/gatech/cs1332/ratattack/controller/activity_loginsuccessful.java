package edu.gatech.cs1332.ratattack.controller;

import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
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

    private List<Rat> rats = new ArrayList<>();
    Database data = Database.getInstance();

    /**
     * Creates a loginsuccessful activity and adds a navigation fragment to the activity
     *
     * @param savedInstanceState the bundle used for onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccessful);

        //Set the default display to Home fragment
        Home_Fragment homefr = Home_Fragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.loginsuccessful_layout, homefr)
                .commit();
        //Implement Navigation menu
        //When the user click any option, will switch fragment
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
                    default:
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginsuccessful_layout, selectedFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
        });



        new CSVFile().execute();
    }

    private class CSVFile extends AsyncTask<List<Rat>, Void, List<Rat>> {


        @Override
        protected List<Rat> doInBackground(List<Rat>... params) {
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
            return rats;
        }

        @Override
        protected void onPostExecute(List<Rat> result) {

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


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