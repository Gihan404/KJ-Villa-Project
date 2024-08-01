package com.kayjay.villareservation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClientInquiryActivity extends AppCompatActivity {

    private EditText editTextInquiryTitle;
    private Spinner spinnerInquiryType;
    private EditText editTextInquiryDetails;
    private Button buttonSubmitInquiry;
    private RecyclerView recyclerViewInquiries;

    private List<Inquiry> inquiryList;
    private InquiryAdapter inquiryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_client_inquiry);

        editTextInquiryTitle = findViewById(R.id.editTextInquiryTitle);
        spinnerInquiryType = findViewById(R.id.spinnerInquiryType);
        editTextInquiryDetails = findViewById(R.id.editTextInquiryDetails);
        buttonSubmitInquiry = findViewById(R.id.buttonSubmitInquiry);
        recyclerViewInquiries = findViewById(R.id.recyclerViewInquiries);

        // Initialize inquiry list and adapter
        inquiryList = new ArrayList<>();
        inquiryAdapter = new InquiryAdapter(inquiryList);

        recyclerViewInquiries.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInquiries.setAdapter(inquiryAdapter);

        // Handle submit button click
        buttonSubmitInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInquiry();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void submitInquiry() {
        String title = editTextInquiryTitle.getText().toString().trim();
        String type = spinnerInquiryType.getSelectedItem().toString();
        String details = editTextInquiryDetails.getText().toString().trim();

        if (title.isEmpty() || details.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new inquiry and add to the list
        Inquiry newInquiry = new Inquiry(title, type, details);
        inquiryList.add(newInquiry);
        inquiryAdapter.notifyDataSetChanged();

        // Clear input fields
        editTextInquiryTitle.setText("");
        editTextInquiryDetails.setText("");

        Toast.makeText(this, "Inquiry submitted", Toast.LENGTH_SHORT).show();
    }
}