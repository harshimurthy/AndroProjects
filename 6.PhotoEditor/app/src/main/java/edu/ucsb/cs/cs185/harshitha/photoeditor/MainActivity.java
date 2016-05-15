package edu.ucsb.cs.cs185.harshitha.photoeditor;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


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

        //getSupportActionBar().setDisplayShowHomeEnabled(false);
        //getSupportActionBar().setIcon(R.drawable.save_icon);
       // setSupportActionBar(toptoolbar);

        toptoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id=item.getItemId();
                if(id==R.id.gallery)
                {
                    Log.d("hi","hi");


                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("You clicked Open gallery")
                                .show();


                }

                return true;
            }
        });

        bottomtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                int id=item.getItemId();
                if(id==R.id.blur)
                {
                    Log.d("hi", "hi");

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);


                }
                return true;

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
        toptoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.save)
                {
                    Log.d("hi","hi");


                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("You clicked Open gallery")
                            .show();


                }

                return true;
            }
        });
        //getMenuInflater().inflate(R.menu.menu_bottom,menu);
        return true;
    }




}
