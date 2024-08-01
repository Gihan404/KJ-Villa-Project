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

public class LoginActivity extends AppCompatActivity {

    private TextView usernameTV;
    private TextView passwordTV;

    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        usernameTV = (TextView) findViewById(R.id.usernameEditText);
        passwordTV = (TextView) findViewById(R.id.passwordEditText);

        dataHandler.openDB();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Move to the Start Activity Screen
    public void onClickBackToStart(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    // Move to the Sign Up Activity Screen
    public void onClickSignUp(View view){
        Intent intent = new Intent(this, ClientRegisterActivity.class);
        startActivity(intent);
    }

    public void onClickForgetPassword(View view){
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    //
    public void onClickSignIn(View view){
        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();

        Client client = new Client(username, password);
        if (emptyFieldValidation()){
            Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
        }else{
            try {
                if(dataHandler.LoginVerificationClient(client)){
                    if(client.getType().equals("client")){
                        Intent intent = new Intent(this, ClientHomeActivity.class);
                        intent.putExtra("CLIENT_ID", Client.getIdNo());
                        startActivity(intent);
                    }
                }else if(dataHandler.loginVerificationEmp(client)){
                    if (client.getType().equals("Administrator")){
                        Intent intent = new Intent(this, AdminHomeActivity.class);
                        startActivity(intent);
                    }else if(client.getType().equals("Receptionist")){
                        Intent intent = new Intent(this, ReceptionistHomeActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username, password or role!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public boolean emptyFieldValidation() {
        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();

        if (username.trim().isEmpty()) {
            return true;
        } else if (password.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}