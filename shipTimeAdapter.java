package com.example.cargotracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class shipTimeAdapter extends ArrayAdapter {

    Activity context;
    List<shipTimeModel> shipTime_list;

    public shipTimeAdapter (Activity context, List<shipTimeModel> shipTime_list){
        super(context,R.layout.list_view_ship, shipTime_list);
        this.context = context;
        this.shipTime_list = shipTime_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemViewShip = inflater.inflate(R.layout.list_view_ship,null,true);


        TextView shippid_tv = listItemViewShip.findViewById(R.id.shippid_tv);
        TextView shipname_tv = listItemViewShip.findViewById(R.id.shipname_tv);
        TextView shiptime_tv = listItemViewShip.findViewById(R.id.shiptime_tv);

        shipTimeModel modell = shipTime_list.get(position);

        shippid_tv.setText(modell.getVesselid());
        shipname_tv.setText(modell.getVesselname());
        shiptime_tv.setText(modell.getArrivaldate());

        return  listItemViewShip;
    }
}
