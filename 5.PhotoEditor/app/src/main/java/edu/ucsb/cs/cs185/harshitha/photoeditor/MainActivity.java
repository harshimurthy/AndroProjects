package edu.ucsb.cs.cs185.harshitha.photoeditor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private Toolbar bottomtoolbar;
    private Toolbar toptoolbar;

    String mCurrentPhotoPath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toptoolbar=(Toolbar)findViewById(R.id.top_toolbar);
        bottomtoolbar=(Toolbar)findViewById(R.id.bottom_toolbar);

        //setSupportActionBar(toptoolbar);
        toptoolbar.inflateMenu(R.menu.menu_resource);
        bottomtoolbar.inflateMenu(R.menu.menu_bottom);

        toptoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.gallery:galleryAddPic();

                }
            }
        });

        bottomtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;

            }
        });

    }

    private void galleryAddPic() {

        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("content://content://media/internal/images/media"));

        /**
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();

        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_resource,menu);
        //getMenuInflater().inflate(R.menu.menu_bottom,menu);
        return true;
    }




}
