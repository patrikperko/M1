package edu.gatech.cs1332.ratattack.controller;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Rat;
import edu.gatech.cs1332.ratattack.R;
import android.support.v4.app.FragmentTransaction;
public class Report_Fragment extends Fragment {
    public static Report_Fragment newInstance() {
        Report_Fragment fragment = new Report_Fragment();
        return fragment;
    }
    private String last_key;
    private EditText locationText;
    private EditText zipText;
    private EditText addressText;
    private EditText cityText;
    private EditText boroughText;
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
        final Button report = (Button) rootView.findViewById(R.id.report);
        final Button cancel = (Button) rootView.findViewById(R.id.reportcancel);
        List<Rat> get_list = Database.getInstance().getRats();
        List<Integer> orderuniquekey = new ArrayList<>();
        for (Rat i: get_list) {
            orderuniquekey.add(Integer.parseInt(i.getUniquekey()));
        }
        //find the maximum uniquekey

        last_key = (Collections.max(orderuniquekey)).toString();
        System.out.println("maximum key" + last_key);
        report.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (locationText.getText().toString().trim().length() > 0 && checkzipcode(zipText.getText().toString().trim())&&
                    cityText.getText().toString().trim().length() > 0 && addressText.getText().toString().trim().length() > 0 &&
                    boroughText.getText().toString().trim().length() > 0) {
                int new_key_number = Integer.parseInt(last_key) + 1;
                String ratuniquekey = Integer.toString(new_key_number);
                Rat newRat = new Rat(ratuniquekey, DateFormat.getDateInstance().format(new Date()), locationText.getText()
                        .toString(), zipText.getText().toString(), addressText.getText().toString(), cityText.getText().toString(),
                        boroughText.getText().toString(), null, null);
                Database.getInstance().addRat(newRat);
                getActivity().getSupportFragmentManager().beginTransaction().remove(Report_Fragment.this).commit();
            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Incorrect Username or Password");
                builder.setMessage("try again :(");
                builder.setPositiveButton("OK",null);
                AlertDialog dialog = builder.show();
            }
        }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                getActivity().onBackPressed();

            }
        });
        return rootView;
    }
    public static boolean checkzipcode(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        int num = Integer.parseInt(s);
        int length = (int) (Math.log10(num)+1);
        if (length == 5) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checklatitude(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        double degree = Double.parseDouble(s);
        if (degree >= -90 && degree <=90) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checklongitude(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        double degree = Double.parseDouble(s);
        if (degree <= -180 && degree >= 180) {
            return true;
        } else {
            return false;
        }
    }
}
