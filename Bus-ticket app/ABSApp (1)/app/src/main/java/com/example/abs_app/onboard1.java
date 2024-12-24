package com.example.abs_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class onboard1 extends AppCompatActivity {

    TextView skipo, nexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard1);


        nexto = findViewById(R.id.nexto);
        skipo = findViewById(R.id.skipo);

        nexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onboard1.this,onboard2.class);
                startActivity(intent);
            }
        });
        skipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onboard1.this,onboard3.class);
                startActivity(intent);
            }
        });
    }
}