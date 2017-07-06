package com.codecourt.mylittlelibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ddopikMain on 4/30/2017.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = new MyView(this);
        setContentView(v);
    }
}