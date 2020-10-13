package com.example.e_commerce_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        //databaseReference= FirebaseDatabase.getInstance().getReference("e-commerce-7fbb3");
        courslist.add(new item("AFRICAN FIREFINCH",R.drawable.rectangle_1));
        courslist.add(new item("ALBATROSS",R.drawable.rectangle_1));
        courslist.add(new item("BALD EAGLE",R.drawable.rectangle_1));
        MyAdapter myAdapter=new MyAdapter(this,R.layout.grid_view,courslist);
        simpleList.setAdapter(myAdapter);
        //getImageData();
    }
    /*private void getImageData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di:dataSnapshot.getChildren()){
                    item cours=di.getValue(item.class);
                    courslist.add(cours);
                }
                MyAdapter myAdapter=new MyAdapter(getApplicationContext(),R.layout.grid_view, courslist);
                simpleList.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}