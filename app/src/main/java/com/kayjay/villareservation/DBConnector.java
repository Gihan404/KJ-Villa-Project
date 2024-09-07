package com.kayjay.villareservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {

    public DBConnector(Context con){
        super(con, "kj_villa", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE client(client_id INTEGER primary key AUTOINCREMENT, first_name TEXT," +
                " last_name TEXT, nic TEXT, email TEXT, contact_no TEXT, street_no TEXT, street_name TEXT," +
                " city TEXT, postal_code TEXT, country TEXT, username TEXT, password TEXT, type TEXT, status INT, emp_id INTEGER)");

        db.execSQL("CREATE TABLE employee(emp_id INTEGER primary key AUTOINCREMENT, staff_id TEXT, first_name TEXT, last_name TEXT," +
                " designation TEXT, email TEXT, contact_no TEXT, username TEXT, user_role TEXT, password TEXT, status INTEGER)");

        db.execSQL("CREATE TABLE room_type(type_id INTEGER primary key AUTOINCREMENT, type TEXT NOT NULL, description TEXT, " + "price REAL, adults INTEGER, children INTEGER, wifi INTEGER, ac INTEGER, tv INTEGER, bar INTEGER)");


        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "admin");
        contentValues.put("password", "admin");
        contentValues.put("user_role", "Administrator");
        db.insert("employee", null, contentValues);

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("username", "user");
        contentValues1.put("password", "user");
        contentValues1.put("user_role", "Receptionist");
        db.insert("employee", null, contentValues1);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("username", "client");
        contentValues3.put("password", "client");
        contentValues3.put("type", "client");
        db.insert("client", null, contentValues3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
