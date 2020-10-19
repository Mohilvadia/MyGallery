package com.example.mygallery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygallery.ItemClickListner;
import com.example.mygallery.PicHolder;
import com.example.mygallery.Pojo.PictureFacer;
import com.example.mygallery.R;

import java.util.ArrayList;

import static androidx.core.view.ViewCompat.setTransitionName;

public class Image_Adapter extends RecyclerView.Adapter<PicHolder> {
    private ArrayList<PictureFacer> pictureList;
    private Context pictureContx;
    private ItemClickListner picListerner;

    public Image_Adapter(ArrayList<PictureFacer> pictureList, Context pictureContx, ItemClickListner picListerner) {
        this.pictureList = pictureList;
        this.pictureContx = pictureContx;
        this.picListerner = picListerner;
    }

    @NonNull
    @Override
    public PicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cell = inflater.inflate(R.layout.image_display, parent, false);
        return new PicHolder(cell);
    }

    @Override
    public void onBindViewHolder(@NonNull final PicHolder holder, final int position) {
        final PictureFacer image = pictureList.get(position);

        Glide.with(pictureContx)
                .load(image.getPicturePath())
                .apply(new RequestOptions().centerCrop())
                .into(holder.picture);

        setTransitionName(holder.picture, String.valueOf(position) + "_image");

        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picListerner.onPicClicked_url(holder,position, pictureList,image.getPicturePath());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }
}
