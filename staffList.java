package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class staffList extends AppCompatActivity {

    RecyclerView recview;
    stafflistAdapter adapter;
    DatabaseReference database;
    ArrayList<stafflistModel> list;
    SearchView searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);

        recview = findViewById(R.id.recyclerview_stafflist);
        database = FirebaseDatabase.getInstance().getReference("Users");
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new stafflistAdapter(this,list);
        recview.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){

                    stafflistModel model = dataSnapshot.getValue(stafflistModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        searchET = findViewById(R.id.searchviewET);
//        searchET.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                ArrayList<stafflistModel> filteredlist = new ArrayList<stafflistModel>();
//                for(stafflistModel model: list){
//                    if((model.getFullname().toLowerCase().contains(s.toLowerCase())) || (model.getStaffid().toLowerCase().contains(s.toLowerCase())) || (model.getUsertype().toLowerCase().contains(s.toLowerCase()))){
//                        filteredlist.add(model);
//                    }
//                }
//
//                stafflistAdapter adapter =  new stafflistAdapter(staffList.this,filteredlist);
//                recview.setAdapter(adapter);
//
//                return false;
//            }
//        });

    }



}