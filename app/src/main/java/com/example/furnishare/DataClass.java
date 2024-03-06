package com.example.furnishare;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DataClass {

    private String ObjectTitle;
    private String ObjectDesc;
    private String ObjectState;
    private String ObjectImage;
    private String key;
    private FirebaseUser User;

    public FirebaseUser getUser ()
    {
        return User;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return ObjectTitle;
    }

    public String getObjectDesc() {
        return ObjectDesc;
    }

    public String getObjectState() {
        return ObjectState;
    }

    public String getObjectImage() {
        return ObjectImage;
    }

    public DataClass(String ObjectTitle, String ObjectDesc, String ObjectState, String ObjectImage) {
        this.User = FirebaseAuth.getInstance().getCurrentUser();
        this.ObjectTitle = ObjectTitle;
        this.ObjectDesc = ObjectDesc;
        this.ObjectState = ObjectState;
        this.ObjectImage = ObjectImage;
    }
    public DataClass(){

    }
}
