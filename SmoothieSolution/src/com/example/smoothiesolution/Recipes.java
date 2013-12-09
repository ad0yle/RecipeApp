package com.example.smoothiesolution;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Recipes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipes);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.smoothie_banner));
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		
		Button view_all = (Button) findViewById(R.id.view_all);
		Button favorites = (Button) findViewById(R.id.favorites);
		Button categories = (Button) findViewById(R.id.categories);
		Button logout = (Button) findViewById(R.id.logout);
		Button login = (Button) findViewById(R.id.login);
		
		Bundle b = getIntent().getExtras();
		String id = b.getString("id");
		if (id.equals("none")) {
			favorites.setVisibility(View.INVISIBLE);
			logout.setVisibility(View.INVISIBLE);
			login.setVisibility(View.VISIBLE);
		}
	
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(Recipes.this, MainActivity.class);
				startActivity(i);
				
			}
		});
		
	logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(Recipes.this, MainActivity.class);
				startActivity(i);
				
			}
		});
	
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipes, menu);
		return true;
	}

}
