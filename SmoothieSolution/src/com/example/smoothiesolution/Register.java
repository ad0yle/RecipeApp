package com.example.smoothiesolution;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
		Typeface typeFace = Typeface.createFromAsset(this.getAssets(),"fonts/26317-2.ttf");
		Button signup = (Button) findViewById(R.id.signup);
		signup.setTypeface(typeFace);
		final EditText name = (EditText) findViewById(R.id.name);
		final EditText email = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);
		final EditText password2 = (EditText) findViewById(R.id.password2);
		final TextView signup_text = (TextView) findViewById(R.id.signup_text);
		
		name.setTypeface(typeFace);
		email.setTypeface(typeFace);
		password.setTypeface(typeFace);
		password2.setTypeface(typeFace);
		signup_text.setTypeface(typeFace);
		
		signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int check = 0;
				Context context = getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				
				if (name.getText().toString().trim().equals("")) {
			    	Toast toast1 = Toast.makeText(context, "Fill in the Name field", duration);
		        	toast1.show();
		        	check = 1;
				}
				
				if (email.getText().toString().trim().equals("")) {
			    	Toast toast3 = Toast.makeText(context, "Fill in the Email field", duration);
		        	toast3.show();
		        	check = 1;
				}
				
				if (password.getText().toString().trim().equals("") || password.getText().toString().length() < 8) {
			    	Toast toast4 = Toast.makeText(context, "Your password must contain at least 8 characters", duration);
		        	toast4.show();
		        	check = 1;
				}
				
				if (!password.getText().toString().equals(password2.getText().toString())) {
					Toast toast5 = Toast.makeText(context, "Passwords must match", duration);
		        	toast5.show();
		        	check = 1;
				}
				
				if (check == 0) {
				
					Toast toast5 = Toast.makeText(context, "Registration Successful", duration);
		        	toast5.show();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
