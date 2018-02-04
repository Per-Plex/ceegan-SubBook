package com.example.ceegan.ceegan_subbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Displays the complete info of the subscription when clicked
 */

public class DisplaySubscription extends DialogFragment {


    /**
     * Listener for the main activity
     */
    public interface EditSubscriptionListener{
        /**
         *
         * @param dialogFragment a fragement of the dialog for main activity
         * @param position position of the item edited in subscriptions
         */
        void onEditPositive(DialogFragment dialogFragment, int position);
    }

    EditSubscriptionListener listener;

    /**
     * Attach method for the main activity
     * @param activity the current activity
     */
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


    /**
     * Creates the dialog, sets listeners, sets textwatchers, error checks for input, and displays
     *
     * @param savedInstanceState main activity instance
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View content = layoutInflater.inflate(R.layout.info_subscription, null);
        final Bundle args = getArguments();

        // Setting the content
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

        // Setting the info for the subscription
        EditText name = content.findViewById(R.id.subscription_name);
        final EditText date = content.findViewById(R.id.subscription_date);
        EditText cost = content.findViewById(R.id.subscription_cost);
        EditText comment = content.findViewById(R.id.subscription_comment);

        name.setText(args.getString("name"));
        date.setText(args.getString("date"));
        cost.setText(args.getString("cost"));
        comment.setText(args.getString("comment"));

        // Textwatcher for date
        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * Appends '-' at desired locations for proper date input
             *
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date.getText().toString();
                int textlength = date.getText().length();

                if (text.endsWith("-")){
                    return;
                }

                if (textlength == 5 || textlength == 8){
                    date.setText(new StringBuilder(text).insert(text.length()-1, "-").toString());
                    date.setSelection(date.getText().length());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // Error checking values before closing dialog
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = content.findViewById(R.id.subscription_name);
                EditText date = content.findViewById(R.id.subscription_date);
                EditText cost = content.findViewById(R.id.subscription_cost);

                if (name.getText().length() >= 1 && date.getText().length() == 10 && cost.getText().length() >= 1){
                    listener.onEditPositive(DisplaySubscription.this, args.getInt("Position"));
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(getActivity(), "Subscription is missing some info. Please try again", Toast.LENGTH_LONG).show();
                }
            }
        });
        return dialog;
    }
}
