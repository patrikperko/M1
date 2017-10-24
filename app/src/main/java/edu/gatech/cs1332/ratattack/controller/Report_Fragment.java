package edu.gatech.cs1332.ratattack.controller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.text.DateFormat;
import java.util.Date;

import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Rat;
import edu.gatech.cs1332.ratattack.R;
import android.support.v4.app.FragmentTransaction;
public class Report_Fragment extends Fragment {
    public static Report_Fragment newInstance() {
        Report_Fragment fragment = new Report_Fragment();
        return fragment;
    }
    private EditText locationText;
    private EditText zipText;
    private EditText addressText;
    private EditText cityText;
    private EditText boroughText;
    //private EditText keyText;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_report__fragment, container, false);
        locationText = (EditText) rootView.findViewById(R.id.locationType);
        zipText = (EditText) rootView.findViewById(R.id.zipCode);
        addressText = (EditText) rootView.findViewById(R.id.address);
        cityText = (EditText) rootView.findViewById(R.id.city);
        boroughText = (EditText) rootView.findViewById(R.id.borough);
        //keyText = (EditText) rootView.findViewById(R.id.key);
        final Button report = (Button) rootView.findViewById(R.id.report);
        final Button cancel = (Button) rootView.findViewById(R.id.reportcancel);
        int upperBound = 49000000;
        int lowerBound = 48000000;
        int number = lowerBound + (int)(Math.random() * ((upperBound - lowerBound) + 1));
        final String uniquekey = Integer.toString(number);
        report.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            if (locationText.getText().toString().trim().length() > 0 && zipText.getText().toString().trim().length() > 0 &&
//                    cityText.getText().toString().trim().length() > 0 && addressText.getText().toString().trim().length() > 0 &&
//                    boroughText.getText().toString().trim().length() > 0 && keyText.getText().toString().trim().length() > 0) {
                Rat newRat = new Rat(uniquekey, DateFormat.getDateInstance().format(new Date()), locationText.getText()
                        .toString(), zipText.getText().toString(), addressText.getText().toString(), cityText.getText().toString(),
                        boroughText.getText().toString(), null, null);
                Database.getInstance().addRat(newRat);
                getActivity().getSupportFragmentManager().beginTransaction().remove(Report_Fragment.this).commit();

//            }
        }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                locationText.setText(null);
                zipText.setText(null);
                addressText.setText(null);
                cityText.setText(null);
                boroughText.setText(null);
            }
        });
        return rootView;
    }
}
