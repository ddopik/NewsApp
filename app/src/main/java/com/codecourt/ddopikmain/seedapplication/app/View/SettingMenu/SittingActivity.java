package com.codecourt.ddopikmain.seedapplication.app.View.SettingMenu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.NotificationConfig;
import com.codecourt.ddopikmain.seedapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp.SittingActivity_sharedPreferance;

/**
 * Created by ddopikMain on 4/17/2017.
 */

public class SittingActivity extends android.support.v4.app.Fragment {

    private View mainView;
    private Unbinder unbinder;

    @BindView(R.id.switchButton_1)
    Switch source_switch;
    @BindView(R.id.switchButton_2)
    Switch notification_switch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.setting_fragment, container, false);
        unbinder = ButterKnife.bind(this, mainView);
        setHasOptionsMenu(true);
        Toolbar myToolbar = (Toolbar) mainView.findViewById(R.id.my_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);


        SharedPreferences prefs = getActivity().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE);
        String restoredText = prefs.getString("source_switch", "false");
        if (restoredText.equals("true")) {
            source_switch.setChecked(true);
        }
        String notificationState = prefs.getString("notification_switch", "false");
        if (notificationState.equals("true")) {
            notification_switch.setChecked(true);
        }

        return mainView;
    }

    @OnClick(R.id.switchButton_1)
    public void change_source_switch(Switch source_switch) {
        SharedPreferences prefs = getActivity().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE);
        String restoredText = prefs.getString("source_switch", "false");
        if (restoredText.equals("true")) {
            SharedPreferences.Editor editor = getActivity().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE).edit();
            editor.putString("source_switch", "false");
            editor.commit();
            Log.e("SittingFragment", "--------->switchButton_1-->(True)");
        } else {
            SharedPreferences.Editor editor = getActivity().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE).edit();
            editor.putString("source_switch", "true");
            editor.commit();
            Log.e("SittingFragment", "--------->switchButton_1-->(false)");
        }

        Log.e("SittingFragment", "--------->switchButton_1+condition after -->" + prefs.getString("source_switch", null));
    }

    @OnClick(R.id.switchButton_2)
    public void turnNotification(Switch notify_switch) {
        SharedPreferences pref = MainApp.getMainAppApplication().getSharedPreferences(NotificationConfig.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        SharedPreferences pref2 = getActivity().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE);
        String restoredText = pref2.getString("notification_switch", "false");
        Log.e("SittingFragment", "--------->switchButton_2 value before -->" + pref2.getString("notification_switch", "false") + "---regId = " + regId);
        if (restoredText.equals("false")) {
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putString("regId", regId);
//            editor.commit();

            SharedPreferences.Editor editor2 = getActivity().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE).edit();
            editor2.putString("notification_switch", "true");
            editor2.commit();
            Log.e("SittingFragment", "--------->notification_switch-->(True)" + "regID =" + regId);
        } else if (restoredText.equals("true")) {
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putString("regId","");
//            editor.commit();

            SharedPreferences.Editor editor2 = getActivity().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE).edit();
            editor2.putString("notification_switch", "false");
            editor2.commit();
            Log.e("SittingFragment", "--------->notification_switch-->(False)" + "regID" + regId);
        }

        Log.e("SittingFragment", "--------->switchButton_2 value after -->" + pref2.getString("notification_switch", "false") + "---regId = " + regId);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("SittingActivity", "onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                LinearLayout fragmentOne = (LinearLayout) (getActivity()).findViewById(R.id.news_container);
                fragmentOne.setVisibility(View.VISIBLE);

                FrameLayout fragmentTwo = (FrameLayout) (getActivity()).findViewById(R.id.single_view_news_fragment);
                fragmentTwo.setVisibility(View.INVISIBLE);
//                new NavigationDrawerPresenter((MainActivity) getActivity());

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
