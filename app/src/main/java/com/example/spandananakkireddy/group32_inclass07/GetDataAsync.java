package com.example.spandananakkireddy.group32_inclass07;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Spandana Nakkireddy on 2/26/2018.
 */

public class GetDataAsync extends AsyncTask<String,Void,ArrayList<News>> {

NewsActivity activity;
    IData iData;

    public GetDataAsync(NewsActivity activity1) {

        this.iData = activity1;

    }



    @Override
    protected void onPreExecute() {
        iData.startprog();

    }

    @Override
    protected ArrayList<News> doInBackground(String... params) {
        try {
            URL url= new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode= con.getResponseCode();
            if(statusCode==HttpURLConnection.HTTP_OK)
            {
                BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb= new StringBuilder();
                String line = reader.readLine();
                while(line != null)
                {
                    sb.append(line);
                    line=reader.readLine();
                }
                ArrayList<News> arrayList = NewsUtil.NewsJSONParser.parseNews(sb.toString());
                Log.d("demo",arrayList+"");
                return arrayList;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }


  @Override
    protected void onPostExecute(ArrayList<News> news) {
        super.onPostExecute(news);
//        activity.progressDialog.dismiss();
        Log.d("data",""+news);
       iData.onGetNews(news);

iData.endprog();
    }

    public  interface IData{
        void onGetNews(ArrayList<News> al);
        void startprog();
        void endprog();
    }
}
