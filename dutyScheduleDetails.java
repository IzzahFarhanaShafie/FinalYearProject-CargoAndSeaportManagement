package com.example.cargotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cargotracker.Notification.Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class dutyScheduleDetails extends AppCompatActivity {

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duty_schedule_details);

        getIncomingIntent();



    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("dutydetails") && getIntent().hasExtra("dutyschedule") &&  getIntent().hasExtra("firebaseid") &&  getIntent().hasExtra("managername")  && getIntent().hasExtra("shipneedtobehandled") && getIntent().hasExtra("staffname") && getIntent().hasExtra("wharfname")
                && getIntent().hasExtra("firebaseidshiptime") && getIntent().hasExtra("scnnumber") && getIntent().hasExtra("vesselid") && getIntent().hasExtra("vesselname") && getIntent().hasExtra("entrypointspinner") && getIntent().hasExtra("locationn") && getIntent().hasExtra("terminalspinner") && getIntent().hasExtra("agentname") && getIntent().hasExtra("arrivaldate")) {


            String txtDutydetails = getIntent().getStringExtra("dutydetails");
            String txtdutyschedule = getIntent().getStringExtra("dutyschedule");
            String txtfirebaseid = getIntent().getStringExtra("firebaseid");
            String txtmanagername = getIntent().getStringExtra("managername");
            String txtshipneedtobehandled = getIntent().getStringExtra("shipneedtobehandled");
            String txtStaffname = getIntent().getStringExtra("staffname");
            String txtWharfname = getIntent().getStringExtra("wharfname");

            String txtfirebaseidshiptime = getIntent().getStringExtra("firebaseidshiptime");
            String txtscnnumber = getIntent().getStringExtra("scnnumber");
            String txtvesselid = getIntent().getStringExtra("vesselid");
            String txtvesselname = getIntent().getStringExtra("vesselname");
            String txtentrypointspinner = getIntent().getStringExtra("entrypointspinner");
            String txtlocationn = getIntent().getStringExtra("locationn");
            String txtterminalspinner = getIntent().getStringExtra("terminalspinner");
            String txtagentname = getIntent().getStringExtra("agentname");
            String txtarrivaldate = getIntent().getStringExtra("arrivaldate");



            setData(txtDutydetails, txtdutyschedule, txtfirebaseid, txtmanagername, txtshipneedtobehandled, txtStaffname, txtWharfname,
                    txtfirebaseidshiptime, txtscnnumber, txtvesselid, txtvesselname, txtentrypointspinner, txtlocationn, txtterminalspinner, txtagentname, txtarrivaldate);
        }
    }


    private void setData( String txtDutydetails, String txtdutyschedule, String txtfirebaseid, String txtmanagername, String txtshipneedtobehandled, String txtStaffname, String txtWharfname,
                         String txtfirebaseidshiptime, String txtscnnumber,String txtvesselid,String txtvesselname,String txtentrypointspinner,String txtlocationn,String txtterminalspinner,String txtagentname,String txtarrivaldate) {



        EditText firebaseid = findViewById(R.id.dutyidET);
        firebaseid.setText(txtfirebaseid);
        firebaseid.setEnabled(false);

        EditText dutydetails = findViewById(R.id.dutydetailsET);
        dutydetails.setText(txtDutydetails);
        dutydetails.setEnabled(false);

        EditText dutyschedule = findViewById(R.id.detailsDutySchedule);
        dutyschedule.setText(txtdutyschedule);
        dutyschedule.setEnabled(false);

        EditText managername = findViewById(R.id.managerinchargeET);
        managername.setText(txtmanagername);
        managername.setEnabled(false);

        EditText shipneedtobehandled = findViewById(R.id.detailsShipneedtobehandled);
        shipneedtobehandled.setText(txtshipneedtobehandled);
        shipneedtobehandled.setEnabled(false);

        TextView staffname = findViewById(R.id.detailsStaffname);
        staffname.setText(txtStaffname);

        EditText wharfname = findViewById(R.id.wharfnameET);
        wharfname.setText(txtWharfname);
        wharfname.setEnabled(false);


        //shiptime display

        EditText shipidET = findViewById(R.id.shipidET);
        shipidET.setText(txtfirebaseidshiptime);
        shipidET.setEnabled(false);

        EditText scnnumberET = findViewById(R.id.scnnumberET);
        scnnumberET.setText(txtscnnumber);
        scnnumberET.setEnabled(false);

        EditText vesselidET = findViewById(R.id.vesselidET);
        vesselidET.setText(txtvesselid);
        vesselidET.setEnabled(false);

        EditText vesselnameET = findViewById(R.id.vesselnameET);
        vesselnameET.setText(txtvesselname);
        vesselnameET.setEnabled(false);

        EditText arrivaldateET = findViewById(R.id.arrivaldateET);
        arrivaldateET.setText(txtarrivaldate);
        arrivaldateET.setEnabled(false);

        EditText entrypointET = findViewById(R.id.entrypointET);
        entrypointET.setText(txtentrypointspinner);
        entrypointET.setEnabled(false);

        EditText locationET = findViewById(R.id.locationET);
        locationET.setText(txtlocationn);
        locationET.setEnabled(false);

        EditText terminalET = findViewById(R.id.terminalET);
        terminalET.setText(txtterminalspinner);
        terminalET.setEnabled(false);

        EditText agentNameET = findViewById(R.id.agentNameET);
        agentNameET.setText(txtagentname);
        agentNameET.setEnabled(false);


    }
}