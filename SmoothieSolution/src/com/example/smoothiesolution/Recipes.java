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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
		
		new DownloadFilesTask().execute("http://ec2-54-242-12-103.compute-1.amazonaws.com/API/populate_recipes.php");
	
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
        	final ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        	JSONObject j;
			try {
				j = new JSONObject(result);
				JSONArray jsonRecipes = j.getJSONArray("recipes");
				for (int counter = 0; counter < jsonRecipes.length(); counter++) {
        			String recipe_id = ((JSONObject)jsonRecipes.get(counter)).getString("recipeID");
        			String recipe_name = ((JSONObject)jsonRecipes.get(counter)).getString("recipeName");
        			String category = ((JSONObject)jsonRecipes.get(counter)).getString("category");
        			String prep_time = ((JSONObject)jsonRecipes.get(counter)).getString("prepTime");
        			Recipe new_recipe = new Recipe(recipe_id,recipe_name,category,prep_time);
    				recipes.add(new_recipe);
				}
				
	  			ArrayList<String> titles = new ArrayList<String>();
    			ArrayList<String> categories = new ArrayList<String>();
    			
    			for (int i = 0; i < recipes.size(); i++) {
    				titles.add(recipes.get(i).getName());
    				categories.add(recipes.get(i).getCategory());
    			}
    			
    			final ListView listview = (ListView) findViewById(R.id.listview);
    	        final ArrayAdapter adapter;
    		    adapter = new CustomAdapter(Recipes.this,titles);
    	        listview.setAdapter(adapter);
    			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
    }

}
