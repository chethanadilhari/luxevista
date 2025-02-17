package com.chethana.luxevista;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class BookingDetailsActivity extends AppCompatActivity {

    EditText checkIn,checkOut, name, email, mobile,noOfGuests;
    TextView roomCount, totalPriceTextView, roomName;
    ImageView calIconCheckIn,calIconCheckOut,roomImage,backButton;
    FirebaseAuth mAuth;
    DatabaseReference userRef,bookingRef,roomRef;
    Button btnIncrease,btnDecrease, btnConfirm, btnCancel;

    RoomBooking roomBooking;
    private int mDate,mMonth,mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_details);

        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());

        String roomId = getIntent().getStringExtra("roomId");
        roomRef = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomId);  // Updated reference to fetch room data by roomId

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        noOfGuests=findViewById(R.id.noOfGuests);
        roomCount=findViewById(R.id.roomCount);
        roomImage=findViewById(R.id.bookingRoom);
        roomName = findViewById(R.id.roomName);

        totalPriceTextView = findViewById(R.id.totalAmount);

        //get user profile from firebase
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                if(snapshot.exists()){
                    name.setText(snapshot.child("name").getValue().toString());
                    mobile.setText(snapshot.child("mobile").getValue().toString());
                }
            }


            @Override
            public void onCancelled( DatabaseError error) {
                Toast.makeText(BookingDetailsActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        email.setText(mAuth.getCurrentUser().getEmail());

        checkIn=findViewById(R.id.date);
        checkOut=findViewById(R.id.checkOutDate);
        calIconCheckIn=findViewById(R.id.datePicker);
        calIconCheckOut=findViewById(R.id.datePickerOut);

        btnIncrease=findViewById(R.id.increaseButton);
        btnDecrease=findViewById(R.id.decreaseButton);
        btnConfirm=findViewById(R.id.confirmButton);
        btnCancel=findViewById(R.id.cancelButton);

        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Get the maxPax value from the room data
                    int maxPax = snapshot.child("maxPax").getValue(Integer.class);
                    noOfGuests.setText(String.valueOf(maxPax));


                    // Get the image URL from the room data
                    String imageUrl = snapshot.child("imageUrl").getValue(String.class);
                    String roomNameValue = snapshot.child("roomName").getValue(String.class);

                    if (roomNameValue == null) {
                        roomNameValue = getIntent().getStringExtra("roomName");
                    }

                    // Load the image into the ImageView using Glide
                    Glide.with(BookingDetailsActivity.this)
                            .load(imageUrl)  // Image URL from Firebase
                            .into(roomImage);  // ImageView where the image will be displayed


                    roomName.setText(roomNameValue);
                }
                }


            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(BookingDetailsActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(roomCount.getText().toString());
                if(count<5){
                    count++;
                    roomCount.setText(String.valueOf(count));
                    calculateTotalPrice();
                }
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(roomCount.getText().toString());
                if(count>1){
                    count--;
                    roomCount.setText(String.valueOf(count));
                    calculateTotalPrice();
                }
            }
        });

        calIconCheckIn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate(checkIn);
            }
        }));

        calIconCheckOut.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate(checkOut);
            }
        }));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageView backButton = findViewById(R.id.roomBackIcon);
        backButton.setOnClickListener(v -> finish()); // Closes the current activity

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnConfirm.setEnabled(false);
                calculateTotalPrice();
                //save booking details to firebase
                bookingRef=FirebaseDatabase.getInstance().getReference().child("RoomBookings").child(mAuth.getCurrentUser().getUid());
                String bookingId = bookingRef.push().getKey();
                roomBooking = new RoomBooking(getIntent().getStringExtra("roomId"),
                        getIntent().getStringExtra("roomName"),
                        getIntent().getStringExtra("roomImageUrl"),
                        getIntent().getLongExtra("roomPrice",0),
                        Integer.parseInt(roomCount.getText().toString()),
                        Integer.parseInt(noOfGuests.getText().toString()),
                        checkIn.getText().toString(),
                        checkOut.getText().toString(),
                        Long.parseLong(totalPriceTextView.getText().toString()));
                bookingRef.child(bookingId).setValue(roomBooking);
                Toast.makeText(BookingDetailsActivity.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
                onBackPressed();

            }

            });
    }

    private void selectDate(TextView date) {
        final Calendar Cal = Calendar.getInstance();
        mDate = Cal.get(Calendar.DATE);
        mMonth = Cal.get(Calendar.MONTH);
        mYear = Cal.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingDetailsActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                date.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                calculateTotalPrice();
            }
        }, mYear, mMonth, mDate);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private int getNoOfDays(String checkInDate, String checkOutDate) {
        if(checkInDate.isEmpty() || checkOutDate.isEmpty()){
            return 0;
        }
        String[] checkInDateArray = checkInDate.split("-");
        String[] checkOutDateArray = checkOutDate.split("-");
        int checkInDay = Integer.parseInt(checkInDateArray[0]);
        int checkInMonth = Integer.parseInt(checkInDateArray[1]);
        int checkInYear = Integer.parseInt(checkInDateArray[2]);
        int checkOutDay = Integer.parseInt(checkOutDateArray[0]);
        int checkOutMonth = Integer.parseInt(checkOutDateArray[1]);
        int checkOutYear = Integer.parseInt(checkOutDateArray[2]);
        Calendar checkInCal = Calendar.getInstance();
        checkInCal.set(checkInYear, checkInMonth, checkInDay);
        Calendar checkOutCal = Calendar.getInstance();
        checkOutCal.set(checkOutYear, checkOutMonth, checkOutDay);
        long diff = checkOutCal.getTimeInMillis() - checkInCal.getTimeInMillis();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    private void calculateTotalPrice() {
        int noOfDays = getNoOfDays(checkIn.getText().toString(), checkOut.getText().toString());
        if(noOfDays < 0){
            Toast.makeText(this, "Please choose Valid Dates", Toast.LENGTH_SHORT).show();
            return;
        }
        int noOfRooms = Integer.parseInt(roomCount.getText().toString());
        long roomPrice = getIntent().getLongExtra("roomPrice", 0);
        long totalPrice = noOfDays * noOfRooms * roomPrice;
        String totalPriceText = Long.toString(totalPrice);
        totalPriceTextView.setText(totalPriceText);
    }
}