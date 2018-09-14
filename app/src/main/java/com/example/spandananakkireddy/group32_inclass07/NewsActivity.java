//Group32 Inclass07
// Sai Spandana Nakireddy - 801023658
// Priyanka Manusanipally -801017222

package com.example.spandananakkireddy.group32_inclass07;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements GetDataAsync.IData{



    RequestParams requestParams;
    ProgressDialog progressDialog;
    NewsAdapter adapter;
    ListView listView2;
    String str, str1;
    ArrayList<News> news= new ArrayList<News>();
    public static String News_Object="news";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle("News Activity");

        listView2 = (ListView)findViewById(R.id.listView2);

        progressDialog = new ProgressDialog(NewsActivity.this);
        if(isConnectedOnline()) {

            if (getIntent().getExtras().getSerializable(MainActivity.Category_key) != null) {
                str = (String) getIntent().getExtras().getSerializable(MainActivity.Category_key);
                str1 = str.toLowerCase();
                Log.d("str", str);


                new GetDataAsync(NewsActivity.this).execute("https://newsapi.org/v2/top-headlines?country=us&apiKey=246fb6ed247340deb05f8e31d6b5878e&category=" + str1);
                Log.d("linkasync", MainActivity.Category_key);
            }
        }
        else
        {
            Toast.makeText(NewsActivity.this,"No Internet",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onGetNews(ArrayList<News> al) {
        if (isConnectedOnline()){
        news.addAll(al);
        if(al.size() == 0)
        {
            Toast.makeText(NewsActivity.this,"No News found",Toast.LENGTH_SHORT).show();
        }
        else {


            Log.d("Loop", "entered");
            // progressDialog.dismiss();
            adapter = new NewsAdapter(this, R.layout.itemrow, news);
            listView2.setAdapter(adapter);

            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NewsActivity.this, DetailedActivity.class);

                    intent.putExtra(News_Object, news.get(position));
                    startActivity(intent);


                }
            });
        }
            }
            else {
                Toast.makeText(NewsActivity.this, "No internet", Toast.LENGTH_SHORT).show();
            }
        }



    @Override
    public void startprog() {
      progressDialog.setTitle("Loading News");
       progressDialog.setCancelable(false);
       progressDialog.show();
    }

    @Override
    public void endprog() {
        progressDialog.dismiss();

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
