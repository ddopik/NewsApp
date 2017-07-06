



/**
 * Created by ddopikMain on 2/22/2017.
 */


package com.codecourt.ddopikmain.seedapplication.app.View.SettingMenu;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.NotificationConfig;
import com.codecourt.ddopikmain.seedapplication.R;


public class AboutUsActivity extends AppCompatActivity {

        private static final String TAG = AboutUsActivity.class.getSimpleName();
        private BroadcastReceiver mRegistrationBroadcastReceiver;
        private TextView txtRegId, txtMessage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.about_us_activity);

            txtRegId = (TextView) findViewById(R.id.txt_reg_id);
            txtMessage = (TextView) findViewById(R.id.txt_push_message);


            displayFirebaseRegId();
        }

        // Fetches reg id from shared preferences
        // and displays on the screen
        private void displayFirebaseRegId() {
            SharedPreferences pref = getApplicationContext().getSharedPreferences(NotificationConfig.SHARED_PREF, 0);
            String regId = pref.getString("regId", null);

            Log.e(TAG, "Firebase reg id: " + regId);

            if (!TextUtils.isEmpty(regId))
                txtRegId.setText("Firebase Reg Id: " + regId);
            else
                txtRegId.setText("Firebase Reg Id is not received yet!");
        }

        @Override
        protected void onResume() {
            super.onResume();
        }

        @Override
        protected void onPause() {
            super.onPause();
        }
}
