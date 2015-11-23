package com.example.gulin_000.restaurantegulinelli;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gulin_000 on 19/11/2015.
 */
public class ListCell extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] item;
    private final Integer[] imageId;

    public ListCell(Activity context, String[] item, Integer[] imageId){
        super(context, R.layout.list_cell, item);
        this.context = context;
        this.item = item;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell, null, true);
        TextView txtTitle = (TextView)rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.img);
        txtTitle.setText(item[position]);
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}
