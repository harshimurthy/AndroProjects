package edu.ucsb.cs.cs185.harshitha.harshithacameraroll;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MEDIA_TYPE_IMAGE = 1;


    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    int counter=0;
    String photoPath;
    int Take_pic_code=125;
    List<String> listofPhotos = new ArrayList<String>();
    private int MY_REQUEST_CODE=2;



    private static final int REQUEST_CODE_PERMISSION = 2;
    //String[] mPermission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

    private Uri fileUri;


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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        File checkfiles = new File(Environment.getExternalStorageDirectory() + "/CS185Pics/photo-0.jpg");
        if(checkfiles.exists()){
          //  TextView text = (TextView) findViewById(R.id.no_photos);
           // text.setVisibility(View.GONE);
        }



        //adding grid view
        GridView gridView=(GridView)findViewById(R.id.gridviewmainacitvity);
        //imageArray=new ArrayAdapter(this, R.layout.image_item,new ArrayList<String>());
       //gridView.setAdapter(imageArray);
       //  gridView.setAdapter(new ImageAdapter(this));



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {

                Intent bigImage=new Intent(MainActivity.this, FullImageView.class);
                startActivity(bigImage);
            }
        });

        String tempAddtoDataset;

        File deleteDir= new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES)) + "/CS185Pics");
        if(deleteDir.isDirectory()){
            Log.d("cameraroll", "add");
            for(File file: deleteDir.listFiles()) {
                tempAddtoDataset=file.getAbsolutePath();
                //Environment.getExternalStorageDirectory() + "/CS190IPics/photo-"+(timeStamp)+".jpg";
                Log.d("cameraroll", "add");
                listofPhotos.add(0,tempAddtoDataset);
            }
        }
        String[] myDataset =listofPhotos.toArray(new String[listofPhotos.size()]);
        gridView.setAdapter(new ImageAdapter(this));
        File dir=new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES)),"/CS185Pics");
        if(!dir.exists())
        {
            dir.mkdirs();
        }


        //floating camera
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.camera_icon);
        //assert fab != null;
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                submitTakePicIntent();





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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == Take_pic_code && resultCode == RESULT_OK) {
            String[] myDataset = listofPhotos.toArray(new String[listofPhotos.size()]);
        }
        else
            listofPhotos.remove(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        return super.onCreateOptionsMenu(menu);


    }

    private void submitTakePicIntent() {

        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_REQUEST_CODE);
        }
                //Capture Image by opening camera intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager())!=null)
        {
            File photoFile=null;
            try
            {
                photoFile=createImageFile();
               // TextView text=(TextView)findViewById(R.id.no_photos);
                //text.setVisibility(View.GONE);
            }
            catch(IOException e)
            {
                //error
            }
            if(photoFile!=null)
            {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent,Take_pic_code);

                /**
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_REQUEST_CODE);
                }
                 */


                listofPhotos.add(0, photoFile.getAbsolutePath());
            }
        }


    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
                // Log.d("Permission granted!!LOL");
            }
            else {
                //Log.d("Damn! Why permissions");
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
            }
        }
    }
    private File createImageFile() throws IOException
    {
        String imageFileName="Photo-"+counter;
        counter++;
        File savingDir=new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES))+"/CS185Pics");
        savingDir.mkdirs();
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        }
        File image=new File(savingDir,imageFileName+".jpg");
        if(!image.exists())
        {
            try
            {
                image.createNewFile();
            }
            catch(IOException ex)
            {

            }
        }

        photoPath="file:"+image.getAbsolutePath();
        return image;

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
   public class ImageAdapter extends BaseAdapter {
       private Context context;

       public ImageAdapter(Context c) {
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
       public View getView(int position, View convertView, ViewGroup parent) {
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
