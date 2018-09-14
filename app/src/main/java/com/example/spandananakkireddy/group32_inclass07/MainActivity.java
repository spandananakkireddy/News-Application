package com.example.spandananakkireddy.group32_inclass07;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    RequestParams requestParams;
  //  ArrayList<News> arrayList = new ArrayList<>();
    static String Category_key="category";
    String[] categories= {"Business","Entertainment", "General", "Health", "Science", "Sports","Technology"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Categories Activity");
        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, categories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isConnectedOnline()) {

                    // new GetDataAsync(MainActivity.this).execute(requestParams.getEncodeURL());

                    Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                    intent.putExtra(Category_key, categories[position]);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No Internet",Toast.LENGTH_SHORT).show();
                }
            }


        });
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
