package com.example.mygallery.Pojo;

public class ImageFolder {

    private  String path;
    private  String FolderName;
    private int numberOfPics = 0;
    private String firstPic;
    private String date;
    private boolean media_picker;

    public ImageFolder(){

    }

    public ImageFolder(String path, String folderName,boolean media_picker,String date) {
        this.path = path;
        FolderName = folderName;
        this.media_picker=media_picker;
        this.date=date;
    }

    public boolean isMedia_picker() {
        return media_picker;
    }

    public void setMedia_picker(boolean media_picker) {
        this.media_picker = media_picker;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public void addpics(){
        this.numberOfPics++;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }
}
