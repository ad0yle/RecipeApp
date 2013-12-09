package com.example.smoothiesolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
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
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
		Button signup = (Button) findViewById(R.id.signup);
		final EditText name = (EditText) findViewById(R.id.name);
		final EditText email = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);
		final EditText password2 = (EditText) findViewById(R.id.password2);
		
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
				
				if (password.getText().toString().trim().equals("") || password.getText().toString().length() < 8 || password.getText().toString().length() >12) {
			    	Toast toast4 = Toast.makeText(context, "Your password must be between 8 and 12 characters", duration);
		        	toast4.show();
		        	check = 1;
				}
				
				if (!password.getText().toString().equals(password2.getText().toString())) {
					Toast toast5 = Toast.makeText(context, "Passwords must match", duration);
		        	toast5.show();
		        	check = 1;
				}
				
				if (check == 0) {
				
			    new DownloadFilesTask().execute("http://ec2-54-242-12-103.compute-1.amazonaws.com/smoothies/register.php");
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
	
	private class DownloadFilesTask extends AsyncTask<String, Void, String> {
        ProgressDialog pd;
        
        @Override
        protected void onPreExecute() {
        	super.onPreExecute();
        	pd=ProgressDialog.show(Register.this,"","Signing Up...",false);
        }
    	protected String doInBackground(String... urls) {
    		final EditText name = (EditText) findViewById(R.id.name);
    		final String name_text = name.getText().toString();
    		final EditText email = (EditText) findViewById(R.id.email);
    		final String email_text = email.getText().toString();
    		final EditText password = (EditText) findViewById(R.id.password);
    		final String pw = password.getText().toString();
    		final String url = urls[0];
    		String result = " ";
        	HttpClient client = new DefaultHttpClient();
        	HttpPost post = new HttpPost(url);
        	try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name",name_text));
                nameValuePairs.add(new BasicNameValuePair("email", email_text));
                nameValuePairs.add(new BasicNameValuePair("password", pw));
                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8);
                post.setEntity(ent);
                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
    			if (null != entity) {
    				Log.d("ALD","MEOW");
    				result = EntityUtils.toString(entity); 
    				Log.d("ALD",result);
    			}   
            } catch (ClientProtocolException e) {
                Log.d("ALD","caught1");
            } catch (IOException e) {
                Log.d("ALD","caught2");
            }
           return result;
        }

        protected void onPostExecute(String result) {
        	pd.dismiss();
        	int duration = Toast.LENGTH_SHORT;
        	Context context = getApplicationContext();
        	if (result.equals("error")) {
        		Toast toast = Toast.makeText(context, "This email address is already registered. Go back to login", duration);
	        	toast.show();         			
        		} else {
        			Toast toast1 = Toast.makeText(context, "Registration successful. Please login.", duration);
    	        	toast1.show();	
        		}
        }
    }


}
