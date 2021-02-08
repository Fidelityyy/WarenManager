package com.tabian.saveanddisplaysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Produkt>{

    private ArrayList<Produkt> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtPreis;
        TextView txtAnzahl;
    }

    public CustomAdapter(ArrayList<Produkt> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Produkt Produkt = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtPreis = (TextView) convertView.findViewById(R.id.preis);
            viewHolder.txtAnzahl = (TextView) convertView.findViewById(R.id.anzahl);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.txtName.setText(Produkt.getName());
        viewHolder.txtAnzahl.setText(Integer.toString(Produkt.getAnzahl()));
        viewHolder.txtPreis.setText(Double.toString(Produkt.getPreis()));
        // Return the completed view to render on screen
        return convertView;
    }
}