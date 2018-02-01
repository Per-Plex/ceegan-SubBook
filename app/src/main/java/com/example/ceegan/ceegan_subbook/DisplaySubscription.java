package com.example.ceegan.ceegan_subbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Ceegan on 2018-01-30.
 */

public class DisplaySubscription extends DialogFragment {

    public interface EditSubscriptionListener{
        void onEditPositive(DialogFragment dialogFragment);
    }

    EditSubscriptionListener listener;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            listener = (EditSubscriptionListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View content = layoutInflater.inflate(R.layout.info_subscription, null);
        final Bundle args = getArguments();

        builder.setView(content)
                .setPositiveButton(R.string.edit_subscription, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Edit subscription
                        Bundle pos = new Bundle();
                        pos.putInt("position", args.getInt("position"));
                        DisplaySubscription.this.setArguments(pos);
                        listener.onEditPositive(DisplaySubscription.this);
                    }
                }).setNegativeButton(R.string.cancel_action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        EditText name = content.findViewById(R.id.subscription_name);
        EditText date = content.findViewById(R.id.subscription_date);
        EditText cost = content.findViewById(R.id.subscription_cost);
        EditText comment = content.findViewById(R.id.subscription_comment);

        name.setText(args.getString("name"));
        date.setText(args.getString("date"));
        cost.setText("$" + args.getString("cost"));
        comment.setText(args.getString("comment"));

        return builder.create();
    }
}
