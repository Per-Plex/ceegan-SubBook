package com.example.ceegan.ceegan_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom array adapter for subscriptions
 */

public class myAdapter extends ArrayAdapter<subscription> {

    private ArrayList<subscription> subscriptions = new ArrayList<>();

    /**
     * Constructor for the adapter
     *
     * @param context main activity context
     * @param textViewResourceID the resourece the adapter is handling
     * @param objects the objects the adapter handles
     */
    public myAdapter(Context context, int textViewResourceID, ArrayList<subscription> objects){
        super(context, textViewResourceID, objects);
        subscriptions = objects;
    }

    @Override
    public int getCount(){
        return super.getCount();
    }

    /**
     * Creates the list view, sets all the values, and adds a listener to the delete button
     *
     * @return the view of the main screen
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_layout, null);
        TextView title = v.findViewById(R.id.title);
        TextView date = v.findViewById(R.id.date);
        TextView cost = v.findViewById(R.id.cost);
        title.setText(subscriptions.get(position).getName());
        date.setText(subscriptions.get(position).getDate());
        cost.setText("$"+String.format("%.2f", subscriptions.get(position).getCost()));

        ImageButton imageButton = v.findViewById(R.id.delete);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscriptions.remove(position);
                notifyDataSetChanged();
                ((MainActivity) getContext()).updateTotal();
            }
        });


        return v;
    }
}
