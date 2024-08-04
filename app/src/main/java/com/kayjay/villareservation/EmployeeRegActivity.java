package com.kayjay.villareservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeRegActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView staffIdTV;
    private TextView firstNameTV;
    private TextView lastNameTV;
    private TextView emailTV;
    private TextView contactNoTV;
    private TextView usernameTV;
    private TextView passwordTV;
    private TextView conPasswordTV;

    private RadioGroup groupUserRole;
    private RadioButton radioButtonUserRole;

    private String[] empDesignation = {"Select Designation","Hotel Manager", "Supervisor", "Cashier"};
    private String selectedDesignation;

    private String MOBILE_REGEX = "^(?:7|0|(?:\\+94))[0-9]{9}$";
    private String EMAIL_REGEX = "^[\\w-\\.]+@[a-zA-Z_.]+?\\.[a-zA-Z]{2,3}$";

    DataHandler dataHandler = new DataHandler(this);

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedDesignation = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_reg);

        dataHandler.openDB();

        staffIdTV = findViewById(R.id.staffIdEmpReg);
        firstNameTV = findViewById(R.id.firstNameEmpReg);
        lastNameTV = findViewById(R.id.lastNameEmpReg);
        emailTV = findViewById(R.id.emailEmpReg);
        contactNoTV = findViewById(R.id.contactNoEmpReg);
        usernameTV = findViewById(R.id.usernameEmpReg);
        passwordTV = findViewById(R.id.passwordEmpReg);
        conPasswordTV = findViewById(R.id.conPasswordEmpReg);

        Spinner spinner = (Spinner) findViewById(R.id.designationEmpReg);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, empDesignation);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

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

    private boolean validateRegex(String filedValue, String regex){

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filedValue);
        if (matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public void onClickRegisterEmployee(View view) {

        groupUserRole = (RadioGroup) findViewById(R.id.groupUserRoleEmpReg);
        int genderGroupID = groupUserRole.getCheckedRadioButtonId();
        radioButtonUserRole = (RadioButton) findViewById(genderGroupID);

        if (staffIdTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill staff id field.", Toast.LENGTH_LONG).show();
        } else if (firstNameTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill first name field.", Toast.LENGTH_LONG).show();
        } else if (lastNameTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill last name field.", Toast.LENGTH_LONG).show();
        } else if (selectedDesignation.equals("Select Designation")) {
            Toast.makeText(getApplicationContext(), "Please select employee designation.", Toast.LENGTH_LONG).show();
        } else if (emailTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill Email field.", Toast.LENGTH_LONG).show();
        } else if (contactNoTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill Contact Number field.", Toast.LENGTH_LONG).show();
        }else if (usernameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Username field.", Toast.LENGTH_LONG).show();
        }else if (passwordTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill Password field.", Toast.LENGTH_LONG).show();
        }else if (conPasswordTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill Confirm Password field.", Toast.LENGTH_LONG).show();
        }else if (!passwordTV.getText().toString().equals(conPasswordTV.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please make sure your passwords match.", Toast.LENGTH_LONG).show();
        }else if (validateRegex(emailTV.getText().toString(), EMAIL_REGEX)){
            if(validateRegex(contactNoTV.getText().toString(), MOBILE_REGEX)){

                String staffId = staffIdTV.getText().toString();
                String firstName = firstNameTV.getText().toString();
                String lastName = lastNameTV.getText().toString();
                String designation = selectedDesignation;
                String email = emailTV.getText().toString();
                String contactNo = contactNoTV.getText().toString();
                String username = usernameTV.getText().toString();
                String userRole = radioButtonUserRole.getText().toString();
                String password = passwordTV.getText().toString();
                int empStatus = 1;

                Employee employee = new Employee(staffId, firstName, lastName, designation, email, contactNo, username, userRole, password, empStatus);
                try {

                    if (dataHandler.checkEmployee(employee)) {
                        Toast.makeText(getApplicationContext(), "Employee can't be registered. This employee already existing.", Toast.LENGTH_LONG).show();
                    }else{
                        boolean flag = dataHandler.createEmployee(employee);
                        if (flag) {
                            Toast.makeText(getApplicationContext(), "Employee successfully Created", Toast.LENGTH_LONG).show();
                            staffIdTV.setText("");
                            firstNameTV.setText("");
                            lastNameTV.setText("");
                            emailTV.setText("");
                            contactNoTV.setText("");
                            usernameTV.setText("");
                            passwordTV.setText("");
                            conPasswordTV.setText("");
                            groupUserRole = findViewById(R.id.groupUserRoleEmpReg);
                            groupUserRole.check(R.id.radioAdminEmpReg);
                            Spinner spinner = (Spinner) findViewById(R.id.designationEmpReg);
                            spinner.setSelection(0);
                        } else {
                            Toast.makeText(getApplicationContext(), "Employee Creation failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect contact number.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect email.", Toast.LENGTH_LONG).show();
        }
    }
}

