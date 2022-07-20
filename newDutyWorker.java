package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class newDutyWorker extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseUser user;
    String userID;
    DatabaseReference database, databaseuser;
    MyDutyAdapter myDutyAdapter;
    ArrayList<dutyScheduleModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_duty_worker);

        recyclerView = findViewById(R.id.recyclerview_newduty);
        database = FirebaseDatabase.getInstance().getReference("DutySchedule");
        databaseuser = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myDutyAdapter = new MyDutyAdapter(this,list);
        recyclerView.setAdapter(myDutyAdapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        databaseuser.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserWorker nameworker = snapshot.getValue(UserWorker.class);

                    String currentuserfullname = nameworker.fullname;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                     dutyScheduleModel model = dataSnapshot.getValue(dutyScheduleModel.class);
                     list.add(model);
                }

                myDutyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}