package com.example.smoothiesolution;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomTextEdit extends EditText{

    public CustomTextEdit(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextEdit(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "26317-2.ttf");
            setTypeface(tf);
        }
    }

 }