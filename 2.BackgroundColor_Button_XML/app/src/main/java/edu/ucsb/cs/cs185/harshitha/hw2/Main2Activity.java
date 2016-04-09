package edu.ucsb.cs.cs185.harshitha.hw2;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    //variables
    LinearLayout mainLayout;
    Button redButton;
    Button greenButton;
    Button blueButton;
    Button yellowButton;
    TextView textProgramJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainLayout=new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.HORIZONTAL);

        //red button
        redButton=new Button(this);
        redButton.setText("Red");
        redButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.red, null));

                //v.setBackgroundColor(0xFF0000);
                // view.getRootView().setBackgroundColor(getResources().getColor(R.color.red,null));

            }
        });

        //greenButton
        greenButton=new Button(this);
        greenButton.setText("Green");
        greenButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        //blueButton
        blueButton=new Button(this);
        blueButton.setText("Blue");
        blueButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.blue, null));
            }
        });

        //yellowButton
        yellowButton=new Button(this);
        yellowButton.setText("Yellow");
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.yellow, null));
            }
        });
        //yellowButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT));

        //text
        textProgramJava=new TextView(this);
        textProgramJava.setText("This was done programatically-Java");

        //adding into the layout
        mainLayout.addView(redButton);
        mainLayout.addView(greenButton);
        mainLayout.addView(blueButton);
        mainLayout.addView(yellowButton);
        mainLayout.addView(textProgramJava);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}
