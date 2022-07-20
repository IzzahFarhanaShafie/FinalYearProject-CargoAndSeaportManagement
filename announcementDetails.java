package com.example.cargotracker;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class announcementDetails extends AppCompatActivity {

    ImageView announcementimage;
    TextView announcementtitle;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_details);

        announcementimage = findViewById(R.id.image_announcementdetails);
        announcementtitle = findViewById(R.id.announcementtitle_tv);
        ref = FirebaseDatabase.getInstance().getReference().child("Announcements");

        String AnnouncementKey = getIntent().getStringExtra("AnnouncementKey");

        ref.child(AnnouncementKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String imagename = dataSnapshot.child("imagename").getValue().toString();
                    String imageUrl = dataSnapshot.child("imageUrl").getValue().toString();

                    Picasso.get().load(imageUrl).into(announcementimage);
                    announcementtitle.setText(imagename);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}
