package com.example.abs_app.trafic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abs_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button btnninsert = findViewById(R.id.btnninsert);
        EditText nedit = findViewById(R.id.neditTitle);
        EditText ndesc = findViewById(R.id.neditdescription);
        btnninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("notification");

                addnot not=new addnot(nedit.getText().toString(),ndesc.getText().toString());

                databaseReference.child(databaseReference.push().getKey()).setValue(not)
                        .addOnSuccessListener(suc->{
                            Toast.makeText(notification.this,"inserted",Toast.LENGTH_LONG).show();
                        }).addOnFailureListener(er->{
                            Toast.makeText(notification.this,"not inserted"+er.getMessage(),Toast.LENGTH_LONG).show();
                        });
            }
        });
    }
}


