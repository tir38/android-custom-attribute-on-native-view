package com.tir38.android.customattrib;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_main, null);

        // new way
        AttributeParser attributeParser = new AttributeParser();
        LayoutInflater layoutInflater = attributeParser.getLayoutInflater(inflater);
        View view = layoutInflater.inflate(R.layout.fragment_main, null);
        attributeParser.setViewAttribute(view);
        return view;
    }
}
