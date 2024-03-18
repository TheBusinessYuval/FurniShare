package com.example.furnishare;

import com.google.firebase.auth.FirebaseAuth;

public class DataClass {

    private String ObjectTitle;
    private String ObjectDesc;
    private String ObjectState;
    private String ObjectImage;
    private String key;
    private String Uid;


    public String getUid (){return Uid;}
    public void setUid(String Uid){
        this.Uid = Uid;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getObjectTitle (){return ObjectTitle;}
    public void setObjectTitle(String ObjectTitle){
        this.ObjectTitle = ObjectTitle;
    }
    public String getObjectDesc (){return ObjectDesc;}
    public void setObjectDesc(String ObjectDesc){
        this.ObjectDesc = ObjectDesc;
    }
    public String getObjectState (){return ObjectState;}
    public void setObjectState(String ObjectStae){
        this.ObjectState = ObjectState;
    }
    public String getObjectImage (){return ObjectImage;}
    public void setObjectImage(String ObjectImage){
        this.ObjectImage = ObjectImage;
    }


    public DataClass(String ObjectTitle, String ObjectDesc, String ObjectState, String ObjectImage) {
        this.Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        this.ObjectTitle = ObjectTitle;
        this.ObjectDesc = ObjectDesc;
        this.ObjectState = ObjectState;
        this.ObjectImage = ObjectImage;
    }
    public DataClass(String ObjectTitle, String ObjectDesc, String ObjectImage, String ObjectState , String Uid){
        this.ObjectTitle = ObjectTitle;
        this.ObjectDesc = ObjectDesc;
        this.ObjectState = ObjectImage;
        this.ObjectImage = ObjectState;
        this.Uid = Uid;

    }
    public DataClass(){}

    public String Debug(){
        return "uid: " +this.Uid +" title: " +this.ObjectTitle +" Desc: " +this.ObjectDesc +" state: " +this.ObjectState+" image: " +this.ObjectImage;
    }
}
