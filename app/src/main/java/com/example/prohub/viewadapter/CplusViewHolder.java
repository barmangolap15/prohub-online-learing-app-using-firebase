package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.CplusItemClickListener;
import com.example.prohub.R;

public class CplusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cp_name, cp_description;
    public CplusItemClickListener listener;

    public CplusViewHolder(@NonNull View itemView) {
        super(itemView);
        cp_name = (TextView)itemView.findViewById(R.id.cpName);
        cp_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(CplusItemClickListener listner){
        this.listener = listner;
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
