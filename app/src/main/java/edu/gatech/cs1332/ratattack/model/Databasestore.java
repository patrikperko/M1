package edu.gatech.cs1332.ratattack.model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

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
    SQLiteDatabase db;
    public Databasestore(Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
        this.db = db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        String query = "DROP TABLE IF EXISTS" + dbtable;
        db.execSQL(query);
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
