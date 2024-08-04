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

public class EmployeeUpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

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

    private String[] userRoles = {"Select User Role","Administrator", "User"};
    private String selectedUserRole;

    private String MOBILE_REGEX = "^(?:7|0|(?:\\+94))[0-9]{9}$";
    private String EMAIL_REGEX = "^\\w+@[a-zA-Z_.]+?\\.[a-zA-Z]{2,3}$";

    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_employee);

        dataHandler.openDB();

        staffIdTV = findViewById(R.id.staffIdUpdateEmp);
        firstNameTV = findViewById(R.id.firstNameUpdateEmp);
        lastNameTV = findViewById(R.id.lastNameUpdateEmp);
        emailTV = findViewById(R.id.emailUpdateEmp);
        contactNoTV = findViewById(R.id.contactNoUpdateEmp);
        usernameTV = findViewById(R.id.usernameUpdateEmp);
        passwordTV = findViewById(R.id.passwordUpdateEmp);
        conPasswordTV = findViewById(R.id.conPasswordUpdateEmp);

        Spinner spinner = (Spinner) findViewById(R.id.designationUpdateEmp);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        selectedDesignation = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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

    public void onClickSearchUpdateEmp(View view){

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
                    Spinner spinner = (Spinner) findViewById(R.id.designationUpdateEmp);
                    int position;
                    switch (employee.getDesignation()) {
                        case "Hotel Manager":
                            position = 1;
                            break;
                        case "Supervisor":
                            position = 2;
                            break;
                        case "Cashier":
                            position = 3;
                            break;
                        default:
                            position = 0;
                            break;
                    }

                    spinner.setSelection(position);
                    emailTV.setText(employee.getEmail());
                    contactNoTV.setText(employee.getContactNo());
                    usernameTV.setText(employee.getUsername());

                    groupUserRole = findViewById(R.id.groupUserRoleUpdateEmp);
                    int genderGroupID = groupUserRole.getCheckedRadioButtonId();
                    radioButtonUserRole = (RadioButton) findViewById(genderGroupID);

                    if (employee.getUserRole().equals("Administrator")) {
                        groupUserRole.check(R.id.radioAdminUpdateEmp);
                    } else if (employee.getUserRole().equals("Receptionist")) {
                        groupUserRole.check(R.id.radioReceptionistUpdateEmp);
                    }

                    passwordTV.setText(employee.getPassword());
                    conPasswordTV.setText(employee.getPassword());

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Staff ID", Toast.LENGTH_LONG).show();

                    staffIdTV.setText("");
                    firstNameTV.setText("");
                    lastNameTV.setText("");
                    emailTV.setText("");
                    contactNoTV.setText("");
                    usernameTV.setText("");
                    passwordTV.setText("");
                    conPasswordTV.setText("");
                    groupUserRole = findViewById(R.id.groupUserRoleUpdateEmp);
                    groupUserRole.check(R.id.radioAdminUpdateEmp);
                    Spinner spinner = (Spinner) findViewById(R.id.designationUpdateEmp);
                    spinner.setSelection(0);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void onClickUpdateEmployee(View view){

        groupUserRole = (RadioGroup) findViewById(R.id.groupUserRoleUpdateEmp);
        int genderGroupID = groupUserRole.getCheckedRadioButtonId();
        radioButtonUserRole = (RadioButton) findViewById(genderGroupID);

        if (staffIdTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please search employee details", Toast.LENGTH_LONG).show();
        } else if (firstNameTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill first name field.", Toast.LENGTH_LONG).show();
        } else if (lastNameTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill last name field.", Toast.LENGTH_LONG).show();
        } else if (selectedDesignation.equals("Select Designation")) {
            Toast.makeText(getApplicationContext(), "Please select employee designation.", Toast.LENGTH_LONG).show();
        } else if (emailTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill email field.", Toast.LENGTH_LONG).show();
        } else if (contactNoTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill contact Number field.", Toast.LENGTH_LONG).show();
        }else if (usernameTV.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill username field.", Toast.LENGTH_LONG).show();
        }else if (passwordTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill password field.", Toast.LENGTH_LONG).show();
        }else if (conPasswordTV.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill confirm password field.", Toast.LENGTH_LONG).show();
        }else if (!passwordTV.getText().toString().equals(conPasswordTV.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please make sure your passwords match.", Toast.LENGTH_LONG).show();
        } else if (validateRegex(emailTV.getText().toString(), EMAIL_REGEX)) {
            if (validateRegex(contactNoTV.getText().toString(), MOBILE_REGEX)) {

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
                    boolean flag = dataHandler.updateEmployee(employee);
                    if (flag) {
                        Toast.makeText(getApplicationContext(), "Employee successfully updated.", Toast.LENGTH_LONG).show();
                        staffIdTV.setText("");
                        firstNameTV.setText("");
                        lastNameTV.setText("");
                        emailTV.setText("");
                        contactNoTV.setText("");
                        usernameTV.setText("");
                        passwordTV.setText("");
                        conPasswordTV.setText("");
                        groupUserRole = findViewById(R.id.groupUserRoleUpdateEmp);
                        groupUserRole.check(R.id.radioAdminUpdateEmp);
                        Spinner spinner = (Spinner) findViewById(R.id.designationUpdateEmp);
                        spinner.setSelection(0);
                    } else {
                        Toast.makeText(getApplicationContext(), "Employee updating failed.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect contact number", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect email", Toast.LENGTH_LONG).show();
        }
    }
}