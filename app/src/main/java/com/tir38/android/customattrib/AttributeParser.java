package com.tir38.android.customattrib;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class AttributeParser {

    private AttributeParserFactory mFactory;
    private Map<Integer, HashMap<Integer, String>> mAttributeList;

    public AttributeParser(){
        mAttributeList = new HashMap<Integer, HashMap<Integer, String>>();
        mFactory = new AttributeParserFactory();
    }

    public void clear() {
        mAttributeList.clear();
    }

    /**
     * Adds our own custom Factory to our existing LayoutInflater
     * @param inflater existing LayoutInflater
     * @return LayoutInflater with custom Factory
     */
    public LayoutInflater getLayoutInflater(LayoutInflater inflater) {
        clear();
        LayoutInflater layoutInflater = inflater.cloneInContext(inflater.getContext());
        layoutInflater.setFactory(mFactory);

        return layoutInflater;
    }

    /**
     * Sets custom factory to an existingLayoutInflater which DOES NOT yet have a factory attached
     * TODO: can a layout inflater be created w/out a Factory already attached?
     * @param inflater
     */
    public void setFactory(LayoutInflater inflater){
        inflater.cloneInContext(inflater.getContext()).setFactory(mFactory);
    }

    public void setViewAttribute(Activity activity) {
        for(Map.Entry<Integer, HashMap<Integer, String>> attribute : mAttributeList.entrySet())
            if(activity.findViewById((Integer) attribute.getKey()) != null)
                activity.findViewById((Integer) attribute.getKey()).setTag(attribute.getValue());

    }

    /**
     * sets all attributes for all views inside parentView
     * @param parentView
     */
    public void setViewAttribute(View parentView) {
        for(Map.Entry<Integer, HashMap<Integer, String>> attribute : mAttributeList.entrySet())

            if(parentView.findViewById(attribute.getKey()) != null)
                parentView.findViewById(attribute.getKey()).setTag(attribute.getValue());
    }

    public Map<Integer, HashMap<Integer, String>> getAttributeList() {
        return mAttributeList;
    }

    public void setAttributeList(Map<Integer, HashMap<Integer, String>> attributeList) {
        this.mAttributeList = attributeList;
    }

    private class AttributeParserFactory implements LayoutInflater.Factory{
        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            String id = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "id");

            if(id != null){
                // String with the reference character "@", so we strip it to keep only the reference
                id = id.replace("@", "");

                TypedArray libraryStyledAttributeList = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
                HashMap<Integer, String> libraryViewAttribute = new HashMap<Integer, String>();
                int i = 0;

                for(int attribute : R.styleable.MyCustomView){
                    String attributeValue = libraryStyledAttributeList.getString(i);

                    if(attributeValue != null)
                        libraryViewAttribute.put(attribute, attributeValue);

                    i++;
                }

                if(!libraryViewAttribute.isEmpty())
                    mAttributeList.put(Integer.valueOf(id), libraryViewAttribute);

                libraryStyledAttributeList.recycle();
            }

            return null;
        }

    }


}