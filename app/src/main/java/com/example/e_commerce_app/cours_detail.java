package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class cours_detail extends AppCompatActivity {
    StorageReference StorageRef;
Button confirm;
FloatingActionButton add_panier;
    String cours_nom,uri;
    TextView prix_cour,nom_cour,description,categorie;
    ImageView image;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours_detail);
        cours_nom = getIntent().getStringExtra("Java");
        nom_cour = findViewById(R.id.nom);
        prix_cour = findViewById(R.id.prix_cour);
        image = findViewById(R.id.img);
        description = findViewById(R.id.description);
        categorie = findViewById(R.id.categorie);
        confirm = findViewById(R.id.confirmer);

        FirebaseDatabase.getInstance().getReference().child("Programmation").child("Java").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
                    nom_cour.setText(value.get("nom_cours").toString());
                    prix_cour.setText(value.get("prix").toString()+" Dh");
                    description.setText(value.get("description").toString());
                    categorie.setText("Programmation");
                    Log.d("Firebase", "Value is: " +nom_cour+prix_cour+description);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        StorageRef = FirebaseStorage.getInstance().getReference().child("/Programmation/Java/java.jpg");

        try {
            final File localFile = File.createTempFile("temp","jpg");
            StorageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    image.setImageBitmap(bitmap);
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);

                startActivity(intent);
            }
        });

    }
}