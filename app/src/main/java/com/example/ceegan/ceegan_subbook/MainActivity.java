package com.example.ceegan.ceegan_subbook;

import android.app.DialogFragment;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AddSubscription.AddSubscriptionListener, DisplaySubscription.EditSubscriptionListener{

    private ArrayList<subscription> subscriptions;
    private myAdapter adapter;
    private ListView listView;
    private static final String FILENAME = "subscriptions_list.sav";

    /**
     * Sets the list view, intializes floating action button, and sets the item click listener
     * @param savedInstanceState instance of the program
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        // Sets the floating action button for add subscription
        FloatingActionButton fab = findViewById(R.id.addSub);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubscription thing = new AddSubscription();
                thing.show(getFragmentManager(), "Add subscription");
            }
        });

        // Sets the item click listener for edit subscriptions
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Sends the name, date, cost, comment, and position to the display subscriptions dialog
             *
             * @param adapterView
             * @param view current view
             * @param i position
             * @param l address
             */
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
    }

    /**
     * Listener for add subscription
     */
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

    /**
     * Listener for edit subscription
     *
     * @param dialogFragment a fragement of the dialog for main activity
     * @param pos position of the edited subscription in the array
     */
    @Override
    public void onEditPositive(DialogFragment dialogFragment, int pos){
        subscription temp = subscriptions.get(pos);
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

    /**
     * Initializes the adpater, view, and total
     */
    @Override
    public void onStart(){
        super.onStart();
        Log.i("LifeCycle --->", "onStart is called");
        TextView total = findViewById(R.id.total);
        loadFromFile();
        adapter = new myAdapter(this, R.layout.activity_main, subscriptions);
        listView.setAdapter(adapter);
        updateTotal();
    }

    /**
     * Updates the total cost
     */
    public void updateTotal(){
        TextView total = findViewById(R.id.total);
        float sum = 0;

        for (subscription sub: subscriptions) {
            sum += sub.getCost();
        }

        total.setText("Total: $"+ String.valueOf(sum));
        saveInFile();
    }

    /**
     * Loads the saved subscriptions from file
     */
    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<subscription>>(){}.getType();
            subscriptions = gson.fromJson(in, listType);
        }catch (FileNotFoundException e){
            subscriptions = new ArrayList<>();
        }
    }

    /**
     * Saves the subscriptions array
     */
    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subscriptions, out);
            out.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
