package com.example.furnishare;

import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;

    ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerView);
        toggleButton = findViewById(R.id.ToggleButton);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(FindActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        dataList = new ArrayList<>();
        adapter = new MyAdapter(FindActivity.this, dataList);
        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance("https://furnishare-551b7-default-rtdb.europe-west1.firebasedatabase.app").getReference("Android Tutorials");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){

                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        toggleButton.setOnClickListener(view -> {
            if (toggleButton.isChecked()) {
                searchList();
            }
            else {
                eventListener = databaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dataList.clear();
                        for (DataSnapshot itemSnapshot: snapshot.getChildren()){

                            DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                            dataClass.setKey(itemSnapshot.getKey());
                            dataList.add(dataClass);
                        }
                        adapter.searchDataList((ArrayList<DataClass>) dataList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
    public void searchList(){
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass: dataList){

            if (dataClass.getUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){

                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}
