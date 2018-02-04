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
 * Created by Ceegan on 2018-01-21.
 */

public class AddSubscription extends DialogFragment {

    public interface AddSubscriptionListener{
        void onDialogPositive(DialogFragment dialogFragment);
    }

    AddSubscriptionListener listener;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            listener = (AddSubscriptionListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View content = layoutInflater.inflate(R.layout.add_subscription, null);

        builder.setCancelable(true).setView(content)
                .setPositiveButton(R.string.add_subscription, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Add subscription
            }
        }).setNegativeButton(R.string.cancel_action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final EditText editText = content.findViewById(R.id.date);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = editText.getText().toString();
                int textlength = editText.getText().length();

                if (text.endsWith("-")){
                    return;
                }

                if (textlength == 5 || textlength == 8){
                    editText.setText(new StringBuilder(text).insert(text.length()-1, "-").toString());
                    editText.setSelection(editText.getText().length());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = content.findViewById(R.id.name);
                EditText date = content.findViewById(R.id.date);
                EditText cost = content.findViewById(R.id.cost);

                if (name.getText().length() >= 1 && date.getText().length() == 10 && cost.getText().length() >= 1){
                    listener.onDialogPositive(AddSubscription.this);
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
