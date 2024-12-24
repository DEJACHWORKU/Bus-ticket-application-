package com.example.abs_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateActivity extends AppCompatActivity {
    ImageView updateImage;
    Button updateButton;
    EditText updateDesc, updateTitle, updateLang,p1,p2,p3,p4,s1,s2,s3,s4;
    String title, desc, lang,img;
    String imageUrl;
    String key, oldImageURL;
    Uri uri;
    String encodedImage;
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        updateButton = findViewById(R.id.updateButton);
        updateDesc = findViewById(R.id.updateDesc);
        updateImage = findViewById(R.id.updateImage);
        updateLang = findViewById(R.id.updateLang);
        updateTitle = findViewById(R.id.updateTitle);

        p1=findViewById(R.id.uprice1);
        p2=findViewById(R.id.uprice2);
        p3=findViewById(R.id.uprice3);
        p4=findViewById(R.id.uprice4);

        s1=findViewById(R.id.uremCar1);
        s2=findViewById(R.id.uremCar2);
        s3=findViewById(R.id.uremCar3);
        s4=findViewById(R.id.uremCar4);









        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //Glide.with(UpdateActivity.this).load(bundle.getString("Image")).into(updateImage);
            updateTitle.setText(bundle.getString("Title"));
            updateDesc.setText(bundle.getString("Description"));
            updateLang.setText(bundle.getString("Language"));





            key = bundle.getString("Key");
            oldImageURL = bundle.getString("Image");
            byte[] bytes= Base64.decode(bundle.getString("Image"),Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            updateImage.setImageBitmap(bitmap);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("placese").child(key).child("car");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    p1.setText(snapshot.child("dataDesc").getValue(String.class));
                    p2.setText(snapshot.child("dataImage").getValue(String.class));
                    p3.setText(snapshot.child("dataLang").getValue(String.class));
                    p4.setText(snapshot.child("dataTitle").getValue(String.class));
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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("placese").child(key);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("placese").child(key).child("car");
        databaseReference2= FirebaseDatabase.getInstance().getReference().child("placese").child(key).child("seats");


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
         AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

            title = updateTitle.getText().toString().trim();
            desc = updateDesc.getText().toString().trim();
            lang = updateLang.getText().toString();

            imageUrl = encodedImage;
        if (encodedImage == null){
             DataClass dataClass = new DataClass(title, desc, lang, oldImageURL);
            DataClass dataClass1 = new DataClass(p1.getText().toString(), p2.getText().toString(), p3.getText().toString(), p4.getText().toString());
            DataClass dataClass2 = new DataClass(s1.getText().toString(), s2.getText().toString(), s3.getText().toString(), s4.getText().toString());


            databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){

                        dialog.dismiss();
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            databaseReference1.setValue(dataClass1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        dialog.dismiss();
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            databaseReference2.setValue(dataClass2).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        dialog.dismiss();
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });



        }else {
            DataClass dataClass1 = new DataClass(p1.getText().toString(), p2.getText().toString(), p3.getText().toString(), p4.getText().toString());
            DataClass dataClass2 = new DataClass(s1.getText().toString(), s2.getText().toString(), s3.getText().toString(), s4.getText().toString());
            DataClass dataClass = new DataClass(title, desc, lang, imageUrl);

            databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        dialog.dismiss();
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            databaseReference1.setValue(dataClass1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        dialog.dismiss();
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            databaseReference2.setValue(dataClass2).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        dialog.dismiss();
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
    private String encodedImage(Bitmap bitmap){
        int previewWidth= 150;
        int previewHight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap,previewWidth,previewHight,false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] bytes =byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);

    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            if (result.getData() != null){
                Uri imageUri = result.getData().getData();
                try {
                    InputStream inputStream= getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    updateImage.setImageBitmap(bitmap);

                    encodedImage=encodedImage(bitmap);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    });

    public void updateData(){
        title = updateTitle.getText().toString().trim();
        desc = updateDesc.getText().toString().trim();
        lang = updateLang.getText().toString();


        DataClass dataClass = new DataClass(title, desc, lang, imageUrl);
        DataClass dataClass1 = new DataClass(title, desc, lang, imageUrl);
        DataClass dataClass2 = new DataClass(title, desc, lang, imageUrl);


        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }



        });
        databaseReference1.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }



        });
        databaseReference2.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }



        });



    }
}