package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.BigDataItemClickListener;
import com.example.prohub.Interface.CnItemClickListener;
import com.example.prohub.R;

public class CnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cn_name, cn_description;
    public CnItemClickListener listener;

    public CnViewHolder(@NonNull View itemView) {
        super(itemView);

        cn_name = (TextView)itemView.findViewById(R.id.cnName);
        cn_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(CnItemClickListener listner){
        this.listener = listner;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
