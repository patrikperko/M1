package edu.gatech.cs1332.ratattack.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.gatech.cs1332.ratattack.R;

/**
 * Created by Patrik on 11/1/2017.
 */

public class Graph_Fragment extends Fragment {
    public static Graph_Fragment newInstance() {
        Graph_Fragment fragment = new Graph_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.activity_graph_fragment,
                container,
                false);
        return rootView;
    }
}
