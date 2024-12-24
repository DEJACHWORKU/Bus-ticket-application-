package com.example.abs_app.trafic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.abs_app.MainActivity;
import com.example.abs_app.R;
import com.example.abs_app.UpdateActivity;
import com.example.abs_app.signup;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;import android.os.Bundle;
import android.widget.Toast;

public class detail_user extends AppCompatActivity {

    EditText name, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        Button button = findViewById(R.id.update1);
        name = findViewById(R.id.name1);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone1);
        FloatingActionButton deletbutton = findViewById(R.id.deleteButton);

        FloatingActionButton add = findViewById(R.id.addbutton);
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(detail_user.this, signup.class));
    }
});

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name.setText(bundle.getString("name"));
            email.setText(bundle.getString("email"));
            phone.setText(bundle.getString("phone"));
            password.setText(bundle.getString("password"));

            String key = bundle.getString("Key");
            //  imageUrl = bundle.getString("Image");
            //Glide.with(this).load(bundle.getString("Image")).into(detailImage);
            // detailImage.setImageBitmap(getUsermage(bundle.getString("Image")));
            deletbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user");
                    reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(detail_user.this, key, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(detail_user.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });


        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n,e,p,pas;
                n=name.getText().toString();
                e=email.getText().toString();
                p=phone.getText().toString();
                pas=password.getText().toString();
                DataClass dataClass = new DataClass(n,e,p,pas);

                FirebaseDatabase.getInstance().getReference().child("user")
                        .child(email.getText().toString()).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(detail_user.this, "updated", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(detail_user.this, "failed: " + e, Toast.LENGTH_SHORT).show();
                            }
                        });


                Toast.makeText(detail_user.this, n+e+p+pas, Toast.LENGTH_SHORT).show();

            }
        });



    }
}