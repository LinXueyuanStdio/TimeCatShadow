package com.timecat.plugin.picturebed.github.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tencent.shadow.sample.plugin.app.lib.R;

import java.util.ArrayList;

public class GridViewGalleryAdapter extends BaseAdapter {
    private Context context;
    private int layoutView;
    private ArrayList<Bitmap> listItems;

    public GridViewGalleryAdapter(ArrayList<Bitmap> listItems, Context context, int layoutView) {
        this.listItems = listItems;
        this.context = context;
        this.layoutView = layoutView;
    }

    public int getCount() {
        return this.listItems.size();
    }

    public Object getItem(int index) {
        return this.listItems.get(index);
    }

    public long getItemId(int index) {
        return (long) index;
    }

    public View getView(int index, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(this.layoutView, null);
        }
        ((ImageView) convertView.findViewById(R.id.imageViewListIcon)).setImageBitmap((Bitmap) this.listItems.get(index));
        return convertView;
    }

    public void refreshItems() {
        notifyDataSetChanged();
    }
}
