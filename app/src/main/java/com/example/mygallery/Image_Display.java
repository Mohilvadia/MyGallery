package com.example.mygallery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Fade;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mygallery.Adapter.Image_Adapter;
import com.example.mygallery.Pojo.PictureFacer;

import java.util.ArrayList;

public class Image_Display extends AppCompatActivity implements ItemClickListner {
    RecyclerView imageRecycler;
    ArrayList<PictureFacer> allpictures;
    ProgressBar load;
    String foldePath;
    TextView folderName;
    int count=0;
    int counter=0;
    boolean media_picker;
    Uri videouri;
    BlankFragment browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__display);
        folderName = findViewById(R.id.foldername1);
         folderName.setText(getIntent().getStringExtra("folderName"));
        foldePath =  getIntent().getStringExtra("folderPath");
        media_picker=getIntent().getBooleanExtra("media_picker",false);
        allpictures = new ArrayList<>();
        imageRecycler = findViewById(R.id.rec_display);
        imageRecycler.hasFixedSize();
      //  load = findViewById(R.id.loader);


        if(allpictures.isEmpty()){
//            load.setVisibility(View.VISIBLE);
            if (media_picker){

                allpictures = getAllvideo(foldePath);

                imageRecycler.setAdapter(new Image_Adapter(allpictures,getApplicationContext(),this));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),4,GridLayoutManager.VERTICAL,false);
                if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                    gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

                }
                else {
                    gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

                }
                imageRecycler.setLayoutManager(gridLayoutManager);

            }
            else {
                allpictures = getAllImagesByFolder(foldePath);

                imageRecycler.setAdapter(new Image_Adapter(allpictures, getApplicationContext(), this));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4, GridLayoutManager.VERTICAL, false);                if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                    gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

                }
                else {
                    gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

                }

                imageRecycler.setLayoutManager(gridLayoutManager);
            }

            Log.d("error",allpictures.toString());
//            load.setVisibility(View.GONE);
        }else{

        }

    }


    public ArrayList<PictureFacer> getAllImagesByFolder(String path){
        ArrayList<PictureFacer> images = new ArrayList<>();
        Uri allVideosuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,MediaStore.Video.Media.DATE_MODIFIED};
        Cursor cursor = getApplicationContext().getContentResolver().query( allVideosuri, projection, MediaStore.Images.Media.DATA + " like ? ", new String[] {"%"+path+"%"}, null);
        try {
            cursor.moveToFirst();
            do{
                PictureFacer pic = new PictureFacer();

                pic.setPicturName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));

                pic.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));

                pic.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));

                images.add(pic);
            }while(cursor.moveToNext());
            cursor.close();
            ArrayList<PictureFacer> reSelection = new ArrayList<>();
            for(int i = images.size()-1;i > -1;i--){
                reSelection.add(images.get(i));
            }
            images = reSelection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    public ArrayList<PictureFacer> getAllvideo(String path){
        ArrayList<PictureFacer> images = new ArrayList<>();
        Uri allVideosuri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
          videouri = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Images.ImageColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE};
        Cursor cursor = getApplicationContext().getContentResolver().query( allVideosuri, projection, MediaStore.Images.Media.DATA + " like ? ", new String[] {"%"+path+"%"}, null);
        try {
            cursor.moveToFirst();
            do{
                PictureFacer pic = new PictureFacer();

                pic.setPicturName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));

                pic.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));

                pic.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));

                images.add(pic);
            }while(cursor.moveToNext());
            cursor.close();
            ArrayList<PictureFacer> reSelection = new ArrayList<>();
            for(int i = images.size()-1;i > -1;i--){
                reSelection.add(images.get(i));
            }
            images = reSelection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onPicClicked(PicHolder holder, int position, ArrayList<PictureFacer> pics) {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onPicClicked_url(PicHolder holder, int position, ArrayList<PictureFacer> pics, String path) {
        if (media_picker){

            Intent intent=new Intent(this,VideoView.class);
            intent.putExtra("url",path);

            intent.putParcelableArrayListExtra("list",pics);
            intent.putExtra("position",position);
            startActivity(intent);
        }
        else {
             browser = BlankFragment.newInstance(pics, position, Image_Display.this,path);

            browser.setEnterTransition(new Fade());
            browser.setExitTransition(new Fade());

            getSupportFragmentManager()
                    .beginTransaction()

                    .addSharedElement(holder.picture, position + "picture")
                    .add(R.id.llImgDisplay, browser)
                    .addToBackStack("imageview")
                    .commit();
        }
    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName) {

        }

    @Override
    public void onPicClicked_media(String pictureFolderPath, String folderName, boolean media_picker) {

    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            int increase=0;
            int decrease=0;
            if (id==R.id.sort){

            }
            else if(id==R.id.filter){

            }
            else if (id==R.id.change){}
            else if(id==R.id.increase){
                count++;
                allpictures.clear();

    //            load.setVisibility(View.VISIBLE);
                    allpictures = getAllImagesByFolder(foldePath);
                    if (counter==0) {
                        imageRecycler.setAdapter(new Image_Adapter(allpictures, getApplicationContext(), this));
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4 + count, GridLayoutManager.VERTICAL, false);
                        if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                            gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

                        }
                        else {
                            gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

                        }
                        imageRecycler.setLayoutManager(gridLayoutManager);
                    }
                    else {
                        imageRecycler.setAdapter(new Image_Adapter(allpictures, getApplicationContext(), this));
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4-counter + count, GridLayoutManager.VERTICAL, false);
                        if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                            gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

                        }
                        else {
                            gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

                        }
                        imageRecycler.setLayoutManager(gridLayoutManager);
                    }

                    Log.d("error",allpictures.toString());
    //            load.setVisibility(View.GONE);

            }
            else if (id==R.id.decrease){
                counter++;
                allpictures.clear();
                if (count==0) {
                    imageRecycler.setAdapter(new Image_Adapter(allpictures, getApplicationContext(), this));
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4, GridLayoutManager.VERTICAL, false);
                    if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                        gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

                    }
                    else {
                        gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

                    }
                    imageRecycler.setLayoutManager(gridLayoutManager);
                }
                else {
                    imageRecycler.setAdapter(new Image_Adapter(allpictures, getApplicationContext(), this));
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4, GridLayoutManager.VERTICAL, false);
                    if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                        gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

                    }
                    else {
                        gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

                    }
                    imageRecycler.setLayoutManager(gridLayoutManager);
                }
            }
            else if (id==R.id.new_folder){}
            else if (id==R.id.Settings){

            }
            return super.onOptionsItemSelected(item);
        }

    @Override
    protected void onResume() {
        allpictures.clear();
        if (media_picker){

            allpictures = getAllvideo(foldePath);

            imageRecycler.setAdapter(new Image_Adapter(allpictures,getApplicationContext(),this));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),4,GridLayoutManager.VERTICAL,false);
            if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

            }
            else {
                gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

            }
            imageRecycler.setLayoutManager(gridLayoutManager);

        }
        else {
            allpictures = getAllImagesByFolder(foldePath);

            imageRecycler.setAdapter(new Image_Adapter(allpictures, getApplicationContext(), this));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4, GridLayoutManager.VERTICAL, false);
            if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
                gridLayoutManager.setOrientation(imageRecycler.HORIZONTAL);

            }
            else {
                gridLayoutManager.setOrientation(imageRecycler.VERTICAL);

            }

            imageRecycler.setLayoutManager(gridLayoutManager);
        }

        Log.d("error",allpictures.toString());
//            load.setVisibility(View.GONE);
        super.onResume();

    }


}