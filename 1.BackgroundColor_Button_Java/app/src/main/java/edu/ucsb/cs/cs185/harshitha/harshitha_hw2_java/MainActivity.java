package edu.ucsb.cs.cs185.harshitha.harshitha_hw2_java;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        //setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mainLayout=new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        //mainLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT));
      // mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT))
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //red button
        redButton=new Button(this);
        redButton.setText("Red");
        redButton.setBackgroundColor(getResources().getColor(R.color.red, null));
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
        greenButton.setBackgroundColor(getResources().getColor(R.color.green, null));
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
        blueButton.setBackgroundColor(getResources().getColor(R.color.blue, null));
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
        yellowButton.setBackgroundColor(getResources().getColor(R.color.yellow,null));
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
        textProgramJava.setText("Java");

        //adding into the layout
        mainLayout.addView(redButton);
        mainLayout.addView(greenButton);
        mainLayout.addView(blueButton);
        mainLayout.addView(yellowButton);
        mainLayout.addView(textProgramJava);
        setContentView(mainLayout);



/**
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
 */
    }
/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
