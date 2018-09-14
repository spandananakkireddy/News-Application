package com.example.spandananakkireddy.group32_inclass07;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spandana Nakkireddy on 2/26/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    List<News> mData;

    int mResource;
    Context mContext;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News news = mData.get(position);
        Log.d("newsadapter", getItem(position) + "");

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
      TextView textView = (TextView) convertView.findViewById(R.id.textView);
        TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        textView.setText(news.getTitle());
        Picasso.with(mContext).load(news.getUrlToImage()).into(imageView);
        textView1.setText(news.getPublishedAt());
        return convertView;
    }


  /*  @Override
    public void onGetNews(ArrayList<News> al) {
        mData.addAll(al);
    }*/

}
