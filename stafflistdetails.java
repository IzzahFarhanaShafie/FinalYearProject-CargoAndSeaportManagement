package com.example.cargotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class stafflistdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stafflistdetails);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("emailprofile") && getIntent().hasExtra("fullname") && getIntent().hasExtra("icnumber") && getIntent().hasExtra("image") && getIntent().hasExtra("phonenumber") && getIntent().hasExtra("port") && getIntent().hasExtra("position") && getIntent().hasExtra("staffid") && getIntent().hasExtra("wharf") ){

            String emailprofile = getIntent().getStringExtra("emailprofile");
            String fullname = getIntent().getStringExtra("fullname");
            String icnumber = getIntent().getStringExtra("icnumber");
            String image = getIntent().getStringExtra("image");
            String phonenumber = getIntent().getStringExtra("phonenumber");
            String port = getIntent().getStringExtra("port");
            String position = getIntent().getStringExtra("position");
            String staffid = getIntent().getStringExtra("staffid");
            String wharf = getIntent().getStringExtra("wharf");

            setData(emailprofile,fullname,icnumber,image,phonenumber,port,position,staffid,wharf);

        }
    }

    private void setData(String emailprofile, String fullname, String icnumber, String image, String phonenumber, String port,  String position, String staffid, String wharf){

        TextView fullnameTV = findViewById(R.id.fullnameTV);
        fullnameTV.setText(fullname);

        EditText staffidET = findViewById(R.id.staffidET);
        staffidET.setText(staffid);
        staffidET.setEnabled(false);

        EditText icnumberET = findViewById(R.id.icnumberET);
        icnumberET.setText(icnumber);
        icnumberET.setEnabled(false);

        EditText emailET = findViewById(R.id.emailET);
        emailET.setText(emailprofile);
        emailET.setEnabled(false);

        EditText phonenumberET = findViewById(R.id.phonenumberET);
        phonenumberET.setText(phonenumber);
        phonenumberET.setEnabled(false);

        EditText portET = findViewById(R.id.portET);
        portET.setText(port);
        portET.setEnabled(false);

        EditText positionET = findViewById(R.id.positionET);
        positionET.setText(position);
        positionET.setEnabled(false);

        EditText wharfET = findViewById(R.id.wharfET);
        wharfET.setText(wharf);
        wharfET.setEnabled(false);

        CircleImageView circleImageView = findViewById(R.id.image_stafflistdetails);
        Glide.with(getApplicationContext()).load(image).into(circleImageView);






    }
}