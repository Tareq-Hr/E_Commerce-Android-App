package com.example.e_commerce_app;

public class Cours_Items {
    String coursListName;
    int imageurl;
    public Cours_Items(){}

    public Cours_Items(String coursName, int imageurl)
    {
        this.imageurl=imageurl;
        this.coursListName=coursName;
    }
    public String getcoursName()
    {
        return coursListName;
    }
    public int getcoursImage()
    {
        return imageurl;
    }
}
