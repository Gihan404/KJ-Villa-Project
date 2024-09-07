package com.kayjay.villareservation;

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


public class ClientRegisterAdminActivity extends AppCompatActivity {

    private TextView fNameTV;
    private TextView lNameTV;
    private TextView nicNoTV;
    private TextView emailAddressTV;
    private TextView contactNoTV;
    private TextView streetNoTV;
    private TextView streetNameTV;
    private TextView cityTV;

    private TextView postalCodeTV;
    private TextView countryTV;
    private TextView usernameTV;
    private TextView passwordTV;
    private TextView conPasswordTV;

    DataHandler dataHandler = new DataHandler(this);

    private String MOBILE_REGEX = "^(?:7|0|(?:\\+94))[0-9]{9}$";
    private String EMAIL_REGEX = "^[\\w-\\.]+@[a-zA-Z_.]+?\\.[a-zA-Z]{2,3}$";
    private String NIC_REGEX = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_client_register_admin);

        fNameTV = (TextView) findViewById(R.id.fNameRegisterClient);
        lNameTV = (TextView) findViewById(R.id.lNameRegisterClient);
        nicNoTV = (TextView) findViewById(R.id.nicNoRegisterClient);
        emailAddressTV = (TextView) findViewById(R.id.emailRegisterClient);
        contactNoTV = (TextView) findViewById(R.id.contactNoRegisterClient);
        streetNoTV = (TextView) findViewById(R.id.streetNoRegisterClient);
        streetNameTV = (TextView) findViewById(R.id.streetNameRegisterClient);
        cityTV = (TextView) findViewById(R.id.cityRegisterClient);
        countryTV = (TextView) findViewById(R.id.countryRegisterClient);
        postalCodeTV = (TextView) findViewById(R.id.postalCodeRegisterClient);
        usernameTV = (TextView) findViewById(R.id.usernameRegisterClient);
        passwordTV = (TextView) findViewById(R.id.passwordRegisterClient);
        conPasswordTV = (TextView) findViewById(R.id.conPasswordRegisterClient);

        dataHandler.openDB();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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

    public void onClickRegisterNewClient(View view){

        System.out.println("in in");
        String firstName = fNameTV.getText().toString();
        String lastName = lNameTV.getText().toString();
        String nic = nicNoTV.getText().toString();
        String email = emailAddressTV.getText().toString();
        String contactNo = contactNoTV.getText().toString();
        String streetNo = streetNoTV.getText().toString();
        String streetName = streetNameTV.getText().toString();
        String city = cityTV.getText().toString();
        String postalCode = postalCodeTV.getText().toString();
        String country = countryTV.getText().toString();
        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();
        String confirmPassword = conPasswordTV.getText().toString();

        Client client = new Client(firstName, lastName, nic, email, contactNo, streetNo, streetName,
                city, postalCode, country, username, password);
        System.out.println("test 2");
        if (fNameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill first name field", Toast.LENGTH_LONG).show();
        } else if(lNameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill last name field", Toast.LENGTH_LONG).show();
        } else if(nicNoTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill NIC field", Toast.LENGTH_LONG).show();
        } else if(emailAddressTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill email field", Toast.LENGTH_LONG).show();
        } else if(contactNoTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill contact number field", Toast.LENGTH_LONG).show();
        } else if(streetNoTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill street number field", Toast.LENGTH_LONG).show();
        }else if(streetNameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill street name field", Toast.LENGTH_LONG).show();
        }else if(cityTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill city field", Toast.LENGTH_LONG).show();
        }else if(countryTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill country field", Toast.LENGTH_LONG).show();
        }else if(usernameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill username field", Toast.LENGTH_LONG).show();
        }else if(passwordTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill password field", Toast.LENGTH_LONG).show();
        } else if (validateRegex(emailAddressTV.getText().toString(), EMAIL_REGEX)){

            if(validateRegex(contactNoTV.getText().toString(), MOBILE_REGEX)){

                if(validateRegex(nicNoTV.getText().toString(), NIC_REGEX)){
                    if(password.equals(confirmPassword)){
                        try {
                            System.out.println("ready");
                            if (dataHandler.checkClient(client)) {
                                Toast.makeText(getApplicationContext(), "Client cannot be registered. This client already existing.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    if (dataHandler.createClient(client)) {
                                        Toast.makeText(getApplicationContext(), "Client successfully created",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Client creation failed", Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "Password does not match!",
                                Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Incorrect NIC number", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Incorrect contact number", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Incorrect email", Toast.LENGTH_LONG).show();
        }
    }
}