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

public class Favorites extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		
		Typeface tf= Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/RumRaisin-Regular.ttf");
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.filter_by_category));
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		
		TextView header = (TextView) findViewById(R.id.categories_header);
		header.setTypeface(tf);
		Bundle c = getIntent().getExtras();
		final String[] ids = c.getStringArray("ids");
		final String user_id = c.getString("user");
		final ArrayList<String> names = c.getStringArrayList("names");
		
		final ListView listview = (ListView) findViewById(R.id.listview3);
        final ArrayAdapter adapter;
	    adapter = new CustomAdapter(Favorites.this,names);
        listview.setAdapter(adapter);
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                int position, long id) {
              //final Listing item = (Listing) parent.getItemAtPosition(position);
              final String recipe_id = ids[position];
              
              Intent returnIntent = new Intent();
              //Log.d("ALD",recipe_id);
              //Log.d("ALD",user_id);
              returnIntent.putExtra("recipe_id",recipe_id);
              returnIntent.putExtra("user", user_id);
              setResult(RESULT_OK,returnIntent);     
              finish();
            }

          });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorites, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED,returnIntent);
		finish();
		super.onBackPressed();
	}
}
