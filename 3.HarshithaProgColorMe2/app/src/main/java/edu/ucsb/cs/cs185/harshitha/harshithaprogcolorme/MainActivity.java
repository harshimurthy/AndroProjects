package edu.ucsb.cs.cs185.harshitha.harshithaprogcolorme;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout colorJavaLayout;
    private Button redButton;
    private Button greenButton;
    private Button blueButton;
    private Button yellowButton;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //creating layout.
        colorJavaLayout=new LinearLayout(this);
        colorJavaLayout.setOrientation(LinearLayout.HORIZONTAL);
        colorJavaLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        // colorJavaLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        colorJavaLayout.setFitsSystemWindows(true);
        // colorJavaLayout.setWeightSum(1f);

        //Pink button creating and adding functionality
        redButton = new Button(this);
        redButton.setText(getResources().getString(R.string.red_button, null));
        redButton.setBackgroundColor(getResources().getColor(R.color.red, null));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 0.25f;
        redButton.setLayoutParams(p);
        //setting functionality to Pink button
        redButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });
        //adding the button to the layout
        colorJavaLayout.addView(redButton);

        //Green button- creating button
        greenButton=new Button(this);
        //setting button propoerties
        greenButton.setText(getResources().getString(R.string.green_button, null));
        greenButton.setBackgroundColor(getResources().getColor(R.color.green, null)); //setting button color
        greenButton.setLayoutParams(p);
        //adding functionality
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });
        //adding button to the layout
        colorJavaLayout.addView(greenButton);


        //Purple button- creating button
        blueButton=new Button(this);
        //setting button propoerties
        blueButton.setText(getResources().getString(R.string.blue_button,null));
        blueButton.setBackgroundColor(getResources().getColor(R.color.blue, null)); //setting button color
        blueButton.setLayoutParams(p);
        //adding functionality
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.blue, null));
            }
        });
        //adding button to the layout
        colorJavaLayout.addView(blueButton);


        //Orange button- creating button
        yellowButton=new Button(this);
        //setting button propoerties
        yellowButton.setText(getResources().getString(R.string.yellow_button,null));
        yellowButton.setBackgroundColor(getResources().getColor(R.color.yellow, null)); //setting button color
        yellowButton.setLayoutParams(p);
        //adding functionality
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.yellow,null));
            }
        });
        //adding button to the layout
        colorJavaLayout.addView(yellowButton);


        setContentView(colorJavaLayout);
    }
}
