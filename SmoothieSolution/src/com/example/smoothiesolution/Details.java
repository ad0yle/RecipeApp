package com.example.smoothiesolution;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Details extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.smoothie_banner));
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/RumRaisin-Regular.ttf");
		
		Bundle b = getIntent().getExtras();
		final String recipeID = b.getString("recipe_id");
		final String name = b.getString("name");
		final String category = b.getString("category");
		final String prep_time = b.getString("prep_time");
		final String user_id = b.getString("user");
		final ArrayList<String> ingredients = b.getStringArrayList("ingredients");
		final LinearLayout layout = (LinearLayout) findViewById(R.id.add);
		
		if(user_id.equals("none")) {
			Button favorites = (Button) findViewById(R.id.favorites);
			favorites.setVisibility(View.INVISIBLE);
		}
		
		for (int i = 0; i<ingredients.size(); i++) {
			final TextView ingredients_textView = new TextView(this);
			ingredients_textView.setText(ingredients.get(i));
			ingredients_textView.setTypeface(tf);
			ingredients_textView.setTextSize(16);
			ingredients_textView.setGravity(Gravity.CENTER);
			layout.addView(ingredients_textView);
		}
		
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(name);
		title.setTypeface(tf);
		
		TextView header = (TextView) findViewById(R.id.ingredients);
		header.setTypeface(tf);
		
		StringBuilder detail_full = new StringBuilder().append("Category: ").append(category);
		String detail_full_category = detail_full.toString();
		TextView detail_category = (TextView) findViewById(R.id.category);
		detail_category.setText(detail_full_category);
		detail_category.setTypeface(tf);
		
		StringBuilder prep_full = new StringBuilder().append("Prep Time: ").append(prep_time);
		String prep_full_text = prep_full.toString();
		TextView detail_prep = (TextView) findViewById(R.id.prep_time);
		detail_prep.setText(prep_full_text);
		detail_prep.setTypeface(tf);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
