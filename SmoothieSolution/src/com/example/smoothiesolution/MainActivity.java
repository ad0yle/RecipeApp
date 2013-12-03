package com.example.smoothiesolution;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Typeface typeFace = Typeface.createFromAsset(this.getAssets(),"fonts/26317-2.ttf");
		Button guest = (Button) findViewById(R.id.guest);
		guest.setTypeface(typeFace);
		
		Button register = (Button) findViewById(R.id.register);
		register.setTypeface(typeFace);
		
		Button login = (Button) findViewById(R.id.login);
		login.setTypeface(typeFace);
		
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, Login.class);
				startActivity(i);
				
			}
		});
		
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, Register.class);
	        	startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
