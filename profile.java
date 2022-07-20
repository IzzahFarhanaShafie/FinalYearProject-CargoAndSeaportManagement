package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cargotracker.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private FirebaseUser userimage;


    CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView fullnameTextView = (TextView) findViewById(R.id.fullname);
        final TextView icnumberTextView = (TextView) findViewById(R.id.icnumber);
        final TextView phonenumberTextView = (TextView) findViewById(R.id.phonenumber);
        final TextView emailprofileTextView = (TextView) findViewById(R.id.emailprofile);
        final TextView staffidTextView = (TextView) findViewById(R.id.staffid);
        final TextView portTextView = (TextView) findViewById(R.id.port);
        final TextView positionTextView = (TextView) findViewById(R.id.position);
        final TextView wharfTextView = (TextView) findViewById(R.id.wharf);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullname = userProfile.fullname;
                    String icnumber = userProfile.icnumber;
                    String phonenumber = userProfile.phonenumber;
                    String emailprofile = userProfile.emailprofile;
                    String staffid = userProfile.staffid;
                    String port = userProfile.port;
                    String position = userProfile.position;
                    String wharf = userProfile.wharf;

                    fullnameTextView.setText(fullname);
                    icnumberTextView.setText(icnumber);
                    phonenumberTextView.setText(phonenumber);
                    emailprofileTextView.setText(emailprofile);
                    staffidTextView.setText(staffid);
                    portTextView.setText(port);
                    positionTextView.setText(position);
                    wharfTextView.setText(wharf);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();

            }
        });

        imageView = findViewById(R.id.profilePic);
        userimage = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Users").child(userimage.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User users = snapshot.getValue(User.class);


                if (users.getImage().equals("")) {
                    imageView.setImageResource(R.drawable.ic_baseline_person_24);
                } else {
                    Glide.with(getApplicationContext()).load(users.getImage()).into(imageView);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}