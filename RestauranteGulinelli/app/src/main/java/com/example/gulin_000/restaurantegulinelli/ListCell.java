package com.example.gulin_000.restaurantegulinelli;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by gulin_000 on 19/11/2015.
 */
public class ListCell extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] nomeItem;
    private final Double[] valorItem;
    private final Integer[] imageId;

    public ListCell(Activity context, String[] nomeItem, Double[] valorItem, Integer[] imageId){
        super(context, R.layout.list_cell, nomeItem);
        this.context = context;
        this.nomeItem = nomeItem;
        this.valorItem = valorItem;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell, null, true);

        TextView txtNome = (TextView)rowView.findViewById(R.id.txtNome);
        TextView txtValor = (TextView)rowView.findViewById(R.id.txtValor);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.img);

        txtNome.setText(nomeItem[position]);
        txtValor.setText("R$ " + String.format("%.2f", valorItem[position]));
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}
