package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.AndroidItemClickListener;
import com.example.prohub.Interface.JavaItemClickListener;
import com.example.prohub.R;

public class AndroidViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView android_name, android_description;
    public AndroidItemClickListener listener;

    public AndroidViewHolder(@NonNull View itemView) {
        super(itemView);

        android_name = (TextView)itemView.findViewById(R.id.androidName);
        android_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(AndroidItemClickListener listner){
        this.listener = listner;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
