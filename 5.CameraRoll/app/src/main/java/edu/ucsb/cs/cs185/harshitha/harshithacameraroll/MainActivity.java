package edu.ucsb.cs.cs185.harshitha.harshithacameraroll;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MEDIA_TYPE_IMAGE = 1;
    private Cursor cursor;
    private int columnIndex;
    int lengthofDir;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    int counter=0;
    String photoPath;
    int Take_pic_code=125;
    List<String> listofPhotos = new ArrayList<String>();
    private int MY_REQUEST_CODE=2;
    String[] myDataset;
    Uri[] mUrls;
    String[] mFiles=null;
    public int position;




    private static final int REQUEST_CODE_PERMISSION = 2;
    //String[] mPermission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

    private Uri fileUri;
    GridView gridview;


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
    int len,len1;
    String strlent;
    int num=0;
    static final int NUM = 0;

Uri myUri;
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(String.valueOf(NUM), num);
        // savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            num = savedInstanceState.getInt(String.valueOf(NUM));
        }

        File checkfiles = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/CS185Pics/photo-0.jpg");
        if(checkfiles.exists()){
          //  TextView text = (TextView) findViewById(R.id.no_photos);
           // text.setVisibility(View.GONE);
            //lengthofDir=fileUtils
            myUri=Uri.fromFile(checkfiles);
            File[] imagelist=checkfiles.listFiles();
            //len1=checkfiles.length();
          // len=imagelist.length;
            //strlent=imagelist.length;


        }


        //Retriving Images from Database(SD CARD) by Cursor.



        //adding grid view
        GridView gridView=(GridView)findViewById(R.id.gridviewmainacitvity);


        ImageAdapter adapter=new ImageAdapter(this);
        //sdcardimage.setAdapter(adapter);
        //imageArray=new ArrayAdapter(this, R.layout.image_item,new ArrayList<String>());
       //gridView.setAdapter(imageArray);
       //  gridView.setAdapter(new ImageAdapter(this));



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {

               // long imageId = (ImageAdapter)parent.getAdapter.getItemAt(position);

                Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT);
                setContentView(R.layout.imagefull);
                ImageView img=(ImageView)findViewById(R.id.imageView2);
                // img.setBackgroundResource(R.drawable.icon);
                //img.setImageResource();
                img.setImageURI(Uri.parse("file://root/sdcard/Pictures/CS185Pics/Photo-" + position + ".jpg"));

                //Intent bigImage=new Intent(MainActivity.this, FullImageView.class);
                //bigImage.putExtra("position",position);
                //bigImage.setImageURI(Uri.parse("file://root/sdcard/Pictures/CS185Pics/Photo-" + i + ".jpg"));

                //startActivity(this);
                //startActivity(bigImage);
            }
        });

        String tempAddtoDataset;

        File deleteDir= new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES)) + "/CS185Pics");
        if(deleteDir.isDirectory()){
            myUri=Uri.fromFile(deleteDir);
            Log.d("cameraroll", "add");
            for(File file: deleteDir.listFiles()) {
                tempAddtoDataset=file.getAbsolutePath();
                //Environment.getExternalStorageDirectory() + "/CS190IPics/photo-"+(timeStamp)+".jpg";
                Log.d("cameraroll", "add");
                listofPhotos.add(0,tempAddtoDataset);
            }
        }
       myDataset =listofPhotos.toArray(new String[listofPhotos.size()]);
        gridView.setAdapter(new ImageAdapter(this));
        File dir=new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES)),"/CS185Pics");
        if(!dir.exists())
        {
            dir.mkdirs();
            myUri=Uri.fromFile(dir);
            File[] imagelist=dir.listFiles();
            len=imagelist.length;
            mFiles=new String[len];
            for(int i=0;i<imagelist.length;i++)
                mFiles[i]=imagelist[i].getAbsolutePath();
            mUrls=new Uri[mFiles.length];
            for(int i=0;i<mFiles.length;i++)
                mUrls[i]=Uri.parse(mFiles[i]);
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
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_main, menu);
    getMenuInflater().inflate(R.menu.main,menu);
        return true;

//        return super.onCreateOptionsMenu(menu);


    }

    private void submitTakePicIntent() {

        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_REQUEST_CODE);
        }
                //Capture Image by opening camera intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        num++;
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
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            //Intent intent = new Intent();
            //Intent intent=new Intent(MainActivity.this, Main.class);
            //setResult(RESULT_OK, intent);
            finish();
            //startActivity(MainActivity.this);
           // super.onBackPressed();

        }

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
            File deleteDir= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/CS185Pics");
            Log.d("cameraroll", "delete");
            if(deleteDir.isDirectory()){
                for(File file:deleteDir.listFiles() )
                {
                    file.delete();
                }
            }
            listofPhotos=new ArrayList<String>();
            String[] myDataset={};
            GridView gridView=(GridView)findViewById(R.id.gridviewmainacitvity);
            gridView.setAdapter(new ImageAdapter(this));
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
          // return imageIDs.length;
           return num;
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
           //File fileofpic=new File(myDataset[position]);
           if (convertView == null) {
              // Picasso.with(holder.mView.getContext()).load(fileofpic).resize(512,700).memoryPolicy(MemoryPolicy.NO_CACHE).into(picroll);

               imageView = new ImageView(context);
               imageView.setLayoutParams(new GridView.LayoutParams(185, 185));
               imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
               imageView.setPadding(5, 5, 5, 5);


           } else {
               imageView = (ImageView) convertView;
           }


          // imageView.setImageURI(Uri.parse("file://root/sdcard/Pictures/CS185Pics/Photo-0.jpg"));
         //  imageView.setImageURI(mUrls[position]);
           for(int i=0;i<num;i++) {
               // for(int i=num;i>=0;i--)
               int j=num-i;
               imageView.setImageURI(Uri.parse("file://root/sdcard/Pictures/CS185Pics/Photo-" + i + ".jpg"));
           }

          //imageView.setImageResource(imageIDs[position]);
           //imageView.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,""+Iid));
           return imageView;
       }


   }
}
