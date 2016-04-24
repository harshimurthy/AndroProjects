package edu.ucsb.cs.cs185.harshitha.keepscore;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by harshitha on 4/23/16.
 */
public class SetTaskFragment extends DialogFragment {
    private OnTaskChosenListener listener;

    public void setTaskListener(OnTaskChosenListener listener) {
        this.listener = listener;
    }

    public interface OnTaskChosenListener {
        void onTaskChosen(String task,String score);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Create a builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Inflate (turn xml into code) your layout
        View contentView = getActivity().getLayoutInflater().inflate(R.layout.set_team1_layout, null);

        //Get any Views you need (Any local variables accessed from inside an anonymous inner class must be final)
        final Spinner taskSelection = (Spinner) contentView.findViewById(R.id.task_selection);
        final EditText scoreSelection=(EditText)contentView.findViewById(R.id.score1);

        //Pass your data to the builder (these can be chained)
        builder.setView(contentView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String value = (String)taskSelection.getSelectedItem();
                        String value1= scoreSelection.getText().toString();
                        listener.onTaskChosen(value,value1);
                       //listener.onTaskChosen(value1);

                    }
                });

        //Build the dialog and return it
        return builder.create();
    }
}
