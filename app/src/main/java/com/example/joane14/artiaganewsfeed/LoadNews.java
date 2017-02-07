package com.example.joane14.artiaganewsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Joane14 on 07/02/2017.
 */

public class LoadNews extends AsyncTaskLoader<List<DataModel>> {
    private String url;

    public LoadNews(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<DataModel> loadInBackground() {
        String JSONResult = QueryU.makeHTTPConnection(url);

        return QueryU.parseJSONToNews(JSONResult);
    }
}
