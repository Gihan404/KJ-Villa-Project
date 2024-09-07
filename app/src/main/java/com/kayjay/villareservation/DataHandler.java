package com.kayjay.villareservation;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;

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

    public boolean checkEmployee(Employee employee) throws Exception{
        Cursor cursor = db.rawQuery("select * from employee where staff_id = '"+employee.getStaffId()+"'", null);
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public boolean createEmployee(Employee employee) throws  Exception{
        ContentValues contentValues = new ContentValues();
        contentValues.put("staff_id", employee.getStaffId());
        contentValues.put("first_name", employee.getFirstName());
        contentValues.put("last_name", employee.getLastName());
        contentValues.put("designation", employee.getDesignation());
        contentValues.put("email", employee.getEmail());
        contentValues.put("contact_no", employee.getContactNo());
        contentValues.put("username", employee.getUsername());
        contentValues.put("user_role", employee.getUserRole());
        contentValues.put("password", employee.getPassword());
        contentValues.put("status", employee.getEmpStatus());

        long result = db.insert("employee", null, contentValues);
        if (result == -1) {
            return false;
        }else {
            return true;
        }

    }

    public boolean searchEmployee(Employee employee) throws Exception{
        Cursor cursor = db.rawQuery("select * from employee where staff_id = '"+employee.getStaffId()+"' and status = 1", null);
        if (cursor.moveToFirst()){
            employee.setStaffId(cursor.getString(1));
            employee.setFirstName(cursor.getString(2));
            employee.setLastName(cursor.getString(3));
            employee.setDesignation(cursor.getString(4));
            employee.setEmail(cursor.getString(5));
            employee.setContactNo(cursor.getString(6));
            employee.setUsername(cursor.getString(7));
            employee.setUserRole(cursor.getString(8));
            employee.setPassword(cursor.getString(9));
            return true;
        }
        return false;
    }

    public boolean updateEmployee(Employee employee) throws Exception{
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", employee.getFirstName());
        contentValues.put("last_name", employee.getLastName());
        contentValues.put("designation", employee.getDesignation());
        contentValues.put("email", employee.getEmail());
        contentValues.put("contact_no", employee.getContactNo());
        contentValues.put("username", employee.getUsername());
        contentValues.put("user_role", employee.getUserRole());
        contentValues.put("password", employee.getPassword());
        contentValues.put("status", employee.getEmpStatus());

        long result = db.update("employee", contentValues, "staff_id = '" + employee.getStaffId() + "'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean deleteEmployee(Employee employee) throws Exception{
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", employee.getEmpStatus());

        long result =db.update("employee", contentValues, "staff_id = '"+ employee.getStaffId() +"'", null);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean createRoomType(RoomType roomType) throws Exception{
        // insert room type details in to db
        try {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", roomType.getRoomName());
        contentValues.put("description", roomType.getRoomDescription());
        contentValues.put("price", roomType.getRoomPrice());
        contentValues.put("adults", roomType.getNumAdults());
        contentValues.put("children", roomType.getNumChildren());
        contentValues.put("wifi", roomType.isHasWiFi() ? 1 : 0);
        contentValues.put("ac", roomType.isHasAC() ? 1 : 0);
        contentValues.put("tv", roomType.isHasTV() ? 1 : 0);
        contentValues.put("bar", roomType.isHasMinibar() ? 1 : 0);

        System.out.println("Room Name: " + roomType.getRoomName());
        System.out.println("Room Description: " + roomType.getRoomDescription());
        System.out.println("Room Price: " + roomType.getRoomPrice());
            System.out.println("Number of Adults: " + roomType.getNumAdults());
            System.out.printf("Number of Children: " + roomType.getNumChildren());
            System.out.println("Has WiFi: " + (roomType.isHasWiFi() ? "Yes" : "No"));


        long result = db.insert("room_type", null, contentValues);
        if (result == -1){
            System.err.println("Failed to insert room type into database.");
            return false;
        }else {
            System.out.println("Room type inserted with ID: " + result);
            return true;
        }
        } catch (Exception e) {
            e.printStackTrace(); // This will help you see exactly what went wrong
            return false;
        }
    }

    public ArrayList<String > searchAllRoomTypes() throws Exception{
        ArrayList<String> roomTypes = new ArrayList<String>();
        roomTypes.add("Select Room Type");
        Cursor cursor = db.rawQuery("select * from room_type", null);
        while (cursor.moveToNext()){
            roomTypes.add(cursor.getString(1));
        }
        return roomTypes;
    }

    public boolean createReservation(Reservation reservation) throws  Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("check_in_date", reservation.getCheckInDate());
        contentValues.put("check_out_date", reservation.getCheckOutDate());
        contentValues.put("no_rooms", reservation.getNoOfRooms());
        contentValues.put("board_type", reservation.getBoardingType());
        contentValues.put("price", reservation.getPrice());
        contentValues.put("no_adults", reservation.getNoOfAdults());
        contentValues.put("no_children", reservation.getNoOfChildren());
        contentValues.put("res_name", reservation.getResName());
        contentValues.put("res_email", reservation.getResEmail());
        contentValues.put("res_contact", reservation.getResContactNo());
        contentValues.put("status", reservation.getStatus());
        contentValues.put("roomType", reservation.getRoomType());
        long result = db.insert("reservation", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean searchRoomTypes(RoomType roomType){
        Cursor cursor = db.rawQuery("select * from room_type where type = '"+roomType.getRoomName()+"'", null);
        if (cursor.moveToFirst()){
            roomType.setRoomName(cursor.getString(1));
            roomType.setRoomDescription(cursor.getString(2));
            roomType.setNumChildren(cursor.getInt(4));
            roomType.setNumAdults(cursor.getInt(5));
            roomType.setRoomPrice(cursor.getDouble(6));
            return true;
        }
        return false;
    }
}
