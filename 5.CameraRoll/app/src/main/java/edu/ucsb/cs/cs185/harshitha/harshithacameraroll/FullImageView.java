package edu.ucsb.cs.cs185.harshitha.harshithacameraroll;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by harshitha on 5/7/16.
 */
public class FullImageView extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagefull);
        ImageView img=(ImageView)findViewById(R.id.imageView2);
        img.setBackgroundResource(R.drawable.icon);
    }
}
