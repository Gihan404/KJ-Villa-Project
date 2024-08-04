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

public class EmployeeDeleteActivity extends AppCompatActivity {


    private TextView staffIdTV;
    private TextView firstNameTV;
    private TextView lastNameTV;

    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_emloyee);

        dataHandler.openDB();

        staffIdTV = findViewById(R.id.staffIdDeleteEmp);
        firstNameTV = findViewById(R.id.firstNameDeleteEmp);
        lastNameTV = findViewById(R.id.lastNameDeleteEmp);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickBackToAdminHome(View view){
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }

    public void onClickSearchDeleteEmp(View view){
        if (staffIdTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter Staff ID field.", Toast.LENGTH_LONG).show();
        }else{

            try {
                String staffId = staffIdTV.getText().toString();
                Employee employee = new Employee(staffId);
                boolean flag = dataHandler.searchEmployee(employee);
                if (flag) {
                    staffIdTV.setText(employee.getStaffId());
                    firstNameTV.setText(employee.getFirstName());
                    lastNameTV.setText(employee.getLastName());
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Staff ID", Toast.LENGTH_LONG).show();
                    firstNameTV.setText("");
                    lastNameTV.setText("");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void onClickDeleteEmployee(View view){

        String staffId = staffIdTV.getText().toString();
        int empStatus = 0;
        Employee employee = new Employee(staffId,empStatus);

        try {
            boolean flag = dataHandler.deleteEmployee(employee);
            if (flag) {
                Toast.makeText(getApplicationContext(), "Employee successfully deleted.", Toast.LENGTH_LONG).show();
                staffIdTV.setText("");
                firstNameTV.setText("");
                lastNameTV.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "Employee deleting failed.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}