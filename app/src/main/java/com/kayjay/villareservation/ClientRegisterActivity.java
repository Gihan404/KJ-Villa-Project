package com.kayjay.villareservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientRegisterActivity extends AppCompatActivity {

    private TextView nameTV;
    private TextView contactNoTV;
    private TextView emailTV;
    private TextView passwordTV;

    private String MOBILE_REGEX = "^(?:7|0|(?:\\+94))[0-9]{9}$";
    private String EMAIL_REGEX = "^[\\w-\\.]+@[a-zA-Z_.]+?\\.[a-zA-Z]{2,3}$";
    DataHandler dataHandler = new DataHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        nameTV = (TextView) findViewById(R.id.nameClientReg);
        contactNoTV = (TextView) findViewById(R.id.contactNoClientReg);
        emailTV = (TextView) findViewById(R.id.emailClientReg);
        passwordTV = (TextView) findViewById(R.id.passwordClientReg);

        dataHandler.openDB();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Move to Start Activity Screen
    public void onClickBackToStart(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void onClickBackToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean validateRegex(String filedValue, String regex){

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filedValue);
        return matcher.matches();
    }

    public void onClickClientRegister(View view){

        String firstName = nameTV.getText().toString();
        String lastName = "";
        String nic = "";
        String email = emailTV.getText().toString();
        String contactNo = contactNoTV.getText().toString();
        String streetNo ="";
        String streetName = "";
        String city = "";
        String postalCode = "";
        String country = "";
        String username = nameTV.getText().toString();
        String password = passwordTV.getText().toString();

        Client client = new Client(firstName, lastName, nic, email, contactNo, streetNo, streetName,
                city, postalCode, country, username, password);

        if (nameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill name field", Toast.LENGTH_LONG).show();
        }else if(contactNoTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill contact number field", Toast.LENGTH_LONG).show();
        } else if(emailTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill email field", Toast.LENGTH_LONG).show();
        } else if(passwordTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill password field", Toast.LENGTH_LONG).show();
        } else if (validateRegex(emailTV.getText().toString(), EMAIL_REGEX)){

            if(validateRegex(contactNoTV.getText().toString(), MOBILE_REGEX)){

                        try {
                            if (dataHandler.checkClient(client)) {
                                Toast.makeText(getApplicationContext(), "Registration failed. The email address you provided is already in use",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    if (dataHandler.createClient(client)) {
                                        Toast.makeText(getApplicationContext(), "Registration completed successfully",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            }else{
                Toast.makeText(getApplicationContext(), "Incorrect contact number", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Incorrect email", Toast.LENGTH_LONG).show();
        }
    }
}