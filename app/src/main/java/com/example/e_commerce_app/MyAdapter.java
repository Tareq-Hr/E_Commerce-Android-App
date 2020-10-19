package com.example.e_commerce_app;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    ArrayList<item> coursList = new ArrayList<item>();
    public MyAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        coursList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view, null);
        final TextView textView = (TextView) v.findViewById(R.id.textView15);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView4);
        textView.setText(coursList.get(position).getcoursName());
        //imageView.setImageResource(coursList.size());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), cours.class);
                intent.putExtra("cour_name", String.valueOf(coursList.get(position).getcoursName()));
                getContext().startActivity(intent);
            }
        });
        return v;

    }

}
