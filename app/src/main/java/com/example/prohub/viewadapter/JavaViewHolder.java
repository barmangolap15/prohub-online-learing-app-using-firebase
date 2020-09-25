package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.JavaItemClickListener;
import com.example.prohub.R;

public class JavaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView j_name, j_description;
    public JavaItemClickListener listener;

    public JavaViewHolder(@NonNull View itemView) {
        super(itemView);

        j_name = (TextView)itemView.findViewById(R.id.javaName);
        j_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(JavaItemClickListener listner){
        this.listener = listner;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
