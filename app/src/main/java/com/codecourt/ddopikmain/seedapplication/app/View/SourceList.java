package com.codecourt.ddopikmain.seedapplication.app.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.codecourt.ddopikmain.seedapplication.app.Model.SourceListModel;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.SourceNews;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter.SourcesListAdapter;
import com.codecourt.ddopikmain.seedapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class SourceList extends AppCompatActivity {


    private RealmResults<SourceNews> sourcesList;
    private SourcesListAdapter sourcesListAdapter;
    private boolean selectedSorces[];
    private SourceListModel sourceListModel;
    @BindView(R.id.sources_listview)
    ListView sourceListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.source_list_activity);
        Log.i("SourceList Activity", "----->On Create");
        setupInjection();


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        sourceListModel = new SourceListModel();
        this.sourcesList = sourceListModel.getSourcesList();
        sourcesListAdapter = new SourcesListAdapter(this, this.getSourcesList());
        this.sourceListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        sourceListView.setAdapter(sourcesListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SourceList Activity ", "---->On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("SourceList Activity ", "---->OnPause");
        this.selectedSorces = this.sourcesListAdapter.getSelectedFlags();
        sourceListModel.setDefaultSources(selectedSorces);
    }
    public RealmResults<SourceNews> getSourcesList() {
        return this.sourcesList;
    }

    protected void setupInjection() {
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
