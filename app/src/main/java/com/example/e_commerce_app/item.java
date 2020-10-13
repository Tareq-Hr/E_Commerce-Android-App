package com.example.e_commerce_app;

public class item {

    String coursListName;
   int imageurl;
    public item(){}

    public item(String coursName, int imageurl)
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
