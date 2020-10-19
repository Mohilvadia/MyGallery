package com.example.mygallery;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefrence {

    private static Prefrence preferences;
    private SharedPreferences.Editor writer;

    private Context ctx;
    private static final String SHARED_PREF_NAME = "sharedprefrence";
    private static final String HIDE_BOOL = "hidebool";
    private static final String AUTO_PLAY = "autoplay";
    private static final String CROP_THUMBNAIL = "cropthumbnail";
    private static final String DURATION = "duration";
    private static final String LOOP = "loop";
    private static final String HORIZONTAL_SCROLL = "horizontalscroll";
    private Prefrence(Context context) {
        ctx = context;
    }

    public static synchronized Prefrence getInstance(Context context) {
        if (preferences == null) {
            preferences = new Prefrence(context);
        }
        return preferences;
    }

  public void hidefolder(boolean hide){
      SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putBoolean(HIDE_BOOL,hide);
      editor.apply();
  }
  public boolean getHide_folder(){
        return ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getBoolean(HIDE_BOOL,false);

  }

  public void setAutoPlay(boolean autoplay){
      SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putBoolean(AUTO_PLAY,autoplay);
      editor.apply();

  }
  public boolean getAutoPlay(){
        return ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getBoolean(AUTO_PLAY,false);
  }

  public void setCropThumb(boolean crop){
      SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putBoolean(CROP_THUMBNAIL,crop);
      editor.apply();
  }

  public boolean getCropThumb(){
        return ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getBoolean(CROP_THUMBNAIL,false);
  }

  public void setDuration(boolean duration){
      SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putBoolean(DURATION,duration);
      editor.apply();

  }
  public  boolean getDuration(){
        return ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getBoolean(DURATION,false);
  }

  public void setHorizontalScroll(boolean duration){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(HORIZONTAL_SCROLL,duration);
        editor.apply();

  }
  public  boolean getHorizontalScroll(){
        return ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getBoolean(HORIZONTAL_SCROLL,false);

  }

    public void setLoop(boolean loop){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOOP,loop);
        editor.apply();

    }
    public  boolean getLoop(){
        return ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getBoolean(LOOP,false);

    }



}
