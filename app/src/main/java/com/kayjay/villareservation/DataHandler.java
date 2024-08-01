package com.kayjay.villareservation;

import android.content.ContentValues;
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

    //Search Client By contact number and email address
    public boolean checkClient(Client client) throws Exception {
        Cursor cursor = db.rawQuery("select * from client where email = '"+client.getEmail()+"'", null);
        return cursor.moveToFirst();
    }

    public boolean createClient(Client client) throws Exception{
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", client.getFirstName());
        contentValues.put("last_name", client.getLastName());
        contentValues.put("nic", client.getNic());
        contentValues.put("email", client.getEmail());
        contentValues.put("contact_no", client.getContactNo());
        contentValues.put("street_no", client.getStreetNo());
        contentValues.put("street_name", client.getStreetName());
        contentValues.put("city", client.getCity());
        contentValues.put("postal_code", client.getPostalCode());
        contentValues.put("country", client.getCountry());
        contentValues.put("username", client.getUsername());
        contentValues.put("password", client.getPassword());
        contentValues.put("type", "client");
        contentValues.put("status", 1);

        boolean return_value = false;

        long result = db.insert("client", null, contentValues);

        if (result == -1){
            return_value = false;
        } else {
            return_value = true;

        }
        return return_value;
    }

    public boolean viewClient(Client client) throws Exception{
        Cursor cursor = db.rawQuery("select * from client where client_id = '" + Client.getIdNo() + "'  ", null);

        while(cursor.moveToNext()){
            client.setFirstName(cursor.getString(1));
            client.setLastName(cursor.getString(2));
            client.setNic(cursor.getString(3));
            client.setEmail(cursor.getString(4));
            client.setContactNo(cursor.getString(5));
            client.setStreetNo(cursor.getString(6));
            client.setStreetName(cursor.getString(7));
            client.setCity(cursor.getString(8));
            client.setPostalCode(cursor.getString(9));
            client.setCountry(cursor.getString(10));
            client.setUsername(cursor.getString(11));
            client.setPassword(cursor.getString(12));
            return true;
        }
        return false;
    }

}
