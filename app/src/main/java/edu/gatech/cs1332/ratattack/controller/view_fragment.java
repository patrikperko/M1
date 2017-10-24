package edu.gatech.cs1332.ratattack.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Rat;

public class view_fragment extends Fragment {
    Database data = Database.getInstance();
    private String[] values = new String[] { "Android List View",
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Android Example List View"
    };
    private ArrayAdapter<String> adapter;
    private List<String> ratListHeader = new ArrayList<String>();
    public static view_fragment newInstance() {
        view_fragment fragment = new view_fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //add Filter

//        setContentView(R.layout.activity_view_fragment);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_view_fragment, container, false);
        final EditText filter = (EditText)v.findViewById(R.id.searchFilter);


        ListView listView = (ListView)v.findViewById(R.id.ratdatalist);
        List<Rat> ratList = Database.getInstance().getRats();

        for (int i = 0; i < ratList.size(); ++i) {
            ratListHeader.add("Rat Sighting #" + ratList.get(i).getUniquekey());
        }

        adapter = new ArrayAdapter<String> (
          getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, ratListHeader
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), activity_ratdatadetails.class);
                i.putExtra("key", Database.getInstance().getRats().get(position));
                getActivity().startActivity(i);
            }
        });
//        System.out.println(ratListHeader.get(ratListHeader.size()-1));
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (view_fragment.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;

    }

}
