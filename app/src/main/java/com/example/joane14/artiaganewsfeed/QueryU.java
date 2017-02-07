package com.example.joane14.artiaganewsfeed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Joane14 on 07/02/2017.
 */

public class QueryU  {
    public QueryU(){

    }

    public static String makeHTTPConnection(String address) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(30000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }

    public static ArrayList<DataModel> parseJSONToNews(String JSON) {
        ArrayList<DataModel> news = new ArrayList<>();
        try {
            JSONObject source = new JSONObject(JSON).getJSONObject("response");
            JSONArray array = source.getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {
                DataModel model = new DataModel();
                model.setTitle(array.getJSONObject(i).getString("webTitle"));
                model.setSection(array.getJSONObject(i).getString("sectionName"));
                model.setUrl(array.getJSONObject(i).getString("webUrl"));
                news.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return news;
    }
}
