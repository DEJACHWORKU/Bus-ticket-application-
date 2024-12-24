package com.example.abs_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcome extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView textregd;
    EditText userid;
    EditText password;
    Button loginButton;
    Switch active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textregd = findViewById(R.id.textregd);
        userid = findViewById(R.id.userid);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginbutton);
        active = findViewById(R.id.active);

        textregd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcome.this, signup.class);
                startActivity(intent);
            }
        });

       loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              login();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(this, traficdashboard.class));
                finish();
            } else if (preferences.getDataAs(this).equals("student")) {
                startActivity(new Intent(this, userdashborad.class));
                finish();
            }
        }
    }

    public void login(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String input1 = userid.getText().toString();
                String input2 = password.getText().toString();

                if (dataSnapshot.child(input1).exists()) {
                    if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                        // Remove the unnecessary Object preferences;
                        if (active.isChecked()) {  // Assuming 'active' is a Switch
                            if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("admin")) {
                                preferences.setDataLogin(welcome.this, true);
                                preferences.setDataAs(welcome.this, "admin");
                                startActivity(new Intent(welcome.this, traficdashboard.class));
                                finish();
                            } else if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("user")) {
                                preferences.setDataLogin(welcome.this, true);
                                String name = String.valueOf(dataSnapshot.child(input1).child("role").getValue(String.class).equals(""));
                                preferences.setName(welcome.this,name);
                                preferences.setDataAs(welcome.this, "users");
                                Intent intent = new Intent(welcome.this, userdashborad.class);
                                intent.putExtra("email",input1);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("admin")) {
                                preferences.setDataLogin(welcome.this, false);
                                startActivity(new Intent(welcome.this, traficdashboard.class));
                                finish();
                            } else if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("user")) {
                                preferences.setDataLogin(welcome.this, false);
                                String name = String.valueOf(dataSnapshot.child(input1).child("role").getValue(String.class).equals(""));
                                preferences.setName(welcome.this,name);
                                Intent intent = new Intent(welcome.this, userdashborad.class);
                                intent.putExtra("email",input1);
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(welcome.this, "No Role Please Contact us", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(welcome.this, "Username and Password Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error if needed
            }
        });
    }
}
