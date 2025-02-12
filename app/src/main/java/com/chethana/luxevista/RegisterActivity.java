package com.chethana.luxevista;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.HashMap;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registerName, registerEmail, registerMobile, registerPassword;

    private Button registerButton;
    private TextView loginRedirectText;
    FirebaseFirestore fireStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       fireStore = FirebaseFirestore.getInstance();

registerName = findViewById(R.id.register_name);
registerEmail = findViewById(R.id.register_email);
registerMobile = findViewById(R.id.register_mobile);
registerPassword = findViewById(R.id.register_password);
registerButton = findViewById(R.id.register_button);
loginRedirectText = findViewById(R.id.login_redirect_text_link);

        registerButton.setOnClickListener (new View.OnClickListener(){

            @Override
            public void onClick (View view){

                String email = registerEmail.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                String name = registerName.getText().toString().trim();
                String mobile = registerMobile.getText().toString().trim();


                if (email.isEmpty()){
                    registerEmail.setError("Email cannot be empty");
                }


                if (password.isEmpty()){
                    registerPassword.setError("Password cannot be empty");
                } else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            userID = auth.getCurrentUser().getUid();
                            DocumentReference documentReference = fireStore.collection("users").document(userID);
                           Map<String,Object> user = new HashMap<>();
                           user.put("name", name);
                           user.put("email", email);
                           user.put("mobile", mobile);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created for " + userID);
                                }
                            });
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                }

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}