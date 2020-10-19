package com.example.mygallery;

import com.example.mygallery.Pojo.PictureFacer;

import java.util.ArrayList;

public interface ItemClickListner {

    void onPicClicked(PicHolder holder, int position, ArrayList<PictureFacer> pics);
    void onPicClicked_url(PicHolder holder, int position, ArrayList<PictureFacer> pics,String path);
    void onPicClicked(String pictureFolderPath,String folderName);
    void onPicClicked_media(String pictureFolderPath,String folderName,boolean media_picker);
}
