package edu.ucsb.cs.cs185.harshitha.camgit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by harshitha on 5/12/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public void setmDataset(String[] mDataset) {
        this.mDataset = mDataset;
    }

    private String[] mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mView.setText(mDataset[positio

        // TextView test = (TextView) holder.mView.findViewById(R.id.test_text);
        // test.setText(mDataset[position]);

        ImageView picroll = (ImageView) holder.mView.findViewById(R.id.camera_pic);

        File fileofpic= new File(mDataset[position]);


        Picasso.with(holder.mView.getContext()).load(fileofpic).resize(512,700).memoryPolicy(MemoryPolicy.NO_CACHE).into(picroll);

        /*Bitmap myBitmap = BitmapFactory.decodeFile(mDataset[position]);
        picroll.setImageBitmap(myBitmap);*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

