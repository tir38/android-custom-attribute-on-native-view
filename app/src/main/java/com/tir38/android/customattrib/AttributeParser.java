package com.tir38.android.customattrib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

public class AttributeParser {

    private AttributeParserFactory mFactory;
    private MagicMan mMagicMan;

    /**
     * mAllAttributesForAllViews is a map of each view's id (Integer) to its attribute set,
     * where each attribute set is represented as a map of the attribute id (Integer) to its value (String).
     * *
     */
    private SparseArray<SparseArray<String>> mAllAttributesForAllViews;

    public AttributeParser(Context context) {
        mAllAttributesForAllViews = new SparseArray<>();
        mFactory = new AttributeParserFactory();
        mMagicMan = new MagicMan(context);
    }

    /**
     * Adds our own custom Factory to an existing LayoutInflater
     *
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
     *
     * @param inflater
     */
    public void setFactory(LayoutInflater inflater) {
        inflater.cloneInContext(inflater.getContext()).setFactory(mFactory);
    }

    /**
     * sets all attributes for all views inside parentView
     *
     * @param parentView
     */
    public void setViewAttribute(View parentView) {

        // iterate over each attribute set in the collection

        for (int i = 0; i < mAllAttributesForAllViews.size(); i++) {
            int viewId = mAllAttributesForAllViews.keyAt(i);

            if (viewId != 0) {  // valid view id
                View view = parentView.findViewById(viewId);
                if (view != null) {

                    // get attributes for view from list
                    SparseArray<String> attributes = mAllAttributesForAllViews.valueAt(i);

                    // apply each attribute one at a time
                    for (int j = 0; j < attributes.size(); j++) {

                        int attributeId = attributes.keyAt(j);

                        if (attributeId != 0) { // valid attribute id
                            String attributeValue = attributes.valueAt(j);

                            // TODO how can we check the type of the attribute value so we don't have to cast?
                            Boolean shouldDoMagic = Boolean.valueOf(attributeValue);
                            mMagicMan.doTheMagic(view, shouldDoMagic);

                            view.setTag(attributeValue); // even though we just did the magic, set the tag so we can check state of the magic later
                        }
                    }
                }
            }

        }
    }

    private void clear() {
        mAllAttributesForAllViews.clear();
    }

    private class AttributeParserFactory implements LayoutInflater.Factory {

        /**
         * called once for each View to store xml attributes for re-access later
         *
         * @param name
         * @param context
         * @param attrs
         * @return
         */
        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            String viewIdString = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "id");

            if (viewIdString != null) {
                // format view ID: remove "@" character and convert to Integer
                Integer viewId = Integer.valueOf(viewIdString.replace("@", ""));

                TypedArray libraryStyledAttributeList = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);

                SparseArray<String> attributeMap = new SparseArray<>();
                int i = 0;

                for (int attributeId : R.styleable.MyCustomView) {
                    String attributeValue = libraryStyledAttributeList.getString(i);

                    if (attributeValue != null)
                        attributeMap.put(attributeId, attributeValue);

                    i++;
                }

                // if any attributes were set on this view then store it to the master list
                if (attributeMap.size() != 0) {
                    mAllAttributesForAllViews.put(viewId, attributeMap);
                }

                libraryStyledAttributeList.recycle();
            }

            return null;
        }
    }
}