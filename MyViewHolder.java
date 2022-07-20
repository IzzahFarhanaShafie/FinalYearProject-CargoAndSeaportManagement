package com.example.cargotracker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView image_announcement;
    TextView text_announcement;
    View v;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        image_announcement = itemView.findViewById(R.id.image_announcement);
        text_announcement = itemView.findViewById(R.id.title_announcement);
        v = itemView;
    }
}
