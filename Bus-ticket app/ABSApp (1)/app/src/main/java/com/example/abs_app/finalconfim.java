package com.example.abs_app;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.abs_app.trafic.DataClass1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;


import java.text.DateFormat;
import java.util.Calendar;


public class finalconfim extends AppCompatActivity {
    TextView t1,t2,t3,t5,t4,t7,t6;

    Preference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalconfim);
        String s1=getIntent().getExtras().getString("place");
        String s2=getIntent().getExtras().getString("price1");
        String s3=getIntent().getExtras().getString("car1");
        String s4=getIntent().getExtras().getString("seats");
        String s5=getIntent().getExtras().getString("km");

        t1=findViewById(R.id.carcity);
        t2=findViewById(R.id.carprice);
        t3=findViewById(R.id.cartype);
        t4=findViewById(R.id.carkm);
        t5=findViewById(R.id.passengername);
        t6=findViewById(R.id.passphone);
       // t7=findViewById(R.id.carcity);

       // preferences.getName(finalconfim,naame)
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        t4.setText(s5);
        t5.setText(s4);
        t6.setText(s5);


        //date
        DatePicker simpledatepicker=(DatePicker) findViewById(R.id.simpledatepicker);

        int day=simpledatepicker.getDayOfMonth();
        int month=simpledatepicker.getMonth()+1;
        int year=simpledatepicker.getYear();






            findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   b();


                if ((simpledatepicker.getMonth()+1)<10 && simpledatepicker.getDayOfMonth()<10) {
                    String date = simpledatepicker.getYear()
                            + "-0" + (simpledatepicker.getMonth() + 1)
                            + "-0" + simpledatepicker.getDayOfMonth();
                    Toast.makeText(finalconfim.this, date, Toast.LENGTH_SHORT).show();
                    DataClass1 dataClass = new DataClass1(s1, s2, t5.getText().toString(), t6.getText().toString(),s3,s5,date);

                    String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    FirebaseDatabase.getInstance().getReference().child("order").child(currentDate)
                            .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(finalconfim.this, "sucessfully paid", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(finalconfim.this, payment.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                } else {
                    String date = simpledatepicker.getYear()
                            + "-0" + (simpledatepicker.getMonth() + 1)
                            + "-" + simpledatepicker.getDayOfMonth();
                    Toast.makeText(finalconfim.this, date, Toast.LENGTH_SHORT).show();
                    DataClass1 dataClass = new DataClass1(s1, s2, t5.getText().toString(), t6.getText().toString(),s3,s5,date);

                    String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    FirebaseDatabase.getInstance().getReference().child("order").child(currentDate)
                            .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(finalconfim.this, "sucessfully paid", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(finalconfim.this, payment.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                }

                }
        });


    }



    public void b()   {


    }

}