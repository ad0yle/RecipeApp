package com.example.smoothiesolution;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

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
		} else {
			Log.d("ALD",id);
		}
		
		new DownloadFilesTask().execute("http://ec2-54-242-12-103.compute-1.amazonaws.com/recipes_smaller.txt");
	
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(Recipes.this, Login.class);
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
	
	public class DownloadFilesTask extends AsyncTask<String, Void, String> {
		
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
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
        				//Log.d("ALD",result);
        			}
        	} catch (ClientProtocolException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
           return result;
        }

        protected void onPostExecute(String result) {
			/*JSONObject j;
			try {
				j = new JSONObject(result);
				JSONArray jsonRecipe = j.getJSONArray("recipes");
				 for (int counter = 0; counter<jsonRecipe.length(); counter++) {
	        			String name = ((JSONObject)jsonRecipe.get(counter)).getString("name");
	        			String category = ((JSONObject)jsonRecipe.get(counter)).getString("category");
	        			String prep_time = ((JSONObject)jsonRecipe.get(counter)).getString("prep_time");
	        			JSONArray jsonIngredients = ((JSONObject)jsonRecipe.get(counter)).getJSONArray("ingredients");
        				ArrayList<String> ingredients = new ArrayList<String>();
	        			for (int counter2 = 0; counter2<6; counter++) {
	        				Log.d("ALD",((JSONObject)jsonIngredients.get(counter2)).getString("item"));
	        				//ingredients.add(((JSONObject)jsonIngredients.get(counter2)).getString("item"));
	        			}
	        			Log.d("ALD",name);
	        			Log.d("ALD",category);
	        			Log.d("ALD",prep_time);
	        			
	    			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        }
    }

}
