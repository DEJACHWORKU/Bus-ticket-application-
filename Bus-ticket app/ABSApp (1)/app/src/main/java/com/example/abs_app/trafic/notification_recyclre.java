package com.example.abs_app.trafic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.abs_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class notification_recyclre extends AppCompatActivity {

    private final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    private final List<Notmoadl> notmoadlsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_recyclre);
        final RecyclerView recyclerView=findViewById(R.id.nrecyclearview_id);

        recyclerView.setLayoutManager(new LinearLayoutManager(notification_recyclre.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                notmoadlsList.clear();

                for (DataSnapshot usres : snapshot.child("notification").getChildren()){

                    if (usres.hasChild("title") && usres.hasChild("description")){
                        final String getTitle=usres.child("title").getValue(String.class);
                        final String getDescription=usres.child("description").getValue(String.class);

                        Notmoadl notmoadl=new Notmoadl(getTitle,getDescription);

                        notmoadlsList.add(notmoadl);
                    }

                }

                recyclerView.setAdapter(new notAdapter(notmoadlsList,notification_recyclre.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}