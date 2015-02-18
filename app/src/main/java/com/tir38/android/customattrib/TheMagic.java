package com.tir38.android.customattrib;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TheMagic {

    private static final String TAG = "TheMagic";
    List<View> theViews;

    public TheMagic() {
        theViews = new ArrayList<>();
    }

    public void doTheMagic(View view, Boolean doIt) {
        if (doIt) {
            theViews.add(view);
            Log.d(TAG, "increased views to: " + theViews.size());
        }
    }
}
