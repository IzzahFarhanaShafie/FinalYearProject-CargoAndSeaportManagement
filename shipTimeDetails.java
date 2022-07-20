package com.example.cargotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class shipTimeDetails extends AppCompatActivity {

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_time_details);

        getIncomingIntent();


    }

    private void getIncomingIntent(){

        if (getIntent().hasExtra("firebaseidshiptime") && getIntent().hasExtra("scnnumber") && getIntent().hasExtra("vesselid") && getIntent().hasExtra("vesselname") && getIntent().hasExtra("entrypointspinner") && getIntent().hasExtra("locationn") && getIntent().hasExtra("terminalspinner") && getIntent().hasExtra("agentname") && getIntent().hasExtra("arrivaldate")){
            String txtfirebaseidshiptime = getIntent().getStringExtra("firebaseidshiptime");
            String txtscnnumber = getIntent().getStringExtra("scnnumber");
            String txtvesselid = getIntent().getStringExtra("vesselid");
            String txtvesselname = getIntent().getStringExtra("vesselname");
            String txtentrypointspinner = getIntent().getStringExtra("entrypointspinner");
            String txtlocationn = getIntent().getStringExtra("locationn");
            String txtterminalspinner = getIntent().getStringExtra("terminalspinner");
            String txtagentname = getIntent().getStringExtra("agentname");
            String txtarrivaldate = getIntent().getStringExtra("arrivaldate");

            setData(txtfirebaseidshiptime, txtscnnumber, txtvesselid, txtvesselname, txtentrypointspinner, txtlocationn, txtterminalspinner, txtagentname, txtarrivaldate);
        }
    }

    private void setData(String txtfirebaseidshiptime,String txtscnnumber,String txtvesselid,String txtvesselname,String txtentrypointspinner,String txtlocationn,String txtterminalspinner,String txtagentname,String txtarrivaldate){

        EditText shipidET = findViewById(R.id.shipidETT);
        shipidET.setText(txtfirebaseidshiptime);
        shipidET.setEnabled(false);

        EditText scnnumberET = findViewById(R.id.scnnumberETT);
        scnnumberET.setText(txtscnnumber);
        scnnumberET.setEnabled(false);

        EditText vesselidET = findViewById(R.id.vesselidETT);
        vesselidET.setText(txtvesselid);
        vesselidET.setEnabled(false);

        EditText vesselnameET = findViewById(R.id.vesselnameETT);
        vesselnameET.setText(txtvesselname);
        vesselnameET.setEnabled(false);

        EditText arrivaldateET = findViewById(R.id.arrivaldateETT);
        arrivaldateET.setText(txtarrivaldate);
        arrivaldateET.setEnabled(false);

        EditText entrypointET = findViewById(R.id.entrypointETT);
        entrypointET.setText(txtentrypointspinner);
        entrypointET.setEnabled(false);

        EditText locationET = findViewById(R.id.locationETT);
        locationET.setText(txtlocationn);
        locationET.setEnabled(false);

        EditText terminalET = findViewById(R.id.terminalETT);
        terminalET.setText(txtterminalspinner);
        terminalET.setEnabled(false);

        EditText agentNameET = findViewById(R.id.agentNameETT);
        agentNameET.setText(txtagentname);
        agentNameET.setEnabled(false);
    }
}