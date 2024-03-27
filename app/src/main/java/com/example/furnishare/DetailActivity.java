package com.example.furnishare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailLang;
    ImageView detailImage;
    Button deleteButton, editButton;
    String User = "";
    String key= "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        deleteButton = findViewById(R.id.deleteButton);
        detailLang = findViewById(R.id.detailUser);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailLang.setText(bundle.getString("State"));
            User = bundle.getString("User");

            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
            key = bundle.getString("Key");
        }
        deleteButton.setOnClickListener(view -> {
            final DatabaseReference reference = FirebaseDatabase.getInstance("https://furnishare-551b7-default-rtdb.europe-west1.firebasedatabase.app").getReference("Android Tutorials");
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
           // storageReference.delete().addOnSuccessListener(unused -> {
                reference.child(key).removeValue();
                    Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

            });
        }//);
    }
//}