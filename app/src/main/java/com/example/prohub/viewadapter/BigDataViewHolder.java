package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.AndroidItemClickListener;
import com.example.prohub.Interface.BigDataItemClickListener;
import com.example.prohub.R;

public class BigDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView bigdata_name, bigdata_description;
    public BigDataItemClickListener listener;

    public BigDataViewHolder(@NonNull View itemView) {
        super(itemView);

        bigdata_name = (TextView)itemView.findViewById(R.id.bigdataName);
        bigdata_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(BigDataItemClickListener listner){
        this.listener = listner;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
