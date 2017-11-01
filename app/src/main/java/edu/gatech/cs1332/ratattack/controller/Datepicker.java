package edu.gatech.cs1332.ratattack.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
//import java.util.Calendar;
/**
 * Created by ziwei on 10/31/17.
 */

public class Datepicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    EditText txtDate;
    String strdate;
    public Datepicker(View v){
        txtDate = (EditText)v;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleformat = new SimpleDateFormat("MM/dd/yyyy");
        strdate = simpleformat.format(c.getTime());
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        SimpleDateFormat simpleformat = new SimpleDateFormat("MM/dd/yyyy");
        String date = simpleformat.format(c.getTime());
        strdate = date;
        txtDate.setText(date);
    }
    public String gettxtdate() {
        strdate = txtDate.getText().toString();
        return strdate;
    }
}
