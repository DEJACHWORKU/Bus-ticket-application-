package com.example.abs_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class onboard3 extends AppCompatActivity {

    TextView textregg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard3);

        textregg = findViewById(R.id.textregg);


        textregg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onboard3.this,welcome.class);
                startActivity(intent);
            }
        });
    }
}