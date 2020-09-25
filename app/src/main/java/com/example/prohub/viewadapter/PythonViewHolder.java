package com.example.prohub.viewadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.CplusItemClickListener;
import com.example.prohub.Interface.PythonItemClickListener;
import com.example.prohub.R;

public class PythonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView py_name, py_description;
    public PythonItemClickListener listener;

    public PythonViewHolder(@NonNull View itemView) {
        super(itemView);
        py_name = (TextView)itemView.findViewById(R.id.pyName);
        py_description = (TextView)itemView.findViewById(R.id.textt);
    }
    public void setItemClickListner(PythonItemClickListener listner){
        this.listener = listner;
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
