package com.example.spandananakkireddy.group32_inclass07;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Spandana Nakkireddy on 2/26/2018.
 */

public class NewsUtil {

    static  public  class  NewsJSONParser{
        static ArrayList<News> parseNews(String in) throws JSONException {
            ArrayList<News> newsList= new ArrayList<>();

            JSONObject root= new JSONObject(in);
            JSONArray newsJSONArray= root.getJSONArray("articles");
            for(int i=0; i<newsJSONArray.length();i++)
            {
                JSONObject newsJSONObject=newsJSONArray.getJSONObject(i);
                News news= new News();
                news.setAuthor(newsJSONObject.getString("author"));
                news.setTitle(newsJSONObject.getString("title"));
                news.setDescription(newsJSONObject.getString("description"));
                news.setUrl(newsJSONObject.getString("url"));
                news.setUrlToImage(newsJSONObject.getString("urlToImage"));
                news.setPublishedAt(newsJSONObject.getString("publishedAt"));
                newsList.add(news);

            }
            Log.d("demo3", newsList+"");
            return  newsList;

        }
    }
}
