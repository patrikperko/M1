package edu.gatech.cs1332.ratattack.controller;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.User;

/**
 * Created by Andy on 12/2/2017.
 */

public class Admin_Fragment extends Fragment {
    EditText user;

    public static Admin_Fragment newInstance() {
        Admin_Fragment fragment = new Admin_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View admView = inflater.inflate(
                R.layout.activity_admin_fragment,
                container,
                false);
        user = (EditText) admView.findViewById(R.id.toban);
        Button banB = (Button) admView.findViewById(R.id.ban);
        banB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                banning(true);
            }
        });

        Button unBanB = (Button) admView.findViewById(R.id.unban);
        unBanB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                banning(false);
            }
        });

        return admView;
    }

    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void banning(boolean whatDo) {
        String name = user.getText().toString();
        if (!Database.getInstance().userExists(name)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Fragment.this.getContext());
            builder.setTitle("Does not exist");
            builder.setMessage("We couldn't find a user with that username in the database");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.show();
            return;
        }
        Database.getInstance().setBan(name, whatDo);
        AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Fragment.this.getContext());
        builder.setTitle("Done");
        builder.setMessage("The ban status of " + name + " is now " + Database.getInstance().userIsBanned(name));
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.show();
    }

}
