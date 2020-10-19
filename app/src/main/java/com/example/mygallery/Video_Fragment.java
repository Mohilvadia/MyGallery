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
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mygallery.Adapter.PictureAdapter;
import com.example.mygallery.Pojo.ImageFolder;
import com.example.mygallery.Pojo.PictureFacer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Video_Fragment extends Fragment  implements ItemClickListner{
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ArrayList<ImageFolder> picFolders=new ArrayList<>();
    RecyclerView folderRecycler;
    TextView settings,filter,increase,decrease;
    FloatingActionButton floatingActionButton;
    ItemClickListner picListerner;
    ArrayList<ImageFolder> folds;
    int a=0;
    private int count;
    private int counter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_video_, container, false);
        floatingActionButton=view.findViewById(R.id.floatingmain);
        settings=view.findViewById(R.id.Settings);
        filter=view.findViewById(R.id.filter);
        increase=view.findViewById(R.id.increase);
        decrease=view.findViewById(R.id.decrease);
        folderRecycler = view.findViewById(R.id.rec_video);
        setevent();
        folderRecycler.hasFixedSize();
        folds = getVideoptath();


            RecyclerView.Adapter folderAdapter = new PictureAdapter(folds,getActivity(),this);
            folderRecycler.setAdapter(folderAdapter);
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),4);
            if (Prefrence.getInstance(getActivity()).getHorizontalScroll()){
                gridLayoutManager.setOrientation(folderRecycler.HORIZONTAL);

            }
            else {
                gridLayoutManager.setOrientation(folderRecycler.VERTICAL);

            }
            folderRecycler.setLayoutManager(gridLayoutManager);

        return view;
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
                    folds = getVideoptath();
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
                folds = getVideoptath();
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

    private void showdialogue() {
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
                        folds = getVideoptath();
                        Collections.sort(folds, new Comparator<ImageFolder>() {
                            @Override
                            public int compare(ImageFolder o1, ImageFolder o2) {
                                return o1.getFolderName().compareTo(o2.getFolderName());

                            }

                        });
                        RecyclerView.Adapter folderAdapter = new PictureAdapter(folds,getActivity(),picListerner);

                        folderRecycler.setAdapter(folderAdapter);

                        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),4+Gallery.count-Gallery.counter);

                        if (Prefrence.getInstance(getActivity()).getHorizontalScroll()){
                            gridLayoutManager.setOrientation(folderRecycler.HORIZONTAL);

                        }
                        else {
                            gridLayoutManager.setOrientation(folderRecycler.VERTICAL);

                        }
                        folderRecycler.setLayoutManager(gridLayoutManager);
                        break;
                    case R.id.rdo_date:
                        folds.clear();
                        folds=getVideoptath();
                        Collections.sort(folds, new Comparator<ImageFolder>() {
                            @Override
                            public int compare(ImageFolder o1, ImageFolder o2) {
                                return o1.getDate().compareTo(o2.getDate());

                            }

                        });
                        RecyclerView.Adapter folderAdapter1 = new PictureAdapter(folds,getActivity(),picListerner);

                        folderRecycler.setAdapter(folderAdapter1);

                        GridLayoutManager gridLayoutManager1=new GridLayoutManager(getContext(),4+Gallery.count-Gallery.counter);

                        if (Prefrence.getInstance(getActivity()).getHorizontalScroll()){
                            gridLayoutManager1.setOrientation(folderRecycler.HORIZONTAL);

                        }
                        else {
                            gridLayoutManager1.setOrientation(folderRecycler.VERTICAL);

                        }
                        folderRecycler.setLayoutManager(gridLayoutManager1);
                        break;

                    case R.id.rdo_size:
                        break;
                }

            }
        });
    }

    private ArrayList<ImageFolder> getVideoptath(){
        ArrayList<String> picPaths = new ArrayList<>();
        Uri allImagesuri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media.BUCKET_ID, MediaStore.Video.Media.DATE_MODIFIED};
        Cursor cursor = getActivity().getContentResolver().query(allImagesuri, projection, null, null, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            do{
                ImageFolder folds = new ImageFolder();
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                String Date = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));

                //String folderpaths =  datapath.replace(name,"");
                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder+"/"));
                folderpaths = folderpaths+folder+"/";
                if (!picPaths.contains(folderpaths)) {
                    picPaths.add(folderpaths);

                    folds.setPath(folderpaths);
                    folds.setFolderName(folder);
                    folds.setFirstPic(datapath);
                    folds.setMedia_picker(true);
                    folds.setDate(Date);
                    folds.addpics();
                    picFolders.add(folds);
                }else{
                    for(int i = 0;i<picFolders.size();i++){
                        if(picFolders.get(i).getPath().equals(folderpaths)){
                            picFolders.get(i).setFirstPic(datapath);
                            picFolders.get(i).addpics();
                        }
                    }
                }
            }while(cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0;i < picFolders.size();i++){
            Log.d("picture folders",picFolders.get(i).getFolderName()+" and path = "+picFolders.get(i).getPath()+" "+picFolders.get(i).getNumberOfPics());
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

    @Override
    public void onResume() {
//        folds.clear();
//        folds=getVideoptath();
//        RecyclerView.Adapter folderAdapter = new PictureAdapter(folds, getActivity(), picListerner);
//        folderRecycler.setAdapter(folderAdapter);
//        //ImageView imageView=getView().findViewById(R.id.folderpic);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4 );
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