package com.example.abs_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailLang,pri,pri2,pri3,pri4,s1,s2,s3,s4,t1,t2,t3,t4;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    CardView cardView1,cardView2,cardView3,cardView4;
    String key = "";
    String imageUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);

        pri = findViewById(R.id.price11);
        pri2 = findViewById(R.id.price22);
        pri3= findViewById(R.id.price33);
        pri4 = findViewById(R.id.price44);
        s1 = findViewById(R.id.seats1);
        s2 = findViewById(R.id.seats2);
        s3 = findViewById(R.id.seats3);
        s4 = findViewById(R.id.seats4);

        t1=findViewById(R.id.recTitle);
        t2=findViewById(R.id.recTitle2);
        t3=findViewById(R.id.recTitle3);
        t4=findViewById(R.id.recTitle4);


        detailLang = findViewById(R.id.detailLang);


        cardView1=findViewById(R.id.recCard);
        cardView2=findViewById(R.id.recCard2);
        cardView3=findViewById(R.id.recCard3);
        cardView4=findViewById(R.id.recCard4);


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
            cardView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(DetailActivity.this, finalconfim.class);
                    intent.putExtra("price1",pri.getText().toString());
                    intent.putExtra("car1",t1.getText().toString());
                    intent.putExtra("seats",s1.getText().toString());
                    intent.putExtra("km",detailLang.getText().toString());
                    intent.putExtra("place",detailTitle.getText().toString());

                    startActivity(intent);
                }
            });

            cardView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(DetailActivity.this, finalconfim.class);
                    intent.putExtra("price1",pri2.getText().toString());
                    intent.putExtra("car1",t2.getText().toString());
                    intent.putExtra("seats",s2.getText().toString());
                    intent.putExtra("km",detailLang.getText().toString());
                    intent.putExtra("place",detailTitle.getText().toString());
                    startActivity(intent);
                }
            });
            cardView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(DetailActivity.this, finalconfim.class);
                    intent.putExtra("price1",pri3.getText().toString());
                    intent.putExtra("car1",t3.getText().toString());
                    intent.putExtra("seats",s3.getText().toString());
                    intent.putExtra("km",detailLang.getText().toString());
                    intent.putExtra("place",detailTitle.getText().toString());
                    startActivity(intent);
                }
            });
            cardView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(DetailActivity.this, finalconfim.class);
                    intent.putExtra("price1",pri4.getText().toString());
                    intent.putExtra("car1",t4.getText().toString());
                    intent.putExtra("seats",s4.getText().toString());
                    intent.putExtra("km",detailLang.getText().toString());
                    intent.putExtra("place",detailTitle.getText().toString());
                    startActivity(intent);
                }
            });
        }



    }

}