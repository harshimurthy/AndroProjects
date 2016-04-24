package edu.ucsb.cs.cs185.harshitha.keepscore;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Date;

/**
 * Created by harshitha on 4/23/16.
 */
public class SetDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    //private OnDateChosenListener listener1;

    OnDateChosenListener listener1;

    Date min_Date=new Date(112,9,30);
    Date max_Date= new Date(113,3,17);
    @Override
    public Dialog onCreateDialog(Bundle bundle)
    {
       // DatePickerDialog.setMaxDate
        DatePickerDialog date_dialog=new DatePickerDialog(getActivity(),this,2012,9,30);
        date_dialog.getDatePicker().setMaxDate(max_Date.getTime());
        date_dialog.getDatePicker().setMinDate(min_Date.getTime());
        return date_dialog;

    }

    @Override
    public void onDateSet(DatePicker datePicker,int year, int month, int day)
    {
       // listener1.getDatePicker().setMaxDate
        //dialog.getDatePicker().setMaxDate(new Date().getTime());
      // listener1.setMaxDate(04/17/2013);
        listener1.onDateChosen(year, month, day);
    }

    //public void setMaxDate()

    public interface OnDateChosenListener
    {
        void onDateChosen(int year, int month, int day);
    }

    public void setOnDateChosenListener(OnDateChosenListener listener1) {
        this.listener1 = listener1;
    }
}
