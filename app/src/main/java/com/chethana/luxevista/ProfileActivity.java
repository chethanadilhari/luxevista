package com.chethana.luxevista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ProfileActivity extends AppCompatActivity {

    Button btnSave, btnCancel;
    EditText etName, etEmail, etPhone, etAddress;

    ImageView profileImage;

    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etPhone = findViewById(R.id.mobile);
        etAddress = findViewById(R.id.address);

        btnSave = findViewById(R.id.saveButton);
        btnCancel = findViewById(R.id.cancelButton);

        profileImage = findViewById(R.id.profileImage);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("users");

        String email = auth.getCurrentUser().getEmail();
        String emailHash = hashEmail(email);
        String imageUrl = "https://www.gravatar.com/avatar/" + emailHash + "?s=256";
        etEmail.setText(email);

        Glide.with(this)
                .load(imageUrl)
                .circleCrop()
                .into(profileImage);

        reference.child(auth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    etName.setText(task.getResult().child("name").getValue().toString());
                    etPhone.setText(task.getResult().child("mobile").getValue().toString());
                    etAddress.setText(task.getResult().child("address").getValue().toString());
                }
            }
        });

        btnCancel.setOnClickListener(v -> {
            onBackPressed();
        });

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            String address = etAddress.getText().toString();

            UserProfileClass userProfile = new UserProfileClass(name, phone, address);

            reference.child(auth.getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            });
        });



    }

    public static String hashEmail(String email) {
        try {
            // Convert email to lowercase
            String lowerCaseEmail = email.toLowerCase();

            // Get SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Compute the hash
            byte[] hashBytes = digest.digest(lowerCaseEmail.getBytes());

            // Convert hash bytes to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}