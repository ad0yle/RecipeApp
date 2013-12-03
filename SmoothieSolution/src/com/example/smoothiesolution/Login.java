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
import android.widget.Toast;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
		Typeface typeFace = Typeface.createFromAsset(this.getAssets(),"fonts/26317-2.ttf");
		
		final EditText email = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);
		Button login = (Button) findViewById(R.id.login_button);
		
		email.setTypeface(typeFace);
		password.setTypeface(typeFace);
		login.setTypeface(typeFace);
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				if(email.getText().toString().trim().equals(""))
		    	 {   
		        	Toast toast1 = Toast.makeText(context, "Enter an email address", duration);
		        	toast1.show();
		    	 }
		    	
		    	if(password.getText().toString().trim().equals(""))
		   	 {   
		       	Toast toast2 = Toast.makeText(context, "Enter your password", duration);
		       	toast2.show();
		   	 }
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
