package com.tir38.android.customattrib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyCustomView extends TextView {

    private boolean mCustomBooleanAttrib;

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView, 0, 0);
        mCustomBooleanAttrib = typedArray.getBoolean(R.styleable.MyCustomView_myCustomAttrib, false);
        typedArray.recycle();
        init();
    }

    private void init() {

        int red = getResources().getColor(R.color.red);
        int blue = getResources().getColor(R.color.blue);

        if (mCustomBooleanAttrib) {
            setTextColor(red);
        } else {
            setTextColor(blue);
        }
    }
}
