package com.tir38.android.customattrib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyCustomView extends TextView {

    private boolean mCustomAttrib;

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView, 0, 0);
        mCustomAttrib = typedArray.getBoolean(R.styleable.MyCustomView_myCustomAttrib, false);
        typedArray.recycle();
        init();
    }

    private void init() {

        int white = getResources().getColor(android.R.color.white);
        int darkgrey = getResources().getColor(android.R.color.darker_gray);

        if (mCustomAttrib) {
            setTextColor(white);
        } else {
            setTextColor(darkgrey);
        }
    }
}
