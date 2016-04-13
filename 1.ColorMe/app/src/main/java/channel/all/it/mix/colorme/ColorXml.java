package channel.all.it.mix.colorme;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by harshitha on 4/10/16.
 */
public class ColorXml extends AppCompatActivity{

    //restore on rotation
    Button red_button;
    Button green_button;
    Button blue_button;
    Button yellow_button;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_xml);

    }




    @TargetApi(Build.VERSION_CODES.M)
    public void submit_red_xml(View view) {
        view.getRootView().setBackgroundColor(getResources().getColor(R.color.red,null));
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void submit_green_xml(View view) {
        view.getRootView().setBackgroundColor(getResources().getColor(R.color.green,null));

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void submit_blue_xml(View view) {
        view.getRootView().setBackgroundColor(getResources().getColor(R.color.blue,null));
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void submit_yellow_xml(View view) {
        view.getRootView().setBackgroundColor(getResources().getColor(R.color.yellow,null));
    }
}
