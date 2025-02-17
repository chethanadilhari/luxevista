package com.chethana.luxevista;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ReservationDetailsActivity extends AppCompatActivity {

    EditText availableTime, name, email, mobile, serviceDuration, date;
    TextView totalPriceTextView, serviceName;
    ImageView timePickerIcon, serviceImage;
    FirebaseAuth mAuth;
    DatabaseReference userRef, reservationRef, serviceRef;
    ServiceReservation serviceReservation;
    Button btnConfirm, btnCancel;
    private int mDate, mMonth, mYear, selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservation_details);

        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());

        String serviceId = getIntent().getStringExtra("serviceId");
        serviceRef = FirebaseDatabase.getInstance().getReference().child("services").child(serviceId);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        serviceDuration = findViewById(R.id.duration);
        serviceImage = findViewById(R.id.reserveService);
        serviceName = findViewById(R.id.serviceName);
        totalPriceTextView = findViewById(R.id.totalAmount);

        // Get user profile from Firebase
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    name.setText(snapshot.child("name").getValue().toString());
                    mobile.setText(snapshot.child("mobile").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ReservationDetailsActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        email.setText(mAuth.getCurrentUser().getEmail());

        date = findViewById(R.id.date);
        availableTime = findViewById(R.id.time);
        timePickerIcon = findViewById(R.id.timePicker);
        btnConfirm = findViewById(R.id.confirmButton);
        btnCancel = findViewById(R.id.cancelButton);

        serviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String durationString = snapshot.child("duration").getValue(String.class);
                    serviceDuration.setText(durationString);

                    String imageUrl = snapshot.child("imageUrl").getValue(String.class);
                    String serviceNameValue = snapshot.child("serviceName").getValue(String.class);

                    if (serviceNameValue == null) {
                        serviceNameValue = getIntent().getStringExtra("roomName");
                    }

                    Glide.with(ReservationDetailsActivity.this)
                            .load(imageUrl)
                            .into(serviceImage);

                    serviceName.setText(serviceNameValue);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ReservationDetailsActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Show DatePickerDialog when clicking on the date input
        date.setOnClickListener(v -> selectDate(date));

        // Show TimePickerDialog when clicking on the time input
        timePickerIcon.setOnClickListener(v -> showTimePicker());

        btnCancel.setOnClickListener(view -> onBackPressed());

        btnConfirm.setOnClickListener(view -> {
            btnConfirm.setEnabled(false);

            // Save booking details to Firebase
            reservationRef = FirebaseDatabase.getInstance().getReference().child("ServiceReservations").child(mAuth.getCurrentUser().getUid());
            String reservationId = reservationRef.push().getKey();

            serviceReservation = new ServiceReservation(
                    getIntent().getStringExtra("serviceId"),
                    getIntent().getStringExtra("serviceName"),
                    getIntent().getStringExtra("serviceImageUrl"),
                    getIntent().getStringExtra("serviceDuration"),
                    getIntent().getLongExtra("servicePrice", 0),
                    date.getText().toString(),
                    availableTime.getText().toString() // Correctly set the time

            );

            reservationRef.child(reservationId).setValue(serviceReservation);
            Toast.makeText(ReservationDetailsActivity.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
            onBackPressed();
        });
    }

    private void selectDate(TextView date) {
        final Calendar cal = Calendar.getInstance();
        mDate = cal.get(Calendar.DATE);
        mMonth = cal.get(Calendar.MONTH);
        mYear = cal.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ReservationDetailsActivity.this, android.R.style.Theme_DeviceDefault_Dialog, (datePicker, year, month, dayOfMonth) -> date.setText(dayOfMonth + "-" + (month + 1) + "-" + year), mYear, mMonth, mDate);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        // Get current time
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute1) -> {
                    selectedHour = hourOfDay;
                    selectedMinute = minute1;

                    // Format time as HH:MM (24-hour format)
                    String formattedTime = String.format("%02d:%02d", hourOfDay, minute1);
                    availableTime.setText(formattedTime);
                },
                hour, minute, true // 'true' for 24-hour format
        );

        timePickerDialog.show();
    }
}
