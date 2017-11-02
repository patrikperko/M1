package edu.gatech.cs1332.ratattack.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.cs1332.ratattack.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Patrik on 11/1/2017.
 */

public class Graph_Fragment extends Fragment implements View.OnClickListener {
    private static final String DATE = "date";

    View graphView;
    String fromdate;
    String todate;

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
        graphView = inflater.inflate(
                R.layout.activity_graph_fragment,
                container,
                false);
        return graphView;
    }

    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText startdate = (EditText)graphView.findViewById(R.id.startdate);
        startdate.setKeyListener(null);
        startdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Datepicker dialog = new Datepicker(v);
                fromdate = dialog.gettxtdate();
                dialog.show(getActivity().getFragmentManager(),DATE);
            }
        });
        final EditText enddate = (EditText)graphView.findViewById(R.id.enddate);
        enddate.setKeyListener(null);
        enddate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Datepicker dialog = new Datepicker(v);
                todate = dialog.gettxtdate();
                dialog.show(getActivity().getFragmentManager(),DATE);
            }
        });

        updateGraph(0,0);
        Button graphButton = (Button) graphView.findViewById(R.id.graphButton);
        graphButton.setOnClickListener(this);

    }

    public void onClick(View graphView) {
        switch (graphView.getId()) {
            case R.id.graphButton:
                updateGraph(3, 3);
                break;
        }
    }

    private void updateGraph(int x, int y) {
        GraphView graph = (GraphView) graphView.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(x, y),
                new DataPoint(x + 1, y + 1),
                new DataPoint(x + 2, y + 2)
        });

        graph.addSeries(series);
    }
}
