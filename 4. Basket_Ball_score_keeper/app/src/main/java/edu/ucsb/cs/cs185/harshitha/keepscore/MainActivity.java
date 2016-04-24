package edu.ucsb.cs.cs185.harshitha.keepscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.text.DateFormatSymbols;

public class MainActivity extends AppCompatActivity
{
    Button setDate;
    Button setTeam1;
    Button setTeam2;

    private static final String KEY_TEXT_VALUE = "textValue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDate=(Button)findViewById(R.id.EnterDate);
        setTeam1=(Button)findViewById(R.id.team1);
        setTeam2=(Button)findViewById(R.id.team2);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        String dateval=setDate.getText().toString();
        String team1val=setTeam1.getText().toString();
        String teamval2=setTeam2.getText().toString();

        savedInstanceState.putString("setDate",dateval);
        savedInstanceState.putString("setTeam1",team1val);
        savedInstanceState.putString("setTeam2",teamval2);



        super.onSaveInstanceState(savedInstanceState);


    }



    public void submitDate(View view)
    {
        SetDateFragment setDateFragment=new SetDateFragment();

        setDateFragment.setOnDateChosenListener(new SetDateFragment.OnDateChosenListener()
        {

            @Override
            public void onDateChosen(int year, int month, int day) {
                setDate.setText(getString(R.string.date_format, getMonth(month), day, year));
            }
            public String getMonth(int month) {
                return new DateFormatSymbols().getMonths()[month];
            }
        });
        setDateFragment.show(getFragmentManager(), "date_set");

    }

    public void onSetTask(View view) {
        SetTaskFragment setTaskFragment = new SetTaskFragment();
        setTaskFragment.setTaskListener(new SetTaskFragment.OnTaskChosenListener() {
            @Override
            public void onTaskChosen(String task,String score1) {
                setTeam1.setText(task+"\n"+score1);

            }
        });

        //Show the fragment
        setTaskFragment.show(getFragmentManager(), "team1");
    }


    public void onSetTask2(View view)
    {
        SetTaskFragment1 setTaskFragment1 = new SetTaskFragment1();
        setTaskFragment1.setTaskListener(new SetTaskFragment1.OnTaskChosenListener() {
            @Override
            public void onTaskChosen(String task,String score2) {
                setTeam2.setText(task+"\n"+score2);

            }
        });

        //Show the fragment
        setTaskFragment1.show(getFragmentManager(), "team2");
    }

    public void nextGameSubmit(View view) {
        /*
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        */
        setDate.setText("Enter Date");
        setTeam1.setText("Team 1 \n 0" );
        setTeam2.setText("Team 2 \n 0");
    }
}
