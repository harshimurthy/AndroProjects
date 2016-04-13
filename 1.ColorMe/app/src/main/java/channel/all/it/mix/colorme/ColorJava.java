package channel.all.it.mix.colorme;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by harshitha on 4/10/16.
 */
public class ColorJava extends AppCompatActivity{

    private LinearLayout colorJavaLayout;
    private Button pinkButton;
    private Button greenButton;
    private Button purpleButton;
    private Button orangeButton;

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
        pinkButton = new Button(this);
        pinkButton.setText(getResources().getString(R.string.pink_button, null));
        pinkButton.setBackgroundColor(getResources().getColor(R.color.pink, null));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 0.25f;
        pinkButton.setLayoutParams(p);
        //setting functionality to Pink button
        pinkButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.pink, null));
            }
        });
        //adding the button to the layout
        colorJavaLayout.addView(pinkButton);

       //Green button- creating button
        greenButton=new Button(this);
        //setting button propoerties
        greenButton.setText(getResources().getString(R.string.green_button, null));
        greenButton.setBackgroundColor(getResources().getColor(R.color.light_green, null)); //setting button color
        greenButton.setLayoutParams(p);
        //adding functionality
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.light_green, null));
            }
        });
        //adding button to the layout
        colorJavaLayout.addView(greenButton);


        //Purple button- creating button
        purpleButton=new Button(this);
        //setting button propoerties
        purpleButton.setText(getResources().getString(R.string.purple_button,null));
        purpleButton.setBackgroundColor(getResources().getColor(R.color.purple, null)); //setting button color
        purpleButton.setLayoutParams(p);
        //adding functionality
        purpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.purple, null));
            }
        });
        //adding button to the layout
        colorJavaLayout.addView(purpleButton);


        //Orange button- creating button
        orangeButton=new Button(this);
        //setting button propoerties
        orangeButton.setText(getResources().getString(R.string.orange_button,null));
        orangeButton.setBackgroundColor(getResources().getColor(R.color.orange, null)); //setting button color
        orangeButton.setLayoutParams(p);
        //adding functionality
        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getRootView().setBackgroundColor(getResources().getColor(R.color.orange,null));
            }
        });
        //adding button to the layout
        colorJavaLayout.addView(orangeButton);


        setContentView(colorJavaLayout);
    }



}
