package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class shipTimeSchedule extends AppCompatActivity {

    ListView myListViewShip;
    List<shipTimeModel> shipList;
    SearchView searchView;
    DatabaseReference databaseship;
    String putscnnumber, putvesselid, putvesselname, putentrypoint, putlocation, putterminal, putagentname, putarrival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_time_schedule);

        myListViewShip = findViewById(R.id.myListViewShip);
        shipList = new ArrayList<>();

        databaseship = FirebaseDatabase.getInstance().getReference("ShipTime");
        databaseship.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shipList.clear();

                for(DataSnapshot ShipDatasnap : dataSnapshot.getChildren()){

                    shipTimeModel shipmodel = ShipDatasnap.getValue(shipTimeModel.class);
                    shipList.add(shipmodel);


                }

                shipTimeAdapter adapter =  new shipTimeAdapter(shipTimeSchedule.this,shipList);
                myListViewShip.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView = findViewById(R.id.searchviewship);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<shipTimeModel> filteredlist = new ArrayList<shipTimeModel>();
                for(shipTimeModel shiptimemodel: shipList){
                    if((shiptimemodel.getVesselid().toLowerCase().contains(s.toLowerCase())) || (shiptimemodel.getVesselname().toLowerCase().contains(s.toLowerCase()))){
                        filteredlist.add(shiptimemodel);
                    }
                }

                shipTimeAdapter adapter =  new shipTimeAdapter(shipTimeSchedule.this,filteredlist);
                myListViewShip.setAdapter(adapter);

                return false;
            }
        });

        myListViewShip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                shipTimeModel model = shipList.get(position);
                Intent intent = new Intent(shipTimeSchedule.this,shipTimeDetails.class);
                intent.putExtra("firebaseidshiptime", model.getFirebaseidshiptime());
                intent.putExtra("scnnumber",model.getScnnumber());
                intent.putExtra("vesselid",model.getVesselid());
                intent.putExtra("vesselname",model.getVesselname());
                intent.putExtra("entrypointspinner",model.getEntrypointspinner());
                intent.putExtra("locationn",model.getLocationn());
                intent.putExtra("terminalspinner",model.getTerminalspinner());
                intent.putExtra("agentname",model.getAgentname());
                intent.putExtra("arrivaldate",model.getArrivaldate());
                startActivity(intent);
            }
        });

        myListViewShip.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                shipTimeModel modelship = shipList.get(position);
                putscnnumber = (modelship.getScnnumber());
                putvesselid = (modelship.getVesselid());
                putvesselname = (modelship.getVesselname());
                putentrypoint = (modelship.getEntrypointspinner());
                putlocation = (modelship.getLocationn());
                putterminal = (modelship.getTerminalspinner());
                putagentname = (modelship.getAgentname());
                putarrival = (modelship.getArrivaldate());
                showUpdateDialog(modelship.getFirebaseidshiptime());

                return true;
            }
        });


    }

    private void showUpdateDialog(String firebaseidshiptime){

        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.updateship_dialog,null);

        mDialog.setView(mDialogView);

        EditText updateScnNumber = mDialogView.findViewById(R.id.updateScnNumber);
        EditText updatevesselid = mDialogView.findViewById(R.id.updatevesselid);
        EditText updatevesselname = mDialogView.findViewById(R.id.updatevesselname);
        Spinner updateentrypoint = mDialogView.findViewById(R.id.updateentrypoint);
        EditText updatelocation = mDialogView.findViewById(R.id.updatelocation);
        Spinner updateterminal = mDialogView.findViewById(R.id.updateterminal);
        EditText updateagentname = mDialogView.findViewById(R.id.updateagentname);
        EditText updatearrivaldate = mDialogView.findViewById(R.id.updatearrivaldate);
        Button btnUpdatee = mDialogView.findViewById(R.id.btnupdatee);
        Button btnDeletee = mDialogView.findViewById(R.id.btndeletee);

        updateScnNumber.setText(putscnnumber);
        updatevesselid.setText(putvesselid);
        updatevesselname.setText(putvesselname);
        updateentrypoint.setSelected(Boolean.parseBoolean(putentrypoint));
        updatelocation.setText(putlocation);
        updateterminal.setSelected(Boolean.parseBoolean(putterminal));
        updateagentname.setText(putagentname);
        updatearrivaldate.setText(putarrival);


        //set time dialog
        updatearrivaldate.setInputType(InputType.TYPE_NULL);
        updatearrivaldate.setOnClickListener(new View.OnClickListener() {
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

                                updatearrivaldate.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        };

                        new TimePickerDialog(shipTimeSchedule.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
                    }
                };

                new DatePickerDialog(shipTimeSchedule.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();



            }
        });

        mDialog.setTitle("Update record : "+firebaseidshiptime);
        final AlertDialog b = mDialog.create();
        b.show();

        btnUpdatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newscnnumber = updateScnNumber.getText().toString();
                String newvesselid = updatevesselid.getText().toString();
                String newvesselname = updatevesselname.getText().toString();
                String newentrypoint = updateentrypoint.getSelectedItem().toString();
                String newlocation = updatelocation.getText().toString();
                String newterminal = updateterminal.getSelectedItem().toString();
                String newagentname = updateagentname.getText().toString();
                String newarrivaldate = updatearrivaldate.getText().toString();


                if((TextUtils.isEmpty(newscnnumber)) || (TextUtils.isEmpty(newvesselid)) || (TextUtils.isEmpty(newvesselname)) || (TextUtils.isEmpty(newlocation)) || (TextUtils.isEmpty(newagentname)) || (TextUtils.isEmpty(newarrivaldate))){
                    Toast.makeText(shipTimeSchedule.this, "Complete the data update!", Toast.LENGTH_SHORT).show();
                }

                if ((!TextUtils.isEmpty(newscnnumber)) && (!TextUtils.isEmpty(newvesselid)) && (!TextUtils.isEmpty(newvesselname)) && (!TextUtils.isEmpty(newlocation)) && (!TextUtils.isEmpty(newagentname)) && (!TextUtils.isEmpty(newarrivaldate))){
                    updateData(newagentname, newarrivaldate, newentrypoint,  firebaseidshiptime, newlocation, newscnnumber ,newterminal, newvesselid, newvesselname);
                    b.dismiss();
                    Toast.makeText(shipTimeSchedule.this, "Record Updated", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnDeletee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(firebaseidshiptime);
                b.dismiss();
            }
        });
    }

    private void showToast (String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String firebaseidshiptime){
        DatabaseReference databasereference = FirebaseDatabase.getInstance().getReference("ShipTime").child(firebaseidshiptime);

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

    private void updateData(String agentname, String arrivaldate, String entrypointspinner, String firebaseidshiptime,String locationn, String scnnumber, String terminalspinner, String vesselid, String vesselname){

        DatabaseReference databasereferencee = FirebaseDatabase.getInstance().getReference("ShipTime").child(firebaseidshiptime);
        shipTimeModel model = new shipTimeModel(agentname, arrivaldate, entrypointspinner, firebaseidshiptime, locationn, scnnumber,  terminalspinner, vesselid,  vesselname);
        databasereferencee.setValue(model);

    }
}