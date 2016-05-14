package edu.ucsb.cs.cs185.harshitha.camgit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
private RecyclerView mRecyclerView;
private RecyclerView.Adapter mAdapter;
private RecyclerView.LayoutManager mLayoutManager;
int Int_take_pic = 125;
int timeStamp=0;
List<String> listofPhotos = new ArrayList<String>();
    public int MY_REQUEST_CODE=2;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            File checkfiles = new File(Environment.getExternalStorageDirectory() + "/CS190IPics/photo-0.jpg");

            if(checkfiles.exists()){
                TextView text = (TextView) findViewById(R.id.no_pho);
                text.setVisibility(View.GONE);


            }

            mRecyclerView = (RecyclerView) findViewById(R.id.rvContacts);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            // mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);


            // listofPhotos = new ArrayList<String>();
            String tempAddtoDataset;

            File deleteDir= new File(Environment.getExternalStorageDirectory() + "/CS190IPics");
            if(deleteDir.isDirectory()){
                Log.d("cameraroll", "add");
                for(File file: deleteDir.listFiles()) {
                    tempAddtoDataset=file.getAbsolutePath();
                    //Environment.getExternalStorageDirectory() + "/CS190IPics/photo-"+(timeStamp)+".jpg";
                    Log.d("cameraroll", "add");
                    listofPhotos.add(0,tempAddtoDataset);
                }
            }
            // specify an adapter (see also next example)

            Log.d("camroll", listofPhotos.size()+" photos");

            String[] myDataset =listofPhotos.toArray(new String[listofPhotos.size()]);

            mAdapter = new RecyclerAdapter(myDataset);
            mRecyclerView.setAdapter(mAdapter);


            File dir = new File(Environment.getExternalStorageDirectory() + "/CS190IPics");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            //fab.setBackgroundTintList(getColor(R.color));

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    dispatchTakePictureIntent();
                    //Intent pic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //  startActivityForResult(pic, Int_take_pic);




                }
            });
        }



        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
            if (requestCode == Int_take_pic && resultCode == RESULT_OK)
            {
//                TextView text = (TextView) findViewById(R.id.no_pho);
//                text.setVisibility(View.GONE);
//
//                Bitmap bmp = (Bitmap) intent.getExtras().get("data");
//                ImageView img = (ImageView) findViewById(R.id.camera_pic);
//                img.setImageBitmap(bmp);
                //Tell recycler to update for new photo
                //
//                mAdapter.
                String[] myDataset =listofPhotos.toArray(new String[listofPhotos.size()]);

                mAdapter = new RecyclerAdapter(myDataset);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();


            }
            else{
                listofPhotos.remove(0);
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);


            return super.onCreateOptionsMenu(menu);


        }


String mCurrentPhotoPath;

        private File createImageFile() throws IOException {
            // Create an image file name
            String imageFileName = "photo-" + timeStamp;
            timeStamp++;
            File storageDir = new File(Environment.getExternalStorageDirectory() + "/CS190IPics");
            storageDir.mkdirs();
            Log.d("cameraroll", storageDir.getAbsolutePath());
            File image = new File(storageDir, imageFileName + ".jpg");
            if (image == null)
            {
                Log.d("cameraroll", "image is null");
            }
            Log.d("cameraroll", image.getAbsolutePath());
            if (!image.exists())
            {
                try {
                    image.createNewFile();

                }
                catch (IOException ex)
                {
                    Log.d("cameraroll", ex.getMessage());
                }
            }
            else
            {
                Log.d("cameraroll", "Already exists");
            }


//        Log.d("cameraroll", image.getAbsolutePath());

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = "file:" + image.getAbsolutePath();


            return image;
        }

static final int REQUEST_TAKE_PHOTO = 1;


        private void dispatchTakePictureIntent() {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_REQUEST_CODE);
            }

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                Log.d("cameraroll", "dispatch");
                // Create the File where the photo should go
                File photoFile = null;
                try {

                    photoFile = createImageFile();
                    TextView text = (TextView) findViewById(R.id.no_pho);
                    text.setVisibility(View.GONE);



                } catch (IOException ex) {
                    // Error occurred while creating the File

                }
//            Log.d("cameraroll", photoFile.getAbsolutePath());
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                    startActivityForResult(takePictureIntent, Int_take_pic);

                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_REQUEST_CODE);
                    }
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_REQUEST_CODE);
                    }

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

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

       /* if (item.getItemId() == R.id.action_send) { // do something;
        } else if (item.getItemId() == R.id.action_archive) {
            // do something;
        } else if (item.getItemId() == R.id.action_open) {
            // do something;
        }*/

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_delete) {

                TextView text = (TextView) findViewById(R.id.no_pho);
                text.setVisibility(View.VISIBLE);
                timeStamp=0;
                File deleteDir= new File(Environment.getExternalStorageDirectory() + "/CS190IPics");
                Log.d("cameraroll", "delete");
                if(deleteDir.isDirectory()){
                    Log.d("cameraroll", "dir");

                    for(File file: deleteDir.listFiles())
                    {
                        Picasso.with(mRecyclerView.getContext()).invalidate(file);
                        file.delete();
                    }
                }
                listofPhotos=new ArrayList<String>();
                String[] myDataset={};
                mAdapter = new RecyclerAdapter(myDataset);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
}

