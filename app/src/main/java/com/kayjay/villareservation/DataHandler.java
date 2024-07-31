package com.kayjay.villareservation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataHandler {

    private Context con;

    private DBConnector dbConnector;

    private SQLiteDatabase db;

    public DataHandler(Context con){
        this.con = con;
    }

    public DataHandler openDB(){
        this.dbConnector = new DBConnector(con);
        this.db = dbConnector.getWritableDatabase();
        return this;
    }

    public boolean LoginVerificationClient(Client client){
        Cursor cursor = db.rawQuery("select * from client where username = '"+client.getUsername()+"' and password = '"+client.getPassword()+"'", null);
        if (cursor.moveToFirst()){
            client.setType(cursor.getString(13));
            Client.setIdNo(cursor.getString(0));
            Client.setUserRole(cursor.getString(12));
            return true;
        }
        return false;
    }

    public boolean loginVerificationEmp(Client client) throws Exception{
        Cursor cursor = db.rawQuery("select * from employee where username = " +
                        "'"+client.getUsername()+"' and password = '"+client.getPassword()+"' ",
                null);
        if(cursor.moveToFirst()){
            client.setType(cursor.getString(8));
            Client.setIdNo(cursor.getString(0));
            Client.setUserRole(cursor.getString(8));

            return true;
        }
        return false;
    }

}
