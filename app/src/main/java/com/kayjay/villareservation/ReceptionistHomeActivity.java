package com.kayjay.villareservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReceptionistHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reseptionist_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickUpdateGuestInfo(View view){
        Intent intent = new Intent(this, ClientUpdateWithSearchActivity.class);
        startActivity(intent);
    }

    public void onClickRegisterNewClient(View view){
        Intent intent = new Intent(this, ClientRegisterAdminActivity.class);
        startActivity(intent);
    }

    public void onClickViewReservations(View view){
        Intent intent = new Intent(this, ClientUpdateWithSearchActivity.class);
        startActivity(intent);
    }

    public void onClickLogout(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}