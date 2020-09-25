package com.example.prohub.viewadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prohub.Interface.FeaturedItemClickListener;
import com.example.prohub.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeaturedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView baseName, baseDecs;
    public CircleImageView baseImage;
    public FeaturedItemClickListener listener;
    private final Context context;

    public FeaturedViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        baseImage = (CircleImageView) itemView.findViewById(R.id.featured_image);
        baseName = (TextView)itemView.findViewById(R.id.featured_title);
        baseDecs = (TextView)itemView.findViewById(R.id.featured_description);
    }

    public void setItemClickListener(FeaturedItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}
