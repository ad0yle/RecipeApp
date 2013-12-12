package com.example.smoothiesolution;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Categories extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		Typeface tf= Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/RumRaisin-Regular.ttf");
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.filter_by_category));
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		
		TextView header = (TextView) findViewById(R.id.categories_header);
		header.setTypeface(tf);
		
		Bundle b = getIntent().getExtras();
		final ArrayList<String> ids = b.getStringArrayList("ids");
		final ArrayList<String> categories = new ArrayList<String>();
		categories.add("Fruit");
		categories.add("Green");
		categories.add("Coffee");
		categories.add("Dessert");
		categories.add("Workout");
		
		final String user_id = b.getString("user");
		
		for (int counter = 0; counter < categories.size(); counter ++) {
			Log.d("ALD",categories.get(counter));
		}
		
		final ListView listview = (ListView) findViewById(R.id.listview2);
        final ArrayAdapter adapter;
	    adapter = new CustomAdapter(Categories.this,categories);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                int position, long id) {
              //final Listing item = (Listing) parent.getItemAtPosition(position);
              final String category_id = categories.get(position);
              
              Intent returnIntent = new Intent();
              returnIntent.putExtra("category",category_id);
              returnIntent.putExtra("user", user_id);
              setResult(RESULT_OK,returnIntent);     
              finish();
            }

          });
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categories, menu);
		return true;
	}

}
