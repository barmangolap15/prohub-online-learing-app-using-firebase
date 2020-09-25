package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.CItemClickListener;
import com.example.prohub.Interface.JavaItemClickListener;
import com.example.prohub.R;

public class CViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView c_name, c_description;
    public CItemClickListener listener;

    public CViewHolder(@NonNull View itemView) {
        super(itemView);
        c_name = (TextView)itemView.findViewById(R.id.cName);
        c_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(CItemClickListener listner){
        this.listener = listner;
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
