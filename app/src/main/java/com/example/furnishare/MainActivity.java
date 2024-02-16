package com.example.furnishare;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private ImageView testPic;
    public Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testPic = findViewById(R.id.testPic);

        testPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePic();
            }
        });
    }

    private void changePic() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction((Intent.ACTION_GET_CONTENT));
        ActivityResultLauncher.launch(intent);
    }


    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        imageUri = data.getData();
                        testPic.setImageURI(imageUri);
                        uploadPicture();
                    }
                }
            });


    private void uploadPicture() {
        Uri file = imageUri;
        final String randomkey = UUID.randomUUID().toString();
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        List<byte[]> list = Arrays.asList(baos.toByteArray());
        TextView tester = findViewById(R.id.tester);
        tester.setText("staring");
        FirebaseDatabase.getInstance("https://furnishare-551b7-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("test").push().setValue(list.toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                TextView tester = findViewById(R.id.tester);
                tester.setText("worked");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                TextView tester = findViewById(R.id.tester);
                tester.setText("didn't worked");
            }
        })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        TextView tester = findViewById(R.id.tester);
                        tester.setText("canceled");
                    }
                });

        }
    }

