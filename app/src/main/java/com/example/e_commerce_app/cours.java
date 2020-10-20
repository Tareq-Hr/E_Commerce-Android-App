package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class cours extends AppCompatActivity {

    private ImageView imageview;
    private TextView description;
    private StorageReference StorageRef;
    private GridView simpleList;
    ArrayList courslist=new ArrayList<Cours_Items>();
    ListeCoursAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);

        imageview = findViewById(R.id.cour_image);
        description = findViewById(R.id.description);
        simpleList = (GridView) findViewById(R.id.liste_cours);

        //courslist.add(new Cours_Items("Java",R.drawable.ic_master_card));
 /*       courslist.add(new Cours_Items("Python",R.drawable.rectangle_1));
        courslist.add(new Cours_Items("SQL",R.drawable.rectangle_1));

 */



        //Firebase Linking
        Query query = FirebaseDatabase.getInstance().getReference().child("Programmation");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
                    //nom_cour.setText(value.get("nom_cours").toString());
                    //prix_cour.setText(value.get("prix").toString()+" Dh");
                    courslist.add(new Cours_Items(value.get("description").toString(), R.drawable.python));
                    Log.d("Firebase", "Value is: " +courslist);
                    myAdapter = new ListeCoursAdapter(getApplicationContext(),R.layout.liste_cours_grid,courslist);
                    simpleList.setAdapter(myAdapter);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* StorageRef = FirebaseStorage.getInstance().getReference().child("/Programmation/Java/java.jpg");

        try {
            final File localFile = File.createTempFile("temp","jpg");
            StorageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageview.setImageBitmap(bitmap);
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }

        */
        //

    }
}