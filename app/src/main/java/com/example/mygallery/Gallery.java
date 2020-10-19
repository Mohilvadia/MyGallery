package com.example.mygallery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.example.mygallery.Pojo.PictureFacer;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class Gallery extends AppCompatActivity implements ItemClickListner {
    Toolbar toolbar;
    static int count = 0;
    static int counter = 0;
    boolean name_filter = false;
    boolean Date_filter = false;
    boolean Size_default = false;
    TabLayout tabLayout;
    Image_Fragment fragment = new Image_Fragment();
    Video_Fragment fragment1 = new Video_Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        allocatememory();
        initview();
        event();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void event() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragment = new Image_Fragment();
                fragment1 = new Video_Fragment();
                if (tabLayout.getSelectedTabPosition() == 0) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frm, fragment)
                            .commit();

                }
                if (tabLayout.getSelectedTabPosition() == 1) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frm, fragment1)
                            .commit();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void allocatememory() {
        // recyclerView=findViewById(R.id.folders);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tablayout);
    }

    private void initview() {
        fragment = new Image_Fragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frm, fragment)
                .commit();
//        media_picker=true;
//        folds = getPicturePaths();
//        folderAdapter = new PictureAdapter(folds,this,this);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
//        gridLayoutManager.setOrientation(recyclerView.VERTICAL);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(folderAdapter);


    }

//    private void showCustomDialog() {
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        final View dialogView = LayoutInflater.from(this).inflate(R.layout.media_selection, viewGroup, false);
//
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setView(dialogView);
//
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        final CheckBox image=alertDialog.findViewById(R.id.image_box);
//        final CheckBox video=alertDialog.findViewById(R.id.video_box);
//        final Button positive=alertDialog.findViewById(R.id.postive);
//        Button negative=alertDialog.findViewById(R.id.negative);
//
//        positive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.hide();
//                if (image.isChecked()){
//                    folds.clear();
//                    folds = getPicturePaths();
//
//                    folderAdapter = new PictureAdapter(folds,Gallery.this,Gallery.this);
//                    GridLayoutManager gridLayoutManager=new GridLayoutManager(Gallery.this,4);
//                    gridLayoutManager.setOrientation(recyclerView.VERTICAL);
//                    recyclerView.setLayoutManager(gridLayoutManager);
//                    recyclerView.setAdapter(folderAdapter);
//                }
//                 if (video.isChecked()){
//                        folds.clear();
//                        folds = getVideoptath();
//
//                        folderAdapter = new PictureAdapter(folds,Gallery.this,Gallery.this);
//                        GridLayoutManager gridLayoutManager=new GridLayoutManager(Gallery.this,4);
//                        gridLayoutManager.setOrientation(recyclerView.VERTICAL);
//                        recyclerView.setLayoutManager(gridLayoutManager);
//                        recyclerView.setAdapter(folderAdapter);
//                }
//                 if (video.isChecked()&&image.isChecked()){
//                     folds.clear();
//                     folds = getPicturePaths();
//                     folderAdapter = new PictureAdapter(folds,Gallery.this,Gallery.this);
//                     GridLayoutManager gridLayoutManager=new GridLayoutManager(Gallery.this,4);
//                     gridLayoutManager.setOrientation(recyclerView.VERTICAL);
//                     recyclerView.setLayoutManager(gridLayoutManager);
//                     recyclerView.setAdapter(folderAdapter);
//
//                     folds = getVideoptath();
//
//                    // folderAdapter = new PictureAdapter(folds,Gallery.this,Gallery.this);
//                     //GridLayoutManager gridLayoutManager1=new GridLayoutManager(Gallery.this,4);
//                     //gridLayoutManager.setOrientation(recyclerView.VERTICAL);
//                    // recyclerView.setLayoutManager(gridLayoutManager);
//                     recyclerView.setAdapter(folderAdapter);
//                 }
//            }
//        });
//        negative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.hide();
//            }
//        });
//    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.sort) {
//            showdialogue();
//        }
//        else if (id == R.id.increase) {
//            count++;
////            if (counter==0) {
////                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4 + count);
////                gridLayoutManager.setOrientation(recyclerView.VERTICAL);
////                recyclerView.setLayoutManager(gridLayoutManager);
////                recyclerView.setAdapter(folderAdapter);
////            }
//
////            else {
////                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4 + count-counter);
////                gridLayoutManager.setOrientation(recyclerView.VERTICAL);
////                recyclerView.setLayoutManager(gridLayoutManager);
////                recyclerView.setAdapter(folderAdapter);
////            }
//        }
//        else if (id == R.id.decrease) {
//            counter++;
////            if (count==0) {
////                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4 - counter);
////                gridLayoutManager.setOrientation(recyclerView.VERTICAL);
////                recyclerView.setLayoutManager(gridLayoutManager);
////                recyclerView.setAdapter(folderAdapter);
////            }
////            else   {
////                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4 - counter+count);
////                gridLayoutManager.setOrientation(recyclerView.VERTICAL);
////                recyclerView.setLayoutManager(gridLayoutManager);
////                recyclerView.setAdapter(folderAdapter);
////
////            }
//        }
//        else if (id == R.id.new_folder) {
//        }
//        else if (id == R.id.Settings) {
//            Intent settings = new Intent(this, Setting.class);
//            startActivity(settings);
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    //    private ArrayList<ImageFolder> getPicturePaths() {
//        ArrayList<String> picPaths = new ArrayList<>();
//        Uri allImagesuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
//                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID,MediaStore.Images.Media.DATE_MODIFIED};
//        Cursor cursor = this.getContentResolver().query(allImagesuri, projection, null, null, null);
//        try {
//            if (cursor != null) {
//                cursor.moveToFirst();
//            }
//            do {
//                ImageFolder folds = new ImageFolder();
//                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
//                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
//                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//                String Date = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED));
//
//
//                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder + "/"));
//                folderpaths = folderpaths + folder + "/";
//                if (!picPaths.contains(folderpaths)) {
//                    picPaths.add(folderpaths);
//
//                    folds.setPath(folderpaths);
//                    folds.setFolderName(folder);
//                    folds.setFirstPic(datapath);
//                    folds.setMedia_picker(false);
//                    folds.addpics();
//                    folds.setDate(Date);
//                    picFolders.add(folds);
//                } else {
//                    for (int i = 0; i < picFolders.size(); i++) {
//                        if (picFolders.get(i).getPath().equals(folderpaths)) {
//                            picFolders.get(i).setFirstPic(datapath);
//                            picFolders.get(i).addpics();
//                        }
//                    }
//                }
//            } while (cursor.moveToNext());
//            cursor.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < picFolders.size(); i++) {
//            Log.d("picture folders", picFolders.get(i).getFolderName() + " and path = " + picFolders.get(i).getPath() + " " + picFolders.get(i).getNumberOfPics());
//        }
//        return picFolders;
//
//    }
    @Override
    public void onPicClicked(PicHolder holder, int position, ArrayList<PictureFacer> pics) {

    }

    @Override
    public void onPicClicked_url(PicHolder holder, int position, ArrayList<PictureFacer> pics, String path) {

    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName) {

    }

    @Override
    public void onPicClicked_media(String pictureFolderPath, String folderName, boolean media_picker) {
        Intent move = new Intent(this, Image_Display.class);
        move.putExtra("folderPath", pictureFolderPath);
        move.putExtra("folderName", folderName);
        move.putExtra("media_picker", media_picker);
        startActivity(move);
    }


    //    private ArrayList<ImageFolder> getVideoptath(){
//        ArrayList<String> picPaths = new ArrayList<>();
//        Uri allImagesuri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME,
//                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media.BUCKET_ID, MediaStore.Video.Media.DATE_MODIFIED};
//        Cursor cursor = this.getContentResolver().query(allImagesuri, projection, null, null, null);
//        try {
//            if (cursor != null) {
//                cursor.moveToFirst();
//            }
//            do{
//                ImageFolder folds = new ImageFolder();
//                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
//                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
//                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
//                String Date = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));
//
//                //String folderpaths =  datapath.replace(name,"");
//                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder+"/"));
//                folderpaths = folderpaths+folder+"/";
//                if (!picPaths.contains(folderpaths)) {
//                    picPaths.add(folderpaths);
//
//                    folds.setPath(folderpaths);
//                    folds.setFolderName(folder);
//                    folds.setFirstPic(datapath);
//                    folds.setMedia_picker(true);
//                    folds.setDate(Date);
//                    folds.addpics();
//                    picFolders.add(folds);
//                }else{
//                    for(int i = 0;i<picFolders.size();i++){
//                        if(picFolders.get(i).getPath().equals(folderpaths)){
//                            picFolders.get(i).setFirstPic(datapath);
//                            picFolders.get(i).addpics();
//                        }
//                    }
//                }
//            }while(cursor.moveToNext());
//            cursor.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for(int i = 0;i < picFolders.size();i++){
//            Log.d("picture folders",picFolders.get(i).getFolderName()+" and path = "+picFolders.get(i).getPath()+" "+picFolders.get(i).getNumberOfPics());
//        }
//
//
//        return picFolders;
//    }
    public void showdialogue() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.sotring_selection, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        RadioGroup rdo_grp = alertDialog.findViewById(R.id.rdo_grp);

        rdo_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                alertDialog.hide();
                switch (checkedId) {

                    case R.id.rdo_name:
                        name_filter = true;
                        Date_filter = false;
                        Size_default = false;
                        ontabset();
                        break;
                    case R.id.rdo_date:
                        name_filter = false;
                        Date_filter = true;
                        Size_default = false;
                        ontabset();
                        break;

                    case R.id.rdo_size:
                        name_filter = false;
                        Date_filter = false;
                        Size_default = true;
                        ontabset();
                        break;
                }


            }
        });

    }

    public void ontabset() {

        Bundle bundle = new Bundle();
        bundle.putBoolean("name", name_filter);
        bundle.putBoolean("date", Date_filter);
        bundle.putBoolean("size", Size_default);
        fragment.setArguments(bundle);
        fragment1.setArguments(bundle);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //fragment = new Image_Fragment();
                // Video_Fragment fragment1 = new Video_Fragment();
                if (tabLayout.getSelectedTabPosition() == 0) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frm, fragment)
                            .commit();

                }
                if (tabLayout.getSelectedTabPosition() == 1) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frm, fragment1)
                            .commit();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }
}