package com.example.mygallery.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

public class PictureFacer implements Parcelable {

    private String picturName;
    private String picturePath;
    private  String pictureSize;
    private  String imageUri;
    private Boolean selected = false;

    public PictureFacer(){

    }

    public PictureFacer(String picturName, String picturePath, String pictureSize, String imageUri) {
        this.picturName = picturName;
        this.picturePath = picturePath;
        this.pictureSize = pictureSize;
        this.imageUri = imageUri;
    }


    public String getPicturName() {
        return picturName;
    }

    public void setPicturName(String picturName) {
        this.picturName = picturName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(String pictureSize) {
        this.pictureSize = pictureSize;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.picturName);
        dest.writeString(this.picturePath);
        dest.writeString(this.pictureSize);
        dest.writeString(this.imageUri);
        dest.writeValue(this.selected);
    }

    protected PictureFacer(Parcel in) {
        this.picturName = in.readString();
        this.picturePath = in.readString();
        this.pictureSize = in.readString();
        this.imageUri = in.readString();
        this.selected = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PictureFacer> CREATOR = new Parcelable.Creator<PictureFacer>() {
        @Override
        public PictureFacer createFromParcel(Parcel source) {
            return new PictureFacer(source);
        }

        @Override
        public PictureFacer[] newArray(int size) {
            return new PictureFacer[size];
        }
    };
}


