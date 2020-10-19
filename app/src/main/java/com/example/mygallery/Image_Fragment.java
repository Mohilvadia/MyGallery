package com.example.mygallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygallery.Adapter.PictureAdapter;
import com.example.mygallery.Pojo.ImageFolder;
import com.example.mygallery.Pojo.PictureFacer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Image_Fragment extends Fragment implements ItemClickListner {

    RecyclerView folderRecycler;
    ArrayList<ImageFolder> picFolders=new ArrayList<>();
    TextView filter,settings,increase,decrease;
    FloatingActionButton floatingActionButton;
    int a=0;
    ArrayList<ImageFolder> folds;
    private ItemClickListner picListerner;
    int count=0,counter=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_image_, container, false);

        floatingActionButton=view.findViewById(R.id.floatingmain);
        settings=view.findViewById(R.id.Settings);
        filter=view.findViewById(R.id.filter);
        increase=view.findViewById(R.id.increase);
        decrease=view.findViewById(R.id.decrease);
        folderRecycler = view.findViewById(R.id.rec_image);
        initview();
        setevent();

        return view;
    }

    private void initview() {
        folderRecycler.hasFixedSize();

        folds = getPicturePaths();
        // Log.e("error", String.valueOf(Prefrence.getInstance(getContext()).getNamefilter()));
        RecyclerView.Adapter folderAdapter = new PictureAdapter(folds, getActivity(), this);
        folderRecycler.setAdapter(folderAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4 +count - counter);
        if (Prefrence.getInstance(getActivity()).getHorizontalScroll()){
            gridLayoutManager.setOrientation(folderRecycler.HORIZONTAL);

        }
        else {
            gridLayoutManager.setOrientation(folderRecycler.VERTICAL);

        }
        folderRecycler.setLayoutManager(gridLayoutManager);

    }

    private void setevent() {
         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 a++;
                 if (a%2==0){
                     filter.setVisibility(View.INVISIBLE);
                     settings.setVisibility(View.INVISIBLE);
                     increase.setVisibility(View.INVISIBLE);
                     decrease.setVisibility(View.INVISIBLE);
                 }
                 else {
                     filter.setVisibility(View.VISIBLE);
                     settings.setVisibility(View.VISIBLE);
                     increase.setVisibility(View.VISIBLE);
                     decrease.setVisibility(View.VISIBLE);
                 }

             }
         });
         filter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 filter.setVisibility(View.INVISIBLE);
                 settings.setVisibility(View.INVISIBLE);
                 increase.setVisibility(View.INVISIBLE);
                 decrease.setVisibility(View.INVISIBLE);
                 showdialogue();
             }
         });
         settings.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 filter.setVisibility(View.INVISIBLE);
                 settings.setVisibility(View.INVISIBLE);
                 increase.setVisibility(View.INVISIBLE);
                 decrease.setVisibility(View.INVISIBLE);
                 startActivity(new Intent(getContext(),Setting.class));
             }
         });
         increase.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 count++;
                // Prefrence.getInstance(getContext()).
                 filter.setVisibility(View.INVISIBLE);
                 settings.setVisibility(View.INVISIBLE);
                 increase.setVisibility(View.INVISIBLE);
                 decrease.setVisibility(View.INVISIBLE);
                 folderRecycler.hasFixedSize();
                if (count<4) {
                    folds = getPicturePaths();
                    RecyclerView.Adapter folderAdapter = new PictureAdapter(folds, getActivity(), picListerner);
                    folderRecycler.setAdapter(folderAdapter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4 + count - counter);
                    if (Prefrence.getInstance(getActivity()).getHorizontalScroll()){
                        gridLayoutManager.setOrientation(folderRecycler.HORIZONTAL);

                    }
                    else {
                        gridLayoutManager.setOrientation(folderRecycler.VERTICAL);

                    }
                    folderRecycler.setLayoutManager(gridLayoutManager);
                }
             }
         });
         decrease.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 filter.setVisibility(View.INVISIBLE);
                 settings.setVisibility(View.INVISIBLE);
                 increase.setVisibility(View.INVISIBLE);
                 decrease.setVisibility(View.INVISIBLE);
                 counter++;
                 folderRecycler.hasFixedSize();
                 folds = getPicturePaths();
                 RecyclerView.Adapter folderAdapter = new PictureAdapter(folds, getActivity(), picListerner);
                 folderRecycler.setAdapter(folderAdapter);
                 //ImageView imageView=getView().findViewById(R.id.folderpic);
                 GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4 +count - counter);
                 if (Prefrence.getInstance(getActivity()).getHorizontalScroll()){
                     gridLayoutManager.setOrientation(folderRecycler.HORIZONTAL);

                 }
                 else {
                     gridLayoutManager.setOrientation(folderRecycler.VERTICAL);

                 }
                 folderRecycler.setLayoutManager(gridLayoutManager);
             }
         });
    }

    private ArrayList<ImageFolder> getPicturePaths() {

        ArrayList<String> picPaths = new ArrayList<>();

        Uri allImagesuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID,MediaStore.Images.Media.DATE_MODIFIED};

        Cursor cursor = getActivity().getContentResolver().query(allImagesuri, projection, null, null, null);

            try {
                if (cursor != null) {
                    cursor.moveToFirst();
                }

            do {
                ImageFolder folds = new ImageFolder();
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                String Date = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED));

                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder + "/"));
                folderpaths = folderpaths + folder + "/";
                if (!picPaths.contains(folderpaths)) {
                    picPaths.add(folderpaths);
                    folds.setPath(folderpaths);
                    folds.setFolderName(folder);
                    folds.setFirstPic(datapath);
                    folds.setMedia_picker(false);
                    folds.addpics();
                    folds.setDate(Date);
                    picFolders.add(folds);
                }
                else {
                    for (int i = 0; i < picFolders.size(); i++) {
                        if (picFolders.get(i).getPath().equals(folderpaths)) {
                            picFolders.get(i).setFirstPic(datapath);
                            picFolders.get(i).addpics();
                        }
                    }
                }
            }
            while (cursor.moveToNext());
            cursor.close();
            }
            catch (Exception e) {
            e.printStackTrace();
            }
        for (int i = 0; i < picFolders.size(); i++) {
            Log.d("picture folders", picFolders.get(i).getFolderName() + " and path = " + picFolders.get(i).getPath() + " " + picFolders.get(i).getNumberOfPics());
         }
        return picFolders;

    }

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
        Intent move = new Intent(getContext(),Image_Display.class);
        move.putExtra("folderPath",pictureFolderPath);
        move.putExtra("folderName",folderName);
        move.putExtra("media_picker",media_picker);
        startActivity(move);

    }

    public void showdialogue() {
        ViewGroup viewGroup = getView().findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.sotring_selection, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                        folds.clear();
                        folds = getPicturePaths();
                 //       Log.e("error", String.valueOf(Prefrence.getInstance(getContext()).getNamefilter()));
                        Collections.sort(folds, new Comparator<ImageFolder>() {
                            @Override
                            public int compare(ImageFolder o1, ImageFolder o2) {
                                return o1.getFolderName().compareTo(o2.getFolderName());
                            }
                        });
                        RecyclerView.Adapter folderAdapter = new PictureAdapter(folds,getActivity(),picListerner);
                        folderRecycler.setAdapter(folderAdapter);
                        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),4+count-counter);
                        gridLayoutManager.setOrientation(folderRecycler.VERTICAL);
                        folderRecycler.setLayoutManager(gridLayoutManager);
                        break;

                    case R.id.rdo_date:
                        folds.clear();
                        folds=getPicturePaths();
                     //   Log.e("error", String.valueOf(Prefrence.getInstance(getContext()).getNamefilter()));
                        Collections.sort(folds, new Comparator<ImageFolder>() {
                            @Override
                            public int compare(ImageFolder o1, ImageFolder o2) {
                                return o1.getDate().compareTo(o2.getDate());

                            }

                        });
                        RecyclerView.Adapter folderAdapter1 = new PictureAdapter(folds,getActivity(),picListerner);
                        folderRecycler.setAdapter(folderAdapter1);
                        GridLayoutManager gridLayoutManager1=new GridLayoutManager(getContext(),4+count-counter);
                        gridLayoutManager1.setOrientation(folderRecycler.VERTICAL);
                        folderRecycler.setLayoutManager(gridLayoutManager1);
                        break;

                    case R.id.rdo_size:
                        break;
                }

            }
        });

    }

    @Override
    public void onResume() {
//        folds.clear();
//        folds = getPicturePaths();
//        RecyclerView.Adapter folderAdapter = new PictureAdapter(folds, getActivity(), picListerner);
//        folderRecycler.setAdapter(folderAdapter);
//        //ImageView imageView=getView().findViewById(R.id.folderpic);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4 +count - counter);
//        if (Prefrence.getInstance(getActivity()).getHorizontalScroll()){
//            gridLayoutManager.setOrientation(folderRecycler.HORIZONTAL);
//
//        }
//        else {
//            gridLayoutManager.setOrientation(folderRecycler.VERTICAL);
//
//        }
//        folderRecycler.setLayoutManager(gridLayoutManager);

        super.onResume();
    }


}