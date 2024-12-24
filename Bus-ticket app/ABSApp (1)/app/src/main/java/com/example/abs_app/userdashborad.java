package com.example.abs_app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abs_app.trafic.notification_recyclre;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userdashborad extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private NestedScrollView draggableLayout;
    public Polyline polyline;
    private LatLng destinationLatLng;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LatLng myLocation;

    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashborad);




        recyclerView = findViewById(R.id.recyclerView);
//        fab = findViewById(R.id.fab);
       //searchView = findViewById(R.id.search);
       // searchView.clearFocus();
     //   GridLayoutManager gridLayoutManager = new GridLayoutManager(userdashborad.this, 1);
         recyclerView.setLayoutManager(new LinearLayoutManager(userdashborad.this));
        AlertDialog.Builder builder = new AlertDialog.Builder(userdashborad.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
       dataList = new ArrayList<>();
        adapter = new MyAdapter(userdashborad.this, dataList);
        recyclerView.setAdapter(adapter);
        String email = getIntent().getExtras().getString("email");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("placese");
        FirebaseDatabase.getInstance().getReference().child("user").child(email)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String name = snapshot.child("full_name").getValue(String.class);
                                TextView textView= findViewById(R.id.directionButton);
                                textView.setText("helloo, "+ name);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                     DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);

                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });




         /* findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(userdashborad.this, "mnmbmbmbmnm", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(userdashborad.this, Activity2_upload.class);
                startActivity(intent);
            }
        });
*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        findViewById(R.id.directionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userdashborad.this, "mnmnnbhghhjjhjhj", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(userdashborad.this, "nbhghhjjhjhj", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(userdashborad.this, notification_recyclre.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
                params.height=0;
                mapFragment.getView().setLayoutParams(params);
                findViewById(R.id.layout1).getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                findViewById(R.id.layout1).requestLayout();
                findViewById(R.id.search).setVisibility(View.VISIBLE);


            }
        });
        findViewById(R.id.currentLocationEditText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
                params.height=0;
                mapFragment.getView().setLayoutParams(params);
                findViewById(R.id.layout1).getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                findViewById(R.id.layout1).requestLayout();
                findViewById(R.id.search).setVisibility(View.VISIBLE);
            }
        });


        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check location permission


        // Get last known location
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    // Now you can use myLocation to get the current location
                    // For example: startNavigation(destinationLatLng);
                } else {
                    Toast.makeText(userdashborad.this, "Unable to retrieve location", Toast.LENGTH_SHORT).show();
                }
            }
        });







      /*  draggableLayout = findViewById(R.id.draggableLayout);

        Button directionButton = findViewById(R.id.directionButton);
        Button startButton = findViewById(R.id.startButton);
        final EditText currentLocationEditText = findViewById(R.id.currentLocationEditText);
        final EditText destinationEditText = findViewById(R.id.destinationEditText);

        // Set onClickListener for direction button
        directionButton.setOnClickListener(v -> {
            // Implement your direction logic here
        });

        // Set onClickListener for start button
        startButton.setOnClickListener(v -> {
            // Implement your start navigation logic here
        });*/

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchFirebaseDatabase(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });


    }
    public void searchList(String text) {
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass : dataList) {
            if (dataClass.getDataDesc().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }

    private void searchFirebaseDatabase(String query){
        databaseReference.orderByChild("dataTitle").equalTo(query)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            String result = snapshot.getValue(String.class);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        TextView button = findViewById(R.id.directionButton);
        // Add a marker and move the camera
        LatLng sydney = new LatLng(7.959795, 39.131948);
        map.addMarker(new MarkerOptions().position(sydney).title("ASella bus terminal"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.addMarker(new MarkerOptions().position(sydney).title("starting point"));
            }
        });
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                destinationLatLng = latLng;
                map.clear();
                map.addMarker(new MarkerOptions().position(destinationLatLng).title("starting point"));
            }
        });


    }
    private void startNavigation(LatLng destinationLatLng) {
        if (destinationLatLng != null) {
            // Request directions from current location to destination
            String url = "https://maps.googleapis.com/maps/api/directions/json?" +
                    "origin=" + 39 + "," + 38 +
                    "&destination=" + destinationLatLng.latitude + "," + destinationLatLng.longitude +
                    "&key=" + "AIzaSyDV2_xy58r15K6TskZy4KWMuhUDVq67jqM";

            // Make the Directions API request (You can use Volley, Retrofit, or any other HTTP client)
            // Parse the JSON response and draw polyline
            // ...

            // Once you have the route information, display it to the user
            // For example, you can display the route on the map and show step-by-step instructions
        } else {
            Toast.makeText(this, "Please select a destination", Toast.LENGTH_SHORT).show();
        }
    }}
