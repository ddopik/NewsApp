package com.codecourt.mylittlelibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by ddopikMain on 4/30/2017.
 */

public class MyView extends LinearLayout {


    //To avoid adding initialization code to each constructor,
    // call a method named initialize from each constructor. Add the following code to each constructor:
    public MyView(Context context) {
        super(context);
        initialize(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }
    private void initialize(Context context){
//        Inflate a view from an XML resource.
//        This convenience method wraps the LayoutInflater class,
//        which provides a full range of options for view inflation.
        inflate(context,R.layout.my_view, this);
    }
}