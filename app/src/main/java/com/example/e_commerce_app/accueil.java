package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

public class accueil extends AppCompatActivity {
    private ImageView imageview;
    private GridView simpleList;
    ArrayList courslist=new ArrayList<>();
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        imageview = findViewById(R.id.imageView4);
        simpleList = (GridView) findViewById(R.id.grid_view);
        courslist.add(new item("Programmation",R.drawable.programmation));
        courslist.add(new item("Intelligence Artificielle",R.drawable.intelligenceartificielle));
        courslist.add(new item("Developpement Mobile",R.drawable.developpementmobile));
        MyAdapter myAdapter=new MyAdapter(this,R.layout.grid_view,courslist);
        simpleList.setAdapter(myAdapter);


    }

}
