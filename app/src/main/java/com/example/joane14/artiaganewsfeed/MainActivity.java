package com.example.joane14.artiaganewsfeed;

import android.app.LoaderManager;
import android.content.*;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<DataModel>>{
    public static final int NEWS_LOADER_ID = 1;
    @BindView(R.id.newsList)
    public ListView newsListView;
    private CustomAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        newsAdapter = new CustomAdapter(this, R.layout.display_list, new ArrayList<DataModel>());
        newsListView.setAdapter(newsAdapter);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        LoaderManager loaderManager = getLoaderManager();

        if (networkInfo != null && networkInfo.isConnected()) {
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        }

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataModel news = (DataModel) newsListView.getItemAtPosition(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getUrl()));
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<List<DataModel>> onCreateLoader(int id, Bundle args) {
        return new LoadNews(this, getString(R.string.base_api));
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<DataModel>> loader, List<DataModel> data) {
        if (data.size() == 0 || data.get(0) == null) {
            return;
        }

        newsAdapter.clear();
        newsAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<DataModel>> loader) {
        newsAdapter.clear();
    }
}
