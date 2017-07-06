package com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.SourceNews;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.View.SourceList;
import com.codecourt.ddopikmain.seedapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by ddopikMain on 3/29/2017.
 */

public class SourcesListAdapter extends BaseAdapter {

    private SourceList homeActivity;
    private RealmResults<SourceNews> sourcesList;
    final boolean isSelected[];


//    public SourcesListAdapter(NewsNetWorkController newsNetWorkController) {
//        this.newsNetWorkController = newsNetWorkController;
//        this.imageLoader = newsNetWorkController.getImageLoader();
//    }

    public SourcesListAdapter(SourceList newsListActivity, RealmResults<SourceNews> sourceList) {
        this.homeActivity = newsListActivity;
        this.sourcesList = sourceList;
        isSelected = new boolean[sourceList.size()];
        for (int i = 0; i < sourceList.size(); i++) {
            this.isSelected[i] = sourceList.get(i).isDefaultSource();
        }
    }


    public Activity getHomeActivity() {
        return homeActivity;
    }

    public void setHomeActivity(SourceList homeActivity) {
        this.homeActivity = homeActivity;
    }


    @Override
    public int getCount() {
        return sourcesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        boolean defaultSource;
        MainApp.realm.beginTransaction();
        SourceNews sourceNews = MainApp.realm.where(SourceNews.class).equalTo("sourceID", position).findFirst();
        defaultSource = sourceNews.isDefaultSource();
        MainApp.realm.commitTransaction();

        if (convertView == null) {
            convertView = LayoutInflater.from(this.homeActivity).inflate(R.layout.source_item, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.sourseListContainer = (LinearLayout) convertView.findViewById(R.id.source_row_container);
            viewHolder.source_name = (CheckedTextView) convertView.findViewById(R.id.row_list_checkedtextview);
            viewHolder.newsImage = (ImageView) convertView.findViewById(R.id.row_list_checkbox_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.source_name.setText(this.sourcesList.get(position).getSourceName());

//        Log.i("flag ", "Index before IF # --->" + defaultSource);
        if (defaultSource) {
            viewHolder.newsImage.setImageDrawable(ContextCompat.getDrawable(homeActivity, R.drawable.set_check));
            viewHolder.sourseListContainer.setBackgroundColor(Color.parseColor("#F16585"));
                    viewHolder.source_name.setChecked(true);
//            Log.i("flag ","WithOutClick defaultSource"+"if (true)   --->"+(defaultSource)+"------------>"+defaultSource);

        } else {
            viewHolder.newsImage.setImageDrawable(ContextCompat.getDrawable(homeActivity, R.drawable.set_uncheck));
            viewHolder.sourseListContainer.setBackgroundResource(0);
                    viewHolder.source_name.setChecked(false);

        }

        viewHolder.sourseListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set the check text view
                boolean flag = viewHolder.source_name.isChecked();
//                viewHolder.source_name.setChecked(!flag);
                isSelected[position] = !isSelected[position];
                Log.i("onClick ","Flag is  --->"+flag);
                if (viewHolder.source_name.isChecked()) {
                    viewHolder.newsImage.setImageDrawable(ContextCompat.getDrawable(homeActivity, R.drawable.set_uncheck));
                    viewHolder.sourseListContainer.setBackgroundResource(0);
                    viewHolder.source_name.setChecked(false);
                } else {
                    viewHolder.newsImage.setImageDrawable(ContextCompat.getDrawable(homeActivity, R.drawable.set_check));
                    viewHolder.sourseListContainer.setBackgroundColor(Color.parseColor("#F16585"));
                    viewHolder.source_name.setChecked(true);

                }

            }
        });


        return convertView;
    }

    public boolean[] getSelectedFlags() {
//                    Log.i("flag ","Index before selected --->"+this.isSelected[0]);
//                    Log.i("flag ","Index before selected --->"+this.isSelected[1]);
//                    Log.i("flag ","Index before selected --->"+this.isSelected[2]);
//                    Log.i("flag ","Index before selected --->"+this.isSelected[3]);
        return isSelected;
    }


    static class ViewHolder {
        @BindView(R.id.source_row_container)
        LinearLayout sourseListContainer;
        @BindView(R.id.row_list_checkedtextview)
        CheckedTextView source_name;
        @BindView(R.id.row_list_checkbox_image)
        ImageView newsImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}