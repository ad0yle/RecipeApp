package com.example.smoothiesolution;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomTextEdit extends EditText {

	public CustomTextEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
		Typeface typeFace = Typeface.createFromAsset(context.getAssets(),"RumRaisin-Regular.ttf");
		this.setTypeface(typeFace);
	}

}
