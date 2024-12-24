package com.example.abs_app.trafic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abs_app.DetailActivity;
import com.example.abs_app.MainActivity;
import com.example.abs_app.R;
import com.example.abs_app.UpdateActivity;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detailadmin extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailLang,pri,pri2,pri3,pri4,s1,s2,s3,s4;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailadmin);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        detailLang = findViewById(R.id.detailLang);

        pri = findViewById(R.id.price1);
        pri2 = findViewById(R.id.price2);
        pri3= findViewById(R.id.price3);
        pri4 = findViewById(R.id.price4);

        s1 = findViewById(R.id.seats1);
        s2 = findViewById(R.id.seats2);
        s3 = findViewById(R.id.seats3);
        s4 = findViewById(R.id.seats4);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText("Description: "+bundle.getString("Description"));
            detailTitle.setText("place: "+bundle.getString("Title"));
            detailLang.setText("kilometer: "+bundle.getString("Language"));
            key = bundle.getString("Key");
            //  imageUrl = bundle.getString("Image");
            //Glide.with(this).load(bundle.getString("Image")).into(detailImage);
            // detailImage.setImageBitmap(getUsermage(bundle.getString("Image")));
            byte[] bytes= Base64.decode(bundle.getString("Image"),Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            detailImage.setImageBitmap(bitmap);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("placese").child(key).child("car");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    pri.setText(snapshot.child("dataDesc").getValue(String.class));
                    pri2.setText(snapshot.child("dataImage").getValue(String.class));
                    pri3.setText(snapshot.child("dataLang").getValue(String.class));
                    pri4.setText(snapshot.child("dataTitle").getValue(String.class));
                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("placese").child(key).child("seats");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            s1.setText(snapshot.child("dataDesc").getValue(String.class));
                            s2.setText(snapshot.child("dataImage").getValue(String.class));
                            s3.setText(snapshot.child("dataLang").getValue(String.class));
                            s4.setText(snapshot.child("dataTitle").getValue(String.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("placese");
                reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(detailadmin.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(detailadmin.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailadmin.this, UpdateActivity.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Language", detailLang.getText().toString())
                        .putExtra("Image", bundle.getString("Image"))


                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }

}