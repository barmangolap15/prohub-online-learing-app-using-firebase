package com.example.prohub.viewadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.CategoriesItemClickListener;
import com.example.prohub.Interface.FeaturedItemClickListener;
import com.example.prohub.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView catName;
    public ImageView catImage;
    public CategoriesItemClickListener listener;
    private final Context context;

    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        catImage = (ImageView) itemView.findViewById(R.id.tech_image);
        catName = (TextView)itemView.findViewById(R.id.tech_title);
    }

    public void setItemClickListener(CategoriesItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}
