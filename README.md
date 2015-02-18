Custom Attribute on Native View
----

We can apply a custom attribute to a custom view pretty easily:

```
 <com.tir38.android.customattrib.MyCustomView
        android:id="@+id/custom_text_view2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:myCustomAttrib="true"/>
```

But can we apply that same attribute to a built-in native view?
```
    <TextView
        android:id="@+id/basic_text_view_with_custom_attribute_true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:myCustomAttrib="true"/>
```
YES WE CAN!
