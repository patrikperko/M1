package edu.gatech.cs1332.ratattack.controller;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.cs1332.ratattack.R;

public class activity_loginsuccessful extends AppCompatActivity implements NavigationFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccessful);
        NavigationFragment fr = new NavigationFragment();
        //fr.setArguments(args);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();


    }

    public void onFragmentInteraction(Uri uri) {

    }
}
