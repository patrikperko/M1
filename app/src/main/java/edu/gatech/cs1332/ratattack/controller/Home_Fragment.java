package edu.gatech.cs1332.ratattack.controller;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.RelativeLayout;

import edu.gatech.cs1332.ratattack.R;

public class Home_Fragment extends Fragment implements View.OnClickListener{
    public static Home_Fragment newInstance() {
        Bundle args = new Bundle();
        Home_Fragment fragment = new Home_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout fl = (RelativeLayout)inflater.inflate(R.layout.activity_home__fragment, container, false);
        Button logout = (Button) fl.findViewById(R.id.logoutbutton);
        logout.setOnClickListener(this);
        return fl;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutbutton:
                getActivity().finish();
                break;
        }
    }

}
