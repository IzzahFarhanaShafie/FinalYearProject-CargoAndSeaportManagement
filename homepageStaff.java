package com.example.cargotracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class homepageStaff extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FirebaseRecyclerOptions<announcement> options;
    FirebaseRecyclerAdapter<announcement, MyViewHolder> adapter;
    DatabaseReference Dataref;
    RecyclerView recyclerView;

//    private FirebaseUser user;
//    private DatabaseReference reference;
//    private String userID;

    private ImageView IVUpdateVesselInfo, IVStaffDutySchedule, IVAssignStaffShift, IVStaffList, IVVesselSchedule;


    private FloatingActionButton messaging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_staff);

        Dataref = FirebaseDatabase.getInstance().getReference().child("Announcements");
        recyclerView = findViewById(R.id.announcement_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);


        LoadData();

        messaging = findViewById(R.id.message_btn);

        messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepageStaff.this,MainmessagingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        IVStaffList = (ImageView) findViewById(R.id.imageStaffList);

        IVStaffList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepageStaff.this, staffList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        IVUpdateVesselInfo = (ImageView) findViewById(R.id.imageUpdateVesselInfo);

        IVUpdateVesselInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepageStaff.this, insertShipTime.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        IVAssignStaffShift = (ImageView) findViewById(R.id.imageAssignStaffShift);

        IVAssignStaffShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepageStaff.this, insertStaffShift.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        IVStaffDutySchedule = (ImageView) findViewById(R.id.imageStaffDutySchedule);

        IVStaffDutySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepageStaff.this, staffDutySchedule.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        IVVesselSchedule = (ImageView) findViewById(R.id.imageVesselSchedule);

        IVVesselSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepageStaff.this, shipTimeSchedule.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });


    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<announcement>().setQuery(Dataref, announcement.class).build();
        adapter = new FirebaseRecyclerAdapter<announcement, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull announcement model) {
                holder.text_announcement.setText(model.getImagename());
                Picasso.get().load(model.getImageUrl()).into(holder.image_announcement);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(homepageStaff.this, announcementDetails.class);
                        intent.putExtra("AnnouncementKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_announcement,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;

            case R.id.nav_profile:
                Intent intent = (new Intent(homepageStaff.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                startActivity(intent);
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(homepageStaff.this, login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



//    private void Status(final String status){
//        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
//
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("status", status);
//        reference.updateChildren(hashMap);
//    }
//
//    protected void onResume() {
//
//        super.onResume();
//        Status("online");
//    }

//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Status("offline");
//    }

}