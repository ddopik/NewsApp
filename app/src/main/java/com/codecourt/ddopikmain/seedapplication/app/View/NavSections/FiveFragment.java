package com.codecourt.ddopikmain.seedapplication.app.View.NavSections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecourt.ddopikmain.seedapplication.R;

/**
 * Created by ddopikMain on 2/20/2017.
 */

public class FiveFragment extends android.support.v4.app.Fragment{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_five, container, false);
    }
}
