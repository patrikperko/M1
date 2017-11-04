package edu.gatech.cs1332.ratattack.controller;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Rat;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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



        List<Rat> rats = Database.getInstance().getRats();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Calendar c = java.util.Calendar.getInstance();
        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c2 = java.util.Calendar.getInstance();

        RadioGroup type = (RadioGroup) graphView.findViewById(R.id.select);

        Date date1 = null;
        try {
            date1 = sdf.parse("09/03/2012");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = sdf.parse("09/05/2012");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int[] counter;
        c1.setTime(date1);
        c2.setTime(date2);
        if (type.getCheckedRadioButtonId() == R.id.Month) {
            counter = new int[12 * (c2.get(Calendar.YEAR)
                - c1.get(Calendar.YEAR)) + c2.get(Calendar.MONTH)
                - c1.get(Calendar.MONTH) + 1];
        } else {
            counter = new int[c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)
                + 1];
        }

        for(Rat i : rats) {
            String ratdate = i.getCreate_date();
            String report = ratdate.substring(0,10);

            if (!(ratdate.equals(null))) {
                Date reportdate = c.getTime();
                try {
                    reportdate = sdf.parse(report);

                } catch (ParseException e) {
                    System.out.println("cannot parse");
                    e.printStackTrace();
                }
                if (reportdate.before(date2) && reportdate.after(date1)) {
                    System.out.println("works");

                    if (type.getCheckedRadioButtonId() == R.id.Month) {
                        counter[12 * (c.get(Calendar.YEAR)
                            - c1.get(Calendar.YEAR)) + c.get(Calendar.MONTH)
                            - c1.get(Calendar.MONTH)]++;
                    } else {
                        counter[c.get(Calendar.YEAR) - c1.get(Calendar.YEAR)]++;
                    }
                }
//
//
            }




        }


        /*LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(x, y),
                new DataPoint(x + 1, y + 1),
                new DataPoint(x + 2, y + 2)
        });*/
        DataPoint[] values = new DataPoint[counter.length];
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(values);

        graph.addSeries(series);
    }
}
