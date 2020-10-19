package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class cours extends AppCompatActivity {
CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
        card=findViewById(R.id.card);


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), cours_detail.class);
                intent.putExtra("cour_name",getIntent().getStringExtra("cour_name"));
                 startActivity(intent);
            }
        });
    }
}