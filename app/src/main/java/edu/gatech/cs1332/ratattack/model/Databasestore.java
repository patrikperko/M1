package edu.gatech.cs1332.ratattack.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ziwei on 10/9/17.
 */

public class Databasestore extends SQLiteOpenHelper{
    private static final int dbversion = 1;
    //create table with columns
    private static final String dbname = "user.db";
    private static final String dbtable = "user";
    private static final String userid  = "id";
    private static final String username = "name";
    private static final String useremail = "email";
    private static final String userpass = "pass";

    private static final String create_table = "create table user (id integer primary key not null, " +
            "name text not null, email text not null, pass text not null);";



    //create second table with columns
    private static final String report_table = "report";
    private static final String report_id = "id";
    private static final String create_date = "create_date";
    private static final String location_type = "location_type";
    private static final String zip_code = "zip";
    private static final String city = "city";
    private static final String borough = "borough";
    private static final String latitude = "latitude";
    private static final String longitude = "longitude";
    private static final String create_reporttable = "create table report (id integer primary key not null," + "create_date text not null"+
            "location_type text not null" + "zip text not null" + "city text not null" + "borough text not null" + "latitude text not null" + "longitude text not null";


    SQLiteDatabase db;



    public Databasestore(Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
        db.execSQL(create_reporttable);
        this.db = db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        String query = "DROP TABLE IF EXISTS" + dbtable;
        String query2 = "DROP TABLE IF EXISTS" + report_table;
        db.execSQL(query);
        db.execSQL(query2);
        this.onCreate(db);
    }
    public void insertuser(User user) {
        db = this.getWritableDatabase();
        String query = "SELECT*FROM user";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        ContentValues values = new ContentValues();
        values.put(userid, count);
        values.put(username, user.getName());
        values.put(useremail,user.getEmail());
        values.put(userpass, user.getPassword());
        db.insert(dbtable, null, values);
        db.close();
    }
    public void insertreport(Rat rat) {
        db = this.getWritableDatabase();
        String query = "SELECT*FROM rat";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        ContentValues values = new ContentValues();
        values.put(report_id,count);
        values.put(create_date, rat.getCreate_date());
        values.put(location_type,rat.getLocation_type());
        values.put(zip_code,rat.getIncident_zip());
        values.put(city, rat.getCity());
        values.put(borough, rat.getBorough());
        values.put(latitude,rat.getLatitude());
        values.put(longitude, rat.getLongitude());
        db.insert(report_table, null, values);
        db.close();
    }
    public String searchPass(String uname) {
        db = this.getReadableDatabase();
        String searchquery = "SELECT name, pass FROM user";
        Cursor cursor = db.rawQuery(searchquery, null);
        String cursorname;
        String cursorpass;
        cursorpass = "not found";
        if (cursor.moveToFirst()) {
            do {
                cursorname = cursor.getString(0);
                if (cursorname.equals(uname)) {
                    cursorpass = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return cursorpass;
    }
}
