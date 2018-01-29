package com.example.ceegan.ceegan_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ceegan on 2018-01-28.
 */

public class myAdapter extends ArrayAdapter<subscription> {

    ArrayList<subscription> subscriptions = new ArrayList<>();

    public myAdapter(Context context, int textViewResourceID, ArrayList<subscription> objects){
        super(context, textViewResourceID, objects);
        subscriptions = objects;
    }

    @Override
    public int getCount(){
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_layout, null);
        TextView title = v.findViewById(R.id.title);
        TextView date = v.findViewById(R.id.date);
        TextView cost = v.findViewById(R.id.cost);
        title.setText(subscriptions.get(position).getName());
        date.setText(subscriptions.get(position).getDate());
        cost.setText("$"+String.valueOf(subscriptions.get(position).getCost()));
        return v;
    }
}