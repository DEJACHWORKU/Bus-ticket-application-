package com.example.abs_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class onboard2 extends AppCompatActivity {

    TextView nextt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard2);

        nextt = findViewById(R.id.nextt);

        nextt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onboard2.this,onboard3.class);
                startActivity(intent);
            }
        });
    }
}