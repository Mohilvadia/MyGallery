package com.example.mygallery.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygallery.ItemClickListner;
import com.example.mygallery.Pojo.ImageFolder;
import com.example.mygallery.Prefrence;
import com.example.mygallery.R;

import java.util.ArrayList;

import static androidx.core.view.ViewCompat.setTransitionName;

public class PictureAdapter extends RecyclerView.Adapter<FolderHolder> {
    private ArrayList<ImageFolder> folders;
    private Context folderContx;
    private ItemClickListner picListerner;


    public PictureAdapter(ArrayList<ImageFolder> folders, Context folderContx,ItemClickListner listen) {
        this.folders = folders;
        this.folderContx = folderContx;
        this.picListerner = listen;
    }

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View cell = inflater.inflate(R.layout.image_raw, viewGroup, false);
        return new FolderHolder(cell);
    }

    @Override
    public void onBindViewHolder(@NonNull final FolderHolder picHolder, int i) {
        final ImageFolder folder = folders.get(i);
        if (Prefrence.getInstance(folderContx).getCropThumb()){
            Glide.with(folderContx)
                    .load(folder.getFirstPic())
                    .apply(new RequestOptions().centerCrop())
                    .into(picHolder.folderPic);

        }
        else {
            Glide.with(folderContx)
                    .load(folder.getFirstPic())
                    .into(picHolder.folderPic);

        }

        String text = "("+folder.getNumberOfPics()+") "+folder.getFolderName();
        Log.d("error",text);
        Log.d("error",folder.getPath());
        picHolder.folderName.setText(text);

        picHolder.folderPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picListerner.onPicClicked_media(folder.getPath(),folder.getFolderName(),folder.isMedia_picker());

            }
        });
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }


}
 class FolderHolder extends RecyclerView.ViewHolder{
    ImageView folderPic;
    TextView folderName;
    CardView folderCard;

    public FolderHolder(@NonNull View itemView) {
        super(itemView);
        folderPic = itemView.findViewById(R.id.folderpic);
        folderName = itemView.findViewById(R.id.foldername);
    }
}