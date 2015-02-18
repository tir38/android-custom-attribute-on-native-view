Custom Attribute on Native View
----

We can apply a custom attribute to a custom view pretty easily:

```
 <com.tir38.android.customattrib.MyCustomView
        android:id="@+id/custom_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:myCustomAttrib="true"/>
```

But can we apply that same attribute to a built-in native view?
```
    <TextView
        android:id="@+id/basic_text_view_with_custom_attribute"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:myCustomAttrib="true"/>
```
YES WE CAN!

Thanks to this SO question: https://stackoverflow.com/questions/14800642/custom-xml-attributes-without-custom-view-in-fragment


TODO:
- can we apply same principals to layouts inflated in Activity?
- Look at how Calligraphy does this: https://github.com/chrisjenx/Calligraphy
