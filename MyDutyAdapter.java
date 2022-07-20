package com.example.cargotracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyDutyAdapter extends RecyclerView.Adapter<MyDutyAdapter.MyViewHolder> {


    Context context;
    ArrayList<dutyScheduleModel> list;

    public MyDutyAdapter(Context context, ArrayList<dutyScheduleModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_myduty,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        dutyScheduleModel model = list.get(position);
        holder.firebaseidET.setText(model.getFirebaseid());
        holder.detailsDutySchedule.setText(model.getDutyschedule());
        holder.dutydetailsET.setText(model.getDutydetails());
        holder.managerinchargeET.setText(model.getManagername());
        holder.wharfnameET.setText(model.getWharfname());
        holder.shipneedtobehandledET.setText(model.getShipneedtobehandled());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dutyNo;
        EditText firebaseidET, detailsDutySchedule, dutydetailsET, managerinchargeET, wharfnameET, shipneedtobehandledET;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firebaseidET = itemView.findViewById(R.id.dutyidET);
            firebaseidET.setEnabled(false);
            detailsDutySchedule = itemView.findViewById(R.id.detailsDutySchedule);
            detailsDutySchedule.setEnabled(false);
            dutydetailsET = itemView.findViewById(R.id.dutydetailsET);
            dutydetailsET.setEnabled(false);
            managerinchargeET = itemView.findViewById(R.id.managerinchargeET);
            managerinchargeET.setEnabled(false);
            wharfnameET = itemView.findViewById(R.id.wharfnameET);
            wharfnameET.setEnabled(false);
            shipneedtobehandledET = itemView.findViewById(R.id.shipneedtobehandledET);
            shipneedtobehandledET.setEnabled(false);

        }


    }
}
