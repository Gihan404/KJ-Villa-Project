package com.kayjay.villareservation;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClientUpdateActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_client_update);

        fNameTV = (TextView) findViewById(R.id.fNameUpdateClient);
        lNameTV = (TextView) findViewById(R.id.lNameUpdateClient);
        nicNoTV = (TextView) findViewById(R.id.nicNoUpdateClient);
        emailAddressTV = (TextView) findViewById(R.id.emailUpdateClient);
        contactNoTV = (TextView) findViewById(R.id.contactNoUpdateClient);
        streetNoTV = (TextView) findViewById(R.id.streetNoUpdateClient);
        streetNameTV = (TextView) findViewById(R.id.streetNameUpdateClient);
        cityTV = (TextView) findViewById(R.id.cityUpdateClient);
        postalCodeTV = (TextView) findViewById(R.id.postalCodeUpdateClient);
        countryTV = (TextView) findViewById(R.id.countryUpdateClient);
        usernameTV = (TextView) findViewById(R.id.usernameUpdateClient);
        passwordTV = (TextView) findViewById(R.id.passwordUpdateClient);
        conPasswordTV = (TextView) findViewById(R.id.conPasswordUpdateClient);

        dataHandler.openDB();

        Client client = new Client();

        try {
            if(dataHandler.viewClient(client)){
                fNameTV.setText(client.getFirstName());
                lNameTV.setText(client.getLastName());
                nicNoTV.setText(client.getNic());
                emailAddressTV.setText(client.getEmail());
                contactNoTV.setText(client.getContactNo());
                streetNoTV.setText(client.getStreetNo());
                streetNameTV.setText(client.getStreetName());
                cityTV.setText(client.getCity());
                countryTV.setText(client.getCountry());
                usernameTV.setText(client.getUsername());
                passwordTV.setText(client.getPassword());

            } else{
                Toast.makeText(getApplicationContext(), "Search Failed", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}