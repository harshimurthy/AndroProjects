package channel.all.it.mix.colorme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    Intent i;

    //load the xml file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //go to activity ColorXml on clicking this button
    public void submit_xml(View view) {
        i=new Intent(this,ColorXml.class);
        startActivity(i);
    }


    public void submit_java(View view) {
        i=new Intent(this,ColorJava.class);
        startActivity(i);

    }
}
