package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class insertStaffShift extends AppCompatActivity {

    Spinner staffnamespinner, managernamespinner, shipnamespinner, wharfspinner;
    EditText dutyDetailsEditText;
    DatabaseReference databaseReference, databaseReference1, scheduleDbRef, databaseReference2;
    List<String> staffname, managername, shipname;
    Button btnSubmitStaffSchedule;

    EditText date_time_in;
    long maxdutyscheduleid = 0 ;
    String dutyid = "DutyScheduleID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_staff_shift);

        dutyDetailsEditText = findViewById(R.id.dutyDetailsEdittext);
        wharfspinner = findViewById(R.id.wharfSpinner);
        btnSubmitStaffSchedule = findViewById(R.id.submitButton);


        //set spinner for staff name, manager name and ship name
        staffnamespinner = findViewById(R.id.staffNameSpinner);
        managernamespinner = findViewById(R.id.managerinchargeSpinner);
        shipnamespinner = findViewById(R.id.shipneedtobehandledSpinner);

        staffname = new ArrayList<>();
        managername = new ArrayList<>();
        shipname = new ArrayList<>();

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("DutySchedule");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxdutyscheduleid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot:dataSnapshot.getChildren()) {

                    if(childSnapshot.child("usertype").getValue(String.class).equals("staff")) {
                        String spinnerstaffname = childSnapshot.child("fullname").getValue(String.class);
                        staffname.add(spinnerstaffname);
                    }

                    if(childSnapshot.child("usertype").getValue(String.class).equals("manager")){
                        String spinnermanagername = childSnapshot.child("fullname").getValue(String.class);
                        managername.add(spinnermanagername);
                    }

                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(insertStaffShift.this, android.R.layout.simple_spinner_item, staffname);
                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(insertStaffShift.this, android.R.layout.simple_spinner_item, managername);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
                staffnamespinner.setAdapter(arrayAdapter);
                managernamespinner.setAdapter(arrayAdapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference1.child("ShipTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot1:dataSnapshot.getChildren()){
                    String spinnershipname = childSnapshot1.child("vesselid").getValue(String.class);
                    shipname.add(spinnershipname);
                }

                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(insertStaffShift.this, android.R.layout.simple_spinner_item, shipname);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
                shipnamespinner.setAdapter(arrayAdapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //set time and dialog picker
        date_time_in = findViewById(R.id.dutyScheduleDate);
        date_time_in.setInputType(InputType.TYPE_NULL);

        date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDateTimeDialog(date_time_in);
            }
        });

        //submit button
        btnSubmitStaffSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertStaffSchedule();
            }
        });

        scheduleDbRef = FirebaseDatabase.getInstance().getReference().child("DutySchedule");

    }

    private void InsertStaffSchedule(){
        String staffname = staffnamespinner.getSelectedItem().toString();
        String dutyschedule = date_time_in.getText().toString().trim();
        String dutydetails = dutyDetailsEditText.getText().toString().trim();
        String managername = managernamespinner.getSelectedItem().toString();
        String wharfname = wharfspinner.getSelectedItem().toString();
        String shipneedtobehandled = shipnamespinner.getSelectedItem().toString();

        if(dutyschedule.isEmpty()){
            date_time_in.setError("Duty Schedule is required!");
            date_time_in.requestFocus();
            return;
        }

        if(dutydetails.isEmpty()){
            dutyDetailsEditText.setError("Duty Details is required!");
            dutyDetailsEditText.requestFocus();
            return;
        }

        final int min = 0;
        final int max = 100;
        final int random = new Random().nextInt((max - min) + 1) + min;

        String id = (dutyid+(random));
        String dutyID = String.valueOf((maxdutyscheduleid+1));

        StaffSchedule staffschedule = new StaffSchedule (dutyID, id, staffname.toUpperCase(), dutyschedule, dutydetails, managername.toUpperCase(), wharfname.toUpperCase(), shipneedtobehandled.toUpperCase());

        scheduleDbRef.child(id).setValue(staffschedule);
        Toast.makeText(insertStaffShift.this, "New Staff Schedule inserted successfully!", Toast.LENGTH_SHORT).show();
        date_time_in.setText("");
        dutyDetailsEditText.setText("");

    }

    private void showDateTimeDialog(final EditText date_time_in) {
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

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(insertStaffShift.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(insertStaffShift.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}