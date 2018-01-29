package com.example.ceegan.ceegan_subbook;

import android.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AddSubscription.AddSubscriptionListener{

    private ArrayList<subscription> subscriptions = new ArrayList<>();
    private myAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subscriptions.add(new subscription("Netflix", "1997-04-22", 10.99f, "Nibba"));
        subscriptions.add(new subscription("Amazon Prime", "2016-01-01", 9.99f, "Worth"));
        subscriptions.add(new subscription("World of Warcraft", "2010-12-25", 15.99f, "God Tier"));


        adapter = new myAdapter(this, R.layout.activity_main, subscriptions);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.addSub);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubscription dialog = new AddSubscription();
                dialog.show(getFragmentManager(), "Add Subscription");
            }
        });

    }

    @Override
    public void onDialogPositive(DialogFragment dialogFragment){
        EditText name = dialogFragment.getDialog().findViewById(R.id.name);
        EditText data = dialogFragment.getDialog().findViewById(R.id.date);
        EditText cost = dialogFragment.getDialog().findViewById(R.id.cost);
        EditText comment = dialogFragment.getDialog().findViewById(R.id.comment);

        subscriptions.add(new subscription(name.getText().toString(), data.getText().toString(),
                Float.parseFloat(cost.getText().toString()), comment.getText().toString()));

        adapter.notifyDataSetChanged();
    }

}
