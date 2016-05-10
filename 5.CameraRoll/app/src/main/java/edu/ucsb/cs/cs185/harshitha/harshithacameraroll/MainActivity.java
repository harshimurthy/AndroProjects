package edu.ucsb.cs.cs185.harshitha.harshithacameraroll;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int REQUEST_TAKE_PHOTO=1;
    private Uri fileUri;


    //working with images
    File image1 = null;
    String cameraRollImages;
    File imageFolder;
    //File imageFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "HarshithaCameraRoll");

    ArrayAdapter imageArray;
    public Integer[] imageIDs=
            {
                    R.drawable.icon,
                    R.drawable.icon,
                    R.drawable.icon,
                    R.drawable.icon,
                    R.drawable.icon,
                    R.drawable.icon,
                    R.drawable.icon
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //adding grid view
        GridView gridView=(GridView)findViewById(R.id.gridviewmainacitvity);
        //imageArray=new ArrayAdapter(this, R.layout.image_item,new ArrayList<String>());
       //gridView.setAdapter(imageArray);
         gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {

                Intent bigImage=new Intent(MainActivity.this, FullImageView.class);
                startActivity(bigImage);
            }
        });




        //image1=new File(imageFolder.getPath()+File.separator+"CamPhoto_"+timeStamp+".jpg");





        //done working with images

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.camera_icon);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String timeStamp=new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
                //image1=new File(imageFolder.getPath()+File.separator+"CamPhoto_"+timeStamp+".jpg");
                //Intent intent = new Intent("MediaStore.ACTION_IMAGE_CAPTURE");
                //File photoFile=null;
                /**
                if(intent.resolveActivity(getPackageManager())!=null)
                {
                  //  imageFolder=getExternalFilesDir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"hi");

                    photoFile=createImageFile();
                }
                if(photoFile!=null)
                {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
                }
                startActivityForResult(intent,REQUEST_TAKE_PHOTO);

                ///fileUri=getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
               // Uri image2=getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
                //startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                 */
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private File createImageFile() {
        return null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.folder_view) {
            new AlertDialog.Builder(this)
                    .setMessage("You clicked Folder View")
                    .show();
            // Handle the camera action
        } else if (id == R.id.list_view) {
            new AlertDialog.Builder(this)
                    .setMessage("You clicked List View")
                    .show();

        } else if (id == R.id.settings) {
            new AlertDialog.Builder(this)
                    .setMessage("You clicked Settings")
                    .show();

        } else if (id == R.id.help) {
            new AlertDialog.Builder(this)
                    .setMessage("You clicked Help")
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   //Implementing image adapter
   public class ImageAdapter extends BaseAdapter
   {
       private Context context;

       public ImageAdapter(Context c)
       {
           context = c;
       }

       //---returns the number of images---
       public int getCount() {
           return imageIDs.length;
       }

       //---returns the ID of an item---
       public Object getItem(int position) {
           return position;
       }

       public long getItemId(int position) {
           return position;
       }

       //---returns an ImageView view---
       public View getView(int position, View convertView, ViewGroup parent)
       {
           ImageView imageView;
           if (convertView == null) {
               imageView = new ImageView(context);
               imageView.setLayoutParams(new GridView.LayoutParams(185, 185));
               imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
               imageView.setPadding(5, 5, 5, 5);
           } else {
               imageView = (ImageView) convertView;
           }
           imageView.setImageResource(imageIDs[position]);
           return imageView;
       }
   }
}
