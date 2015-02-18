package com.tir38.android.customattrib;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MagicMan {

    private static final String TAG = "TheMagic";
    List<View> theViews;
    private Context mContext;

    public MagicMan(Context context) {
        mContext = context;
        theViews = new ArrayList<>();
    }

    public void doTheMagic(View view, Boolean doIt) {
        if (view instanceof TextView) {

            TextView textView = (TextView) view;

            int red = mContext.getResources().getColor(R.color.red);
            int blue = mContext.getResources().getColor(R.color.blue);

            if (doIt) {
                textView.setTextColor(red);
            } else {
                textView.setTextColor(blue);
            }
        }

        theViews.add(view);
        Log.d(TAG, "increased views to: " + theViews.size());
    }
}