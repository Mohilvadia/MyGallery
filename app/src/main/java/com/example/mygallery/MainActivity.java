package com.example.mygallery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkpermission();

    }
    public void checkpermission(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            //check permission is already granted or not
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            {
                //take runtime permission from user
                String[] PermissionList = {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(PermissionList,MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
            else {
                startActivity(new Intent(MainActivity.this,Gallery.class));
                finish();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check call phone permission is granted by user or not
        if(grantResults[0]!=PackageManager.PERMISSION_GRANTED) {
            finish();
        }
        else {
            startActivity(new Intent(MainActivity.this,Gallery.class));
            finish();
        }
    }



}