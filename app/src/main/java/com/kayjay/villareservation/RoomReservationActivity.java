package com.kayjay.villareservation;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomReservationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView adultsTV;
    private TextView childrenTV;
    private TextView noOfRoomsTV;
    private TextView boardingTypeTV;
    private TextView resNameTV;
    private TextView resContactNoTV;
    private TextView fromDateTV;
    private TextView toDateTV;
    private TextView resEmailTV;
    private TextView priceTV;
    private TextView roomPriceTV;
    private int year, month, day;

    private String MOBILE_REGEX = "^(?:7|0|(?:\\+94))[0-9]{9}$";
    private String EMAIL_REGEX = "^[\\w-\\.]+@[a-zA-Z_.]+?\\.[a-zA-Z]{2,3}$";

    private String selectedRoomType;
    private ArrayList<String> roomTypes;

    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_reservation);

        fromDateTV = (TextView) findViewById(R.id.fromDateRoomReservation);
        toDateTV = (TextView) findViewById(R.id.toDateRoomReservation);

        dataHandler.openDB();

        adultsTV = (TextView) findViewById(R.id.adultsResForm);
        childrenTV = (TextView) findViewById(R.id.childrenResForm);
        noOfRoomsTV = (TextView) findViewById(R.id.noRoomsResForm);
        boardingTypeTV = (TextView) findViewById(R.id.bordTypeResForm);
        resNameTV = (TextView) findViewById(R.id.nameResForm);
        resContactNoTV = (TextView) findViewById(R.id.contactResForm);
        resEmailTV = (TextView) findViewById(R.id.emailResForm);
        priceTV = (TextView) findViewById(R.id.totalPriceResForm);
        roomPriceTV = (TextView) findViewById(R.id.roomPriceResForm);

        try {
            roomTypes = dataHandler.searchAllRoomTypes();
            Spinner spinner = (Spinner) findViewById(R.id.spRoomTypeResForm);
            spinner.setOnItemSelectedListener(this);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomTypes);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onFromDateClickRoomReservation(View view){
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fromDateTV.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void onToDateClickRoomReservation(View view){
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                toDateTV.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private boolean validateRegex(String filedValue, String regex){

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filedValue);
        if (matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public void onClickCreateReservation(View view){
        if (adultsTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill number of adults field.", Toast.LENGTH_LONG).show();
        } else if(childrenTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill number of children's field.", Toast.LENGTH_LONG).show();
        }else if(noOfRoomsTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill no of rooms field.", Toast.LENGTH_LONG).show();
        } else if(boardingTypeTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill boarding type field.", Toast.LENGTH_LONG).show();
        } else if(resNameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill client name field.", Toast.LENGTH_LONG).show();
        } else if(resContactNoTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill contact number field.", Toast.LENGTH_LONG).show();
        }  else if(resEmailTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill email field.", Toast.LENGTH_LONG).show();
        }else if(priceTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill price field.", Toast.LENGTH_LONG).show();
        } else if(validateRegex(resEmailTV.getText().toString(), EMAIL_REGEX)){
            if(validateRegex(resContactNoTV.getText().toString(), MOBILE_REGEX)){

                int noOfAdults = Integer.parseInt(adultsTV.getText().toString());
                int noOfChildren =  Integer.parseInt(childrenTV.getText().toString());
                int noOfRooms = Integer.parseInt(noOfRoomsTV.getText().toString());
                String boardingType = boardingTypeTV.getText().toString();
                String resName = resNameTV.getText().toString();
                String resContactNo = resContactNoTV.getText().toString();
                String resEmail = resEmailTV.getText().toString();
                double price = Double.parseDouble(priceTV.getText().toString());
                String fromDate = fromDateTV.getText().toString();
                String toDate = toDateTV.getText().toString();
                int status = 1;
                String roomType = selectedRoomType;
                try{

                    Reservation reservation = new Reservation(fromDate, toDate, noOfRooms, boardingType, price, noOfAdults,
                            noOfChildren, resName, resEmail, resContactNo, status, roomType);
                    boolean flag = dataHandler.createReservation(reservation);
                    if (flag){
                        Toast.makeText(getApplicationContext(), "Reservation Successful.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Reservation Failed.", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        selectedRoomType = adapterView.getItemAtPosition(position).toString();

        if (selectedRoomType.equals("Select Room Type")) {

        }else{
            RoomType roomType = new RoomType(selectedRoomType);
            boolean flag = dataHandler.searchRoomTypes(roomType);
            if (flag) {
                roomPriceTV.setText(roomType.getRoomPrice() + "");
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}