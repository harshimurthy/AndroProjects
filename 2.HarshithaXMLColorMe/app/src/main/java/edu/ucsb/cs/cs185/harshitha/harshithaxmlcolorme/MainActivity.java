package edu.ucsb.cs.cs185.harshitha.harshithaxmlcolorme;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        view.getRootView().setBackgroundColor(getResources().getColor(R.color.yellow, null));
    }
}
