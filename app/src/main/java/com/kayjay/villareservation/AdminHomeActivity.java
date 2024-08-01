package com.kayjay.villareservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickEmpCreation(View view){
        Intent intent = new Intent(this, EmployeeRegActivity.class);
        startActivity(intent);
    }

    public void onClickUpdateEmp(View view){
        Intent intent = new Intent(this, EmployeeUpdateActivity.class);
        startActivity(intent);
    }

    public void onClickDeleteEmp(View view){
        Intent intent = new Intent(this, EmployeeDeleteActivity.class);
        startActivity(intent);
    }

    public void onClickCreateRoomType(View view){
        Intent intent = new Intent(this, RoomTypeCreateActivity.class);
        startActivity(intent);
    }

    public void onClickUpdateRoomType(View view){
        Intent intent = new Intent(this, RoomTypeUpdateActivity.class);
        startActivity(intent);
    }

    public void onClickCreateRoom(View view){
        Intent intent = new Intent(this, RoomCreateActivity.class);
        startActivity(intent);
    }

    public void onClickUpdateRoom(View view){
        Intent intent = new Intent(this, RoomCreateActivity.class);
        startActivity(intent);
    }

    public void onClickLogout(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}