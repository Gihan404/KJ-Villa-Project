package com.kayjay.villareservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomTypeCreateActivity extends AppCompatActivity {

    private EditText roomNameEditText;
    private EditText roomDescriptionEditText;
    private EditText roomPriceEditText;
    private EditText numAdultsEditText;
    private EditText numChildrenEditText;
    private CheckBox wifiCheckBox;
    private CheckBox acCheckBox;
    private CheckBox tvCheckBox;
    private CheckBox minibarCheckBox;

    private String AMOUNT_REGEX = "[+-]?\\d*\\.?\\d+";
    private String INTEGER_REGEX = "[+-]?[0-9][0-9]*";

    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_room_type);

        dataHandler.openDB();

        // Initialize UI components
        roomNameEditText = findViewById(R.id.roomName);
        roomDescriptionEditText = findViewById(R.id.roomDescription);
        roomPriceEditText = findViewById(R.id.roomPrice);
        numAdultsEditText = findViewById(R.id.numAdults);
        numChildrenEditText = findViewById(R.id.numChildren);
        wifiCheckBox = findViewById(R.id.facilityWiFi);
        acCheckBox = findViewById(R.id.facilityAC);
        tvCheckBox = findViewById(R.id.facilityTV);
        minibarCheckBox = findViewById(R.id.facilityMinibar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }

    private boolean validateRegex(String fieldValue, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fieldValue);

        if (matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public void onClickSaveRoomType(View view) {

        if (roomNameEditText.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill room type field.", Toast.LENGTH_LONG).show();
        }else if (roomDescriptionEditText.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Description field.", Toast.LENGTH_LONG).show();
        }else if (roomPriceEditText.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill Price field.", Toast.LENGTH_LONG).show();
        }else if (validateRegex(numAdultsEditText.getText().toString(), INTEGER_REGEX)) {
            if (validateRegex(numChildrenEditText.getText().toString(), INTEGER_REGEX)) {
                if (validateRegex(roomPriceEditText.getText().toString(), AMOUNT_REGEX)) {
                    // Capture inputs
                    String roomName = roomNameEditText.getText().toString();
                    String roomDescription = roomDescriptionEditText.getText().toString();

                    // Convert numeric inputs
                    double roomPrice =  Double.parseDouble(roomPriceEditText.getText().toString());
                    int numAdults = Integer.parseInt(numAdultsEditText.getText().toString());
                    int numChildren = Integer.parseInt(numChildrenEditText.getText().toString());

                    // Capture facilities
                    boolean hasWiFi = wifiCheckBox.isChecked();
                    boolean hasAC = acCheckBox.isChecked();
                    boolean hasTV = tvCheckBox.isChecked();
                    boolean hasMinibar = minibarCheckBox.isChecked();

                    // Create a new RoomType object
                    RoomType newRoomType = new RoomType(roomName, roomDescription, roomPrice, numAdults, numChildren, hasWiFi, hasAC, hasTV, hasMinibar);

                    try {
                        boolean flag = dataHandler.createRoomType(newRoomType);
                        if (flag) {
                            Toast.makeText(getApplicationContext(), "Room Type successfully created.", Toast.LENGTH_LONG).show();
                            roomNameEditText.setText("");
                            roomDescriptionEditText.setText("");
                            roomPriceEditText.setText("");
                            numAdultsEditText.setText("");
                            numChildrenEditText.setText("");
                            wifiCheckBox.setSelected(false);
                            acCheckBox.setSelected(false);
                            tvCheckBox.setSelected(false);
                            minibarCheckBox.setSelected(false);
                        } else {
                            Toast.makeText(getApplicationContext(), "Room Type creation fail", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter the valid room price.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter valid number of children.", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Please enter valid number of adults.", Toast.LENGTH_LONG).show();
        }
    }

}
