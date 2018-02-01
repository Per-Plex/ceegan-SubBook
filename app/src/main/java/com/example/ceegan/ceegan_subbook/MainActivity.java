package com.example.ceegan.ceegan_subbook;

import android.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AddSubscription.AddSubscriptionListener, DisplaySubscription.EditSubscriptionListener{

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
        final ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.addSub);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubscription dialog = new AddSubscription();
                dialog.show(getFragmentManager(), "Add Subscription");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle args = new Bundle();
                args.putString("name", subscriptions.get(i).getName());
                args.putString("date", subscriptions.get(i).getDate());
                args.putString("cost", String.valueOf(subscriptions.get(i).getCost()));
                args.putString("comment", subscriptions.get(i).getComment());
                args.putInt("position", i);

                DisplaySubscription dialog = new DisplaySubscription();
                dialog.setArguments(args);
                dialog.show(getFragmentManager(), "Edit Subscription");
            }
        });
        updateTotal();
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
        updateTotal();
    }

    @Override
    public void onEditPositive(DialogFragment dialogFragment){
        subscription temp = subscriptions.get(dialogFragment.getArguments().getInt("position"));
        EditText name = dialogFragment.getDialog().findViewById(R.id.subscription_name);
        EditText data = dialogFragment.getDialog().findViewById(R.id.subscription_date);
        EditText cost = dialogFragment.getDialog().findViewById(R.id.subscription_cost);
        EditText comment = dialogFragment.getDialog().findViewById(R.id.subscription_comment);

        temp.setName(name.getText().toString());
        temp.setDate(data.getText().toString());
        temp.setCost(Float.valueOf(cost.getText().toString().replace("$", "")));
        temp.setComment(comment.getText().toString());

        adapter.notifyDataSetChanged();
        updateTotal();
    }

    public void updateTotal(){
        TextView total = findViewById(R.id.total);
        float sum = 0;

        for (subscription sub: subscriptions) {
            sum += sub.getCost();
        }

        total.setText("Total: $"+ String.valueOf(sum));
    }
}
