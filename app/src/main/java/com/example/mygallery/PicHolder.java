package com.example.mygallery;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicHolder extends RecyclerView.ViewHolder{

    public ImageView picture;

    public PicHolder(@NonNull View itemView) {
        super(itemView);

        picture = itemView.findViewById(R.id.image_display);
    }
}

