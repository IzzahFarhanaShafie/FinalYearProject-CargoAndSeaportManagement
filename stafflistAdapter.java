package com.example.cargotracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class stafflistAdapter extends RecyclerView.Adapter<stafflistAdapter.MyViewHolder>{

    Context context;

    ArrayList<stafflistModel> list;

    public stafflistAdapter(Context context, ArrayList<stafflistModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_stafflist,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        stafflistModel model = list.get(position);
        holder.name_stafflist.setText(model.getFullname());
        holder.staffid_stafflist.setText(model.getStaffid());
        holder.usertype_stafflist.setText(model.getUsertype().toUpperCase());
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stafflistModel model = list.get(position);
                Intent intent = new Intent(context, stafflistdetails.class);
                intent.putExtra("emailprofile", model.getEmailprofile());
                intent.putExtra("fullname", model.getFullname());
                intent.putExtra("icnumber", model.getIcnumber());
                intent.putExtra("image", model.getImage());
                intent.putExtra("phonenumber", model.getPhonenumber());
                intent.putExtra("port", model.getPort());
                intent.putExtra("position", model.getPosition());
                intent.putExtra("staffid", model.getStaffid());
                intent.putExtra("wharf", model.getWharf());
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name_stafflist, staffid_stafflist, usertype_stafflist;
        RelativeLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageview_stafflist);
            name_stafflist = itemView.findViewById(R.id. name_stafflist);
            staffid_stafflist = itemView.findViewById(R.id.staffid_stafflist);
            usertype_stafflist = itemView.findViewById(R.id.usertype_stafflist);
            parentLayout = itemView.findViewById(R.id.relativeLayoutstafflist);
        }
    }
}
