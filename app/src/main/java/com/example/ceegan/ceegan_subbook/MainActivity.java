package com.example.ceegan.ceegan_subbook;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.addSub);
        fab.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               AddSubscription dialog = new AddSubscription();
               dialog.show(getFragmentManager(), "Add Subscription");
           }
        });
    }

}
