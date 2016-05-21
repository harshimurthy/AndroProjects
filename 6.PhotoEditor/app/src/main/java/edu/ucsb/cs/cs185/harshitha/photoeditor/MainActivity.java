package edu.ucsb.cs.cs185.harshitha.photoeditor;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar bottomtoolbar;
    private Toolbar toptoolbar;
    List<GrayscaleTransformation> ltransformations = new ArrayList<>();


    private static int RESULT_LOAD_IMAGE = 1,RESULT_OK=1,SELECT_PICTURE=1;


    String mCurrentPhotoPath;
    int REQUEST_WRITE_STORAGE = 87;
    public static Uri imageHolder = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toptoolbar=(Toolbar)findViewById(R.id.top_toolbar);
        bottomtoolbar=(Toolbar)findViewById(R.id.bottom_toolbar);
        final ImgEdit thisimageView = (ImgEdit) findViewById(R.id.imageView1);
        thisimageView.transformsForMe();

        //setSupportActionBar(toptoolbar);
       // toptoolbar.inflateMenu(R.menu.menu_resource);
        bottomtoolbar.inflateMenu(R.menu.menu_bottom);

        bottomtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.bw) {
                    Bitmap bwImage = ((BitmapDrawable) thisimageView.getDrawable()).getBitmap();

                    GrayscaleTransformation blurg = new GrayscaleTransformation();
                    Bitmap bwimage = blurg.transform(bwImage);

                    thisimageView.setImageBitmap(bwimage);
                    ltransformations.add(new GrayscaleTransformation());

                    return true;


                }
                if (id == R.id.round) {
                    Bitmap roundImage = ((BitmapDrawable) thisimageView.getDrawable()).getBitmap();

                    CropCircleTransformation blurc = new CropCircleTransformation();
                    Bitmap cimage = blurc.transform(roundImage);

                    thisimageView.setImageBitmap(cimage);


                    //ltransformations.add(new CropCircleTransformation(thisimageView.getContext()));
                    return true;


                    //GrayscaleTransformation greyimage=new GrayscaleTransformation();
                }
                if (id == R.id.blur) {

                    Bitmap myImage = ((BitmapDrawable) thisimageView.getDrawable()).getBitmap();

                    BlurTransformation blurt = new BlurTransformation(thisimageView.getContext());
                    Bitmap newimage = blurt.transform(myImage);

                    thisimageView.setImageBitmap(newimage);
                    ltransformations.add(new BlurTransformation(thisimageView.getContext()));
                    return true;

                }

                return true;

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.d("onActivityResult,");

            Log.d("heli", "hiel");
            if (requestCode == SELECT_PICTURE && null != data) {
                Log.d("yes","yes");
                imageHolder = data.getData();

                Picasso.with(MainActivity.this)
                        .load(data.getData())
                        .fit()
                        .centerInside()
                        .into((ImageView) findViewById(R.id.imageView1));
            }

    }

    private void galleryAddPic() {

        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("content://content://media/internal/images/media"));



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.gallery)
        {
            Log.d("hi", "hi");
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            startActivityForResult(intent, SELECT_PICTURE);
        }
        if(id==R.id.revert)
        {
            if (ltransformations.size() > 0) {

                ImgEdit thisimageView = (ImgEdit) findViewById(R.id.imageView1);

               // Uri uri = resourceIdToUri(thisimageView.getContext(), R.id.imageView1);

                ltransformations.remove(ltransformations.size() - 1);
                //Pop last thing off the stack
                Picasso
                        .with(thisimageView.getContext())
                        .load(imageHolder)
                        .transform(ltransformations)
                        .into(thisimageView);
            }
            else
            {
                Context context = getApplicationContext();
                CharSequence text = "Nothing to Be Done";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            return true;


        }
        if(id==R.id.save)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                saveImageView();
            } else
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);

            Context context = getApplicationContext();
            CharSequence text = "Image Saved";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return true;
    }



    private void saveImageView() {
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "PhotoEditor";
            //File imageFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            //create dir if not there

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "PNG_" + timeStamp + "_.png";

            File imageFolder = new File(path);
            if (!imageFolder.exists()) {
                imageFolder.mkdir();
            }
            File image = null;
            image = new File(imageFolder, imageFileName);
            image.createNewFile();
            //editedImageView.saveImage(new FileOutputStream(image));


            ImageView thisimageView = (ImageView) findViewById(R.id.imageView1);
            Bitmap myImageBit = ((BitmapDrawable) thisimageView.getDrawable()).getBitmap();
            FileOutputStream myOutput = new FileOutputStream(image);
            myImageBit.compress(Bitmap.CompressFormat.PNG, 85, myOutput);
            myOutput.close();


            // Trigger media scanner, so that the new file will show up in the gallery the next time it's opened:
            MediaScannerConnection.scanFile(this, new String[]{image.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    // Auto-generated method stub, nothing to do here.
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 87) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //saveImageView();
            }
        }
    }






}
