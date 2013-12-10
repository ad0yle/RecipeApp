package com.example.smoothiesolution;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
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
		
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/RumRaisin-Regular.ttf");
        
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
		
		final EditText email = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);
		Button login = (Button) findViewById(R.id.login_button);
		
		email.setTypeface(tf);
		password.setTypeface(tf);
		login.setTypeface(tf);
		
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
		    	
		    	if (!password.getText().toString().trim().equals("") && !email.getText().toString().trim().equals("")) {
		    		String url = "http://ec2-54-242-12-103.compute-1.amazonaws.com/API/login.php";
		    		StringBuilder text = new StringBuilder().append(url).append("?email=").append(email.getText()).append("&password=").append(password.getText());
			        String full_url = text.toString();
		    		new DownloadFilesTask().execute(full_url);
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
	
	public class DownloadFilesTask extends AsyncTask<String, Void, String> {
    	ProgressDialog pd; 
    	
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		pd=ProgressDialog.show(Login.this,"","Logging in...",false); 
    	}
    	
    	protected String doInBackground(String... urls) {
    		String url = urls[0];
    		String result = " ";
        	HttpClient client = new DefaultHttpClient();
        	HttpGet get = new HttpGet(url);
        	try {
        		HttpResponse response = client.execute(get);
        		HttpEntity entity = response.getEntity();
        			if (null != entity) {
        				result = EntityUtils.toString(entity); 
        			}
        	} catch (ClientProtocolException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
           return result;
        }

        protected void onPostExecute(String result) {
        	pd.dismiss();
        	Context context = getApplicationContext();
        	Toast toast;
        	int duration = Toast.LENGTH_SHORT;
				if (result.contains("error")) {
		        	Log.d("ALD","Login Fail");
		           	toast = Toast.makeText(context, "Invalid login. Please try again", duration);
			        toast.show();
		           } else {
		        	   Log.d("ALD","Login Success");
		           	   Intent i = new Intent(Login.this, Recipes.class);
		        	   Bundle b = new Bundle();
		        	   b.putString("id", result); //Your id
		        	   i.putExtras(b);
		        	   startActivity(i);
		           }
        }
    }

}
