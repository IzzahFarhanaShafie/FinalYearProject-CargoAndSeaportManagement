package com.example.cargotracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class dutyScheduleAdapter extends ArrayAdapter {

    Activity context;
    List<dutyScheduleModel> dutySchedule_list;

    public dutyScheduleAdapter(Activity context, List<dutyScheduleModel> dutySchedule_list) {
        super(context,R.layout.list_view, dutySchedule_list);
        this.context = context;
        this.dutySchedule_list = dutySchedule_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_view,null,true);


        TextView staffname_tv = listItemView.findViewById(R.id.staffname_tv);
        TextView shipid_tv = listItemView.findViewById(R.id.shipid_tv);
        TextView dutyschedule_tv = listItemView.findViewById(R.id.dutyschedule_tv);

        dutyScheduleModel dutyScheduleModel = dutySchedule_list.get(position);

        staffname_tv.setText(dutyScheduleModel.getStaffname());
        shipid_tv.setText(dutyScheduleModel.getShipneedtobehandled());
        dutyschedule_tv.setText(dutyScheduleModel.getDutyschedule());

        return  listItemView;


    }

}
