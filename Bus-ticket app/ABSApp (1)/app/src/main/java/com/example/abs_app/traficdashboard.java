package com.example.abs_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.abs_app.trafic.notification;
import com.example.abs_app.trafic.order_activity;
import com.example.abs_app.trafic.placesManagment;
import com.example.abs_app.trafic.userManagment;

public class traficdashboard extends AppCompatActivity {

    CardView logoutcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traficdashboard);
        logoutcard = findViewById(R.id.logoutcard);

        logoutcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(traficdashboard.this, welcome.class);
                startActivity(intent);
                preferences.clearData(traficdashboard.this);
                finish();
                Toast.makeText(traficdashboard.this, "Logout Admin", Toast.LENGTH_LONG).show();;
            }
        });

        findViewById(R.id.Card3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(traficdashboard.this, userManagment.class));
            }
        });
        findViewById(R.id.Card4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(traficdashboard.this, placesManagment.class));

            }
        });
        findViewById(R.id.Card5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(traficdashboard.this, order_activity.class));
            }
        });
        findViewById(R.id.Card6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(traficdashboard.this, notification.class));

            }
        });


    }
}