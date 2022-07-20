package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class staffDutySchedule extends AppCompatActivity {

    ListView myListView;
    List<dutyScheduleModel> dutyList;
    List<String> staffname, managername, shipname;
    SearchView searchview;

    DatabaseReference databasereference, databaseReference , DBshipdata, databaseReferenceship;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_duty_schedule);

        searchview = findViewById(R.id.searchview);
        myListView = findViewById(R.id.myListView);
        dutyList = new ArrayList<>();

        databasereference = FirebaseDatabase.getInstance().getReference("DutySchedule");
        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dutyList.clear();

                for(DataSnapshot dutyDatasnap : dataSnapshot.getChildren()){

                    dutyScheduleModel duty = dutyDatasnap.getValue(dutyScheduleModel.class);
                    dutyList.add(duty);


                }

                dutyScheduleAdapter adapter =  new dutyScheduleAdapter(staffDutySchedule.this,dutyList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dutyScheduleModel model = dutyList.get(position);
                Intent intent = new Intent(staffDutySchedule.this, dutyScheduleDetails.class);
                intent.putExtra("dutydetails", model.getDutydetails());
                intent.putExtra("dutyschedule", model.getDutyschedule());
                intent.putExtra("firebaseid", model.getFirebaseid());
                intent.putExtra("managername", model.getManagername());
                intent.putExtra("shipneedtobehandled", model.getShipneedtobehandled());
                intent.putExtra("staffname", model.getStaffname());
                intent.putExtra("wharfname", model.getWharfname());


                databaseReferenceship = FirebaseDatabase.getInstance().getReference();
                databaseReferenceship.child("ShipTime").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot childSnapshot :dataSnapshot.getChildren()){

                            if((childSnapshot.child("vesselid").getValue(String.class)).equals(model.getShipneedtobehandled())){
                                String firebaseidshiptime = childSnapshot.child("firebaseidshiptime").getValue(String.class);
                                String scnnumber = childSnapshot.child("scnnumber").getValue(String.class);
                                String vesselid = childSnapshot.child("vesselid").getValue(String.class);
                                String vesselname = childSnapshot.child("vesselname").getValue(String.class);
                                String entrypointspinner = childSnapshot.child("entrypointspinner").getValue(String.class);
                                String locationn = childSnapshot.child("locationn").getValue(String.class);
                                String terminalspinner = childSnapshot.child("terminalspinner").getValue(String.class);
                                String agentname = childSnapshot.child("agentname").getValue(String.class);
                                String arrivaldate = childSnapshot.child("arrivaldate").getValue(String.class);

                                intent.putExtra("firebaseidshiptime",firebaseidshiptime);
                                intent.putExtra("scnnumber",scnnumber);
                                intent.putExtra("vesselid",vesselid);
                                intent.putExtra("vesselname",vesselname);
                                intent.putExtra("entrypointspinner",entrypointspinner);
                                intent.putExtra("locationn",locationn);
                                intent.putExtra("terminalspinner",terminalspinner);
                                intent.putExtra("agentname",agentname);
                                intent.putExtra("arrivaldate",arrivaldate);
                                startActivity(intent);

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<dutyScheduleModel> filteredlist = new ArrayList<dutyScheduleModel>();
                for(dutyScheduleModel dutyschedulemodel: dutyList){
                    if((dutyschedulemodel.getStaffname().toLowerCase().contains(s.toLowerCase())) || (dutyschedulemodel.getShipneedtobehandled().toLowerCase().contains(s.toLowerCase()))){
                        filteredlist.add(dutyschedulemodel);
                    }
                }

                dutyScheduleAdapter adapter =  new dutyScheduleAdapter(staffDutySchedule.this,filteredlist);
                myListView.setAdapter(adapter);

                return false;
            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dutyScheduleModel model = dutyList.get(position);
                showUpdateDialog(model.getFirebaseid());


                return true;
            }
        });


    }

    private void showUpdateDialog(String firebaseid){

        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog,null);

        mDialog.setView(mDialogView);

        EditText etdutydetails = mDialogView.findViewById(R.id.updatedutydetails);
        EditText etdutyschedule = mDialogView.findViewById(R.id.updatedetailsDutySchedule);
        Spinner etmanagername = mDialogView.findViewById(R.id.updatemanagerincharge);
        Spinner etshipid = mDialogView.findViewById(R.id.updateshipneedtobehandled);
        Spinner etUpdateName = mDialogView.findViewById(R.id.updateStaffName);
        Spinner spinnerwharfname = mDialogView.findViewById(R.id.updatewharfname);
        Button btnUpdate = mDialogView.findViewById(R.id.btnupdate);
        Button btnDelete = mDialogView.findViewById(R.id.btndelete);

        //set spinner staff and spinner manager and ship name

        staffname = new ArrayList<>();
        managername = new ArrayList<>();
        shipname = new ArrayList<>();

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

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(staffDutySchedule.this, android.R.layout.simple_spinner_item, staffname);
                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(staffDutySchedule.this, android.R.layout.simple_spinner_item, managername);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
                etUpdateName.setAdapter(arrayAdapter);
                etmanagername.setAdapter(arrayAdapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DBshipdata = FirebaseDatabase.getInstance().getReference();
        DBshipdata.child("ShipTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot1:dataSnapshot.getChildren()){
                    String spinnershipname = childSnapshot1.child("vesselid").getValue(String.class);
                    shipname.add(spinnershipname);
                }

                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(staffDutySchedule.this, android.R.layout.simple_spinner_item, shipname);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
                etshipid.setAdapter(arrayAdapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //set time dialog
        etdutyschedule.setInputType(InputType.TYPE_NULL);
        etdutyschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                                etdutyschedule.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        };

                        new TimePickerDialog(staffDutySchedule.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
                    }
                };

                new DatePickerDialog(staffDutySchedule.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();



            }
        });

        mDialog.setTitle("Update record : "+firebaseid);
        final AlertDialog b = mDialog.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newdutydetails = etdutydetails.getText().toString();
                String newdutyschedule = etdutyschedule.getText().toString();
                String newmanagername = etmanagername.getSelectedItem().toString();
                String newstaffname = etUpdateName.getSelectedItem().toString();
                String newshipid = etshipid.getSelectedItem().toString();
                String newwharfname = spinnerwharfname.getSelectedItem().toString();


                if((TextUtils.isEmpty(newdutydetails)) || (TextUtils.isEmpty(newdutyschedule))){
                    Toast.makeText(staffDutySchedule.this, "Complete the data update!", Toast.LENGTH_SHORT).show();
                }

                if ((!TextUtils.isEmpty(newdutydetails)) && (!TextUtils.isEmpty(newdutyschedule)) ) {
                    updateData(newdutydetails, newdutyschedule, firebaseid,  newmanagername, newshipid, newstaffname ,newwharfname);
                    b.dismiss();
                    Toast.makeText(staffDutySchedule.this, "Record Updated", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(firebaseid);
                b.dismiss();
            }
        });
    }

    private void showToast (String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String firebaseid){
        DatabaseReference databasereference = FirebaseDatabase.getInstance().getReference("DutySchedule").child(firebaseid);

        Task<Void> mTask = databasereference.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showToast("Deleted");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error deleting record");
            }
        });
    }

    private void updateData(String dutydetails, String dutyschedule, String firebaseid, String managername,String shipneedtobehandled, String staffname, String wharfname){

        DatabaseReference databasereference = FirebaseDatabase.getInstance().getReference("DutySchedule").child(firebaseid);
        dutyScheduleModel model = new dutyScheduleModel(dutydetails, dutyschedule, firebaseid, managername, shipneedtobehandled, staffname,  wharfname);
        databasereference.setValue(model);

    }

}