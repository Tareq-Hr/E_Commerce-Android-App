package com.example.e_commerce_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.ViewHolder> {
    Context context;
    ArrayList<Panier> panier;

    public PanierAdapter(Context context, ArrayList<Panier> panier){
        this.context = context;
        this.panier = panier;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.panier_recycler,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Panier paniers = panier.get(position);
        holder.nom_cours.setText(paniers.getNom_cours());
        holder.prix.setText(String.valueOf(paniers.getPrix())+" $");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panier.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return panier.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nom_cours;
        TextView prix;
        ImageView image;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_cours = itemView.findViewById(R.id.textView2);
            prix = itemView.findViewById(R.id.textView3);
            image = itemView.findViewById(R.id.imageView2);
            delete = itemView.findViewById(R.id.button2);
        }
    }
}
