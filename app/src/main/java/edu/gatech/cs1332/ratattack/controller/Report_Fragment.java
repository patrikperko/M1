package edu.gatech.cs1332.ratattack.controller;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.gatech.cs1332.ratattack.R;

public class Report_Fragment extends Fragment {
    public static Report_Fragment newInstance() {
        Report_Fragment fragment = new Report_Fragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_report__fragment, container, false);
    }
}
