package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class insertShipTime extends AppCompatActivity {

    EditText scnNumber;
    EditText vesselID;
    EditText vesselName;
    Spinner entryPointSpinner;
    EditText location;
    Spinner terminalSpinner;
    EditText agentName;
    EditText arrivalDateTime;
    Button btnSubmit;
    long maxshiptimeid = 0 ;
    String shipid = "ShipTimeID";
    String firebaseidshiptime;

    DatabaseReference shipDbRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_ship_time);

        scnNumber = findViewById(R.id.scnNumber);
        vesselID = findViewById(R.id.vesselID);
        vesselName = findViewById(R.id.vesselName);
        entryPointSpinner = findViewById(R.id.entryPointSpinner);
        location = findViewById(R.id.location);
        terminalSpinner = findViewById(R.id.terminalSpinner);
        agentName = findViewById(R.id.agentName);
        btnSubmit = findViewById(R.id.btnSubmit);

        shipDbRef = FirebaseDatabase.getInstance().getReference().child("ShipTime");
        shipDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxshiptimeid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set time and dialog picker
        arrivalDateTime = findViewById(R.id.arrivalDate);
        arrivalDateTime.setInputType(InputType.TYPE_NULL);

        arrivalDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDateTimeDialog(arrivalDateTime);
            }
        });

        //submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertShipData();
            }
        });


    }

    private void showDateTimeDialog(final EditText arrivalDateTime) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");

                        arrivalDateTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(insertShipTime.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(insertShipTime.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void InsertShipData() {
        String scnnumber = scnNumber.getText().toString().trim();
        String vesselid = vesselID.getText().toString().trim();
        String vesselname = vesselName.getText().toString().trim();
        String entrypointspinner = entryPointSpinner.getSelectedItem().toString();
        String locationn = location.getText().toString().trim();
        String terminalspinner = terminalSpinner.getSelectedItem().toString();
        String agentname = agentName.getText().toString().trim();
        String arrivaldate = arrivalDateTime.getText().toString().trim();

        if(scnnumber.isEmpty()){
            scnNumber.setError("SCN Number is required!");
            scnNumber.requestFocus();
            return;
        }

        if(vesselid.isEmpty()){
            vesselID.setError("Vessel ID is required!");
            vesselID.requestFocus();
            return;
        }

        if(vesselname.isEmpty()){
            vesselName.setError("Vessel Name is required");
            vesselName.requestFocus();
            return;
        }

        if(locationn.isEmpty()){
            location.setError("Location is required");
            location.requestFocus();
            return;
        }

        if(agentname.isEmpty()){
            agentName.setError("Agent Name is required!");
            agentName.requestFocus();
            return;
        }

        if(arrivaldate.isEmpty()){
            arrivalDateTime.setError("Arrival Date & Time is required!");
            return;
        }

        final int min = 0;
        final int max = 100;
        final int random = new Random().nextInt((max - min) + 1) + min;


        String id = (shipid+(random));

        Ship ship = new Ship(scnnumber.toUpperCase(), id, vesselid.toUpperCase(), vesselname.toUpperCase(), entrypointspinner, locationn.toUpperCase(), terminalspinner, agentname.toUpperCase(), arrivaldate);

        shipDbRef.child(id).setValue(ship);

        Toast.makeText(insertShipTime.this, "Data inserted successfully!", Toast.LENGTH_SHORT).show();


        scnNumber.setText("");
        vesselID.setText("");
        vesselName.setText("");
        location.setText("");
        agentName.setText("");
        arrivalDateTime.setText("");
    }
}