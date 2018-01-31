package com.example.ceegan.ceegan_subbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Ceegan on 2018-01-30.
 */

public class DisplaySubscription extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View content = layoutInflater.inflate(R.layout.info_subscription, null);
        Bundle args = getArguments();


        builder.setView(content)
                .setPositiveButton(R.string.edit_subscription, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Edit subscription
                    }
                }).setNegativeButton(R.string.cancel_action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        TextView name = content.findViewById(R.id.subscription_name);
        TextView date = content.findViewById(R.id.subscription_date);
        TextView cost = content.findViewById(R.id.subscription_cost);
        TextView comment = content.findViewById(R.id.subscription_comment);

        name.setText(args.getString("name"));
        date.setText(args.getString("date"));
        cost.setText(args.getString("cost"));
        comment.setText(args.getString("comment"));

        return builder.create();
    }
}
