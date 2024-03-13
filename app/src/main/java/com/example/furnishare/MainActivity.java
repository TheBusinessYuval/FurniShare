package com.example.furnishare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView UserName;
    Button find,upload,myfrurn,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        UserName = findViewById(R.id.UserTextView);

        UserName.setText("Welcome " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        find = findViewById(R.id.find);

        upload = findViewById(R.id.upload);

        myfrurn = findViewById(R.id.myfrurn);

        logout = findViewById(R.id.logout);

        find.setOnClickListener(view -> {
                Intent intent = new Intent(this,FindActivity.class);
                startActivity(intent);
        });
        upload.setOnClickListener(view -> {
            Intent intent = new Intent(this,UploadActivity.class);
            startActivity(intent);
        });
        myfrurn.setOnClickListener(view -> {

        });
        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            finish();
        });






    }
}