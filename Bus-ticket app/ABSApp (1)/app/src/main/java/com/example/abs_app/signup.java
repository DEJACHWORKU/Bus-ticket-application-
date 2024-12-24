package com.example.abs_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    TextView textrege;
    EditText name,password,email,phone,cPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textrege = findViewById(R.id.textrege);

        name=findViewById(R.id.names);
        password = findViewById(R.id.password);
        cPassword=findViewById(R.id.confrimpassword);
        email=findViewById(R.id.emails);
        phone=findViewById(R.id.phones);

        textrege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=password.getText().toString();
                String emails=email.getText().toString();
                String cpass=cPassword.getText().toString();
                String names=name.getText().toString();
                String phones=phone.getText().toString();
                String role = "user";
                if (cpass.equals(pass)){
                    useredataclas useredataclas = new useredataclas(names,role,pass,emails,phones);
                    FirebaseDatabase.getInstance().getReference().child("user").child(emails)
                            .setValue(useredataclas).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(signup.this, "mnmmm", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signup.this, welcome.class));

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signup.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }else {
                    Toast.makeText(signup.this, "the password must be the same!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }
    public void create(){

    }
}