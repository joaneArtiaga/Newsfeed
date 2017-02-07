package com.example.joane14.artiaganewsfeed;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joane14 on 07/02/2017.
 */

public class CustomAdapter  extends ArrayAdapter<DataModel> {
    private int viewId;

    public CustomAdapter(Context context, int resource, List<DataModel> news) {
        super(context, resource, news);
        viewId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        DataModel news = getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(viewId, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_title.setText(news.getTitle());
        viewHolder.tv_section.setText(news.getSection());

        return view;
    }



    class ViewHolder {
        @BindView(R.id.tvTitle)
        public TextView tv_title;
        @BindView(R.id.tvSection)
        public TextView tv_section;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
