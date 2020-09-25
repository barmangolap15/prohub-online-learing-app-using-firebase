package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.CnItemClickListener;
import com.example.prohub.Interface.MlItemClickListener;
import com.example.prohub.R;

public class MlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView ml_name, ml_description;
    public MlItemClickListener listener;

    public MlViewHolder(@NonNull View itemView) {
        super(itemView);

        ml_name = (TextView)itemView.findViewById(R.id.mlName);
        ml_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(MlItemClickListener listner){
        this.listener = listner;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
