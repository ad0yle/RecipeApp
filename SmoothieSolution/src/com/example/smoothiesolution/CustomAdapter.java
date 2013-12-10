package com.example.smoothiesolution;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> titles;
	
	public CustomAdapter(Context context, ArrayList<String> titles) {
		super(context, R.layout.row_layout, titles);
		this.context = context;
		this.titles = titles;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = convertView;
	    if (rowView == null) {
	    rowView = inflater.inflate(R.layout.row_layout, parent, false);
	    }
	    Typeface typeFace = Typeface.createFromAsset(context.getAssets(),"fonts/RumRaisin-Regular.ttf");
	    TextView textView = (TextView) rowView.findViewById(R.id.title);
	    textView.setTypeface(typeFace);
	    textView.setText(titles.get(position));
	    return rowView;
	  }

}
