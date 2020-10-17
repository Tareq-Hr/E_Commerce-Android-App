package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class PanierActivity extends AppCompatActivity {

    private RecyclerView panier_list;
    private PanierAdapter panierAdapter;
    private Panier p,p2;
    private ArrayList<Panier> panier;
    private TextView total;
    private float sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        panier_list = findViewById(R.id.recyclerView);
        total = findViewById(R.id.textView5);


        p = new Panier();
        p.setNom_cours("Programmation fonctionnelle en Python");
        p.setPrix((float) 10.55);
        p2= new Panier();
        p2.setNom_cours("Programmation Orienté Objet Java");
        p2.setPrix((float) 5.55);

        panier = new ArrayList<Panier>();
        panier.add(p);
        panier.add(p2);
        panier.add(p);
        panier.add(p2);
        panier.add(p);
        panier.add(p2);
        panier.add(p);
        panier.add(p2);

        //Calculer le montant total du panier
        for (int i=0;i<=panier.size()-1;i++){
            sum = sum+panier.get(i).getPrix();
        }
        total.setText(String.valueOf(" Montant total : "+sum+" $ "));
        ///

        panierAdapter = new PanierAdapter(this, panier);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        panier_list.setLayoutManager(llm);
        panier_list.setAdapter(panierAdapter);

    }
        public void logout(View view){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
    }
}
