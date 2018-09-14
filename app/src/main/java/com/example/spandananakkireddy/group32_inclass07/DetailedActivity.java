package com.example.spandananakkireddy.group32_inclass07;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity {

    News news;
    TextView tvtitle, tvdate,tvdescription;
    ImageView imageView;
    ScrollView sc;
    LinearLayout vertical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        setTitle("Detail Activity");
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        tvdate = (TextView) findViewById(R.id.tvdate);
        tvdescription = (TextView) findViewById(R.id.tvdescription);
        imageView = (ImageView) findViewById(R.id.imageView);
        sc = (ScrollView) findViewById(R.id.scroll);
        vertical = (LinearLayout) findViewById(R.id.vertical);
        if (isConnectedOnline()) {
            if (getIntent().getExtras() != null) {
                news = (News) getIntent().getExtras().getSerializable(NewsActivity.News_Object);
            }

            tvtitle.setText(news.getTitle());
            tvdate.setText(news.getPublishedAt());


            tvdescription.setText(news.getDescription());
            // et4= new TextView(this);

            Picasso.with(this).load(news.getUrlToImage()).placeholder(R.drawable.ic_launcher_foreground).into(imageView);
        } else {
            Toast.makeText(DetailedActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }
    private  boolean isConnectedOnline()
    {
        ConnectivityManager cm= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if( networkInfo!= null && networkInfo.isConnected())
        {
            return  true;
        }
        return  false;
    }
}
