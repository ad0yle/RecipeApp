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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Recipes extends Activity {
	
	public final ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
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
		Button categories = (Button) findViewById(R.id.categories_button);
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
		
		String urls[] = new String[2];
		urls[0] = "http://ec2-54-242-12-103.compute-1.amazonaws.com/API/populate_recipes.php";
		urls[1] = "http://ec2-54-242-12-103.compute-1.amazonaws.com/API/get_ingredients.php";
		new DownloadFilesTask().execute(urls);
	
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
		//ProgressDialog pd;
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		//pd=ProgressDialog.show(Recipes.this,"","Loading Recipes...",false); 
    	}
    	
    	protected String doInBackground(String... urls) {
    		
    		String url = urls[0];
    		String url2 = urls[1];
    		String result = " ";
    		String result2 = " ";
        	HttpClient client = new DefaultHttpClient();
        	HttpGet get = new HttpGet(url);
        	HttpGet get2 = new HttpGet(url2);
        	
        	try {
        		HttpResponse response = client.execute(get);
        		HttpResponse response2 = client.execute(get2);
        		HttpEntity entity = response.getEntity();
        		HttpEntity entity2 = response2.getEntity();
        			if (null != entity) {
        				result = EntityUtils.toString(entity); 
        				result2 = EntityUtils.toString(entity2); 
        				//Log.d("ALD",result2);
        			}
        	} catch (ClientProtocolException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
           return result + "#" + result2;
        }

        protected void onPostExecute(String result) {
        	String[] separated = result.split("#");
        	String result1 = separated[0];
        	String result2 = separated[1];
        	
        	Bundle b = getIntent().getExtras();
    		final String user_id = b.getString("id");
        	
        	
        	JSONObject j;
			try {
				j = new JSONObject(result1);
				JSONArray jsonRecipes = j.getJSONArray("recipes");
				for (int counter = 0; counter < jsonRecipes.length(); counter++) {
        			String recipe_id = ((JSONObject)jsonRecipes.get(counter)).getString("recipeID");
        			String recipe_name = ((JSONObject)jsonRecipes.get(counter)).getString("recipeName");
        			String category = ((JSONObject)jsonRecipes.get(counter)).getString("category");
        			String prep_time = ((JSONObject)jsonRecipes.get(counter)).getString("prepTime");
        			ArrayList<String> ingredients = new ArrayList<String>();
        			Recipe new_recipe = new Recipe(recipe_id,recipe_name,category,prep_time,ingredients);
    				recipes.add(new_recipe);
				}
				
	  			ArrayList<String> titles = new ArrayList<String>();
    			
    			for (int i = 0; i < recipes.size(); i++) {
    				titles.add(recipes.get(i).getName());
    			}
    			
    			final ListView listview = (ListView) findViewById(R.id.listview);
    	        final ArrayAdapter adapter;
    		    adapter = new CustomAdapter(Recipes.this,titles);
    	        listview.setAdapter(adapter);
    	        //pd.dismiss();
    			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject k;
			try {
				k = new JSONObject(result2);
				JSONArray jsonIngredients = k.getJSONArray("ingredients");
				ArrayList<String> recipe1 = new ArrayList<String>();
				ArrayList<String> recipe2 = new ArrayList<String>();
				ArrayList<String> recipe3 = new ArrayList<String>();
				ArrayList<String> recipe4 = new ArrayList<String>();
				ArrayList<String> recipe5 = new ArrayList<String>();
				ArrayList<String> recipe6 = new ArrayList<String>();
				ArrayList<String> recipe7 = new ArrayList<String>();
				ArrayList<String> recipe8 = new ArrayList<String>();
				ArrayList<String> recipe9 = new ArrayList<String>();
				ArrayList<String> recipe10 = new ArrayList<String>();
				ArrayList<String> recipe11 = new ArrayList<String>();
				ArrayList<String> recipe12 = new ArrayList<String>();
				ArrayList<String> recipe13 = new ArrayList<String>();
				ArrayList<String> recipe14 = new ArrayList<String>();
				ArrayList<String> recipe15 = new ArrayList<String>();
				
				for (int counter1 = 0; counter1 < jsonIngredients.length(); counter1++) {
					String id = ((JSONObject)jsonIngredients.get(counter1)).getString("recipeID");
					if (id.equals("1")) {
						recipe1.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("2")) {
						recipe2.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("3")) {
						recipe3.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("4")) {
						recipe4.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("5")) {
						recipe5.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("6")) {
						recipe6.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("7")) {
						recipe7.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("8")) {
						recipe8.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("9")) {
						recipe9.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("10")) {
						recipe10.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("11")) {
						recipe11.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("12")) {
						recipe12.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("13")) {
						recipe13.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("14")) {
						recipe14.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					} else if (id.equals("15")) {
						recipe15.add(((JSONObject)jsonIngredients.get(counter1)).getString("name"));
					}
				}
					recipes.get(0).setIngredients(recipe1);
					recipes.get(1).setIngredients(recipe2);
					recipes.get(2).setIngredients(recipe3);
					recipes.get(3).setIngredients(recipe4);
					recipes.get(4).setIngredients(recipe5);
					recipes.get(5).setIngredients(recipe6);
					recipes.get(6).setIngredients(recipe7);
					recipes.get(7).setIngredients(recipe8);
					recipes.get(8).setIngredients(recipe9);
					recipes.get(9).setIngredients(recipe10);
					recipes.get(10).setIngredients(recipe11);
					recipes.get(11).setIngredients(recipe12);
					recipes.get(12).setIngredients(recipe13);
					recipes.get(13).setIngredients(recipe14);
					recipes.get(14).setIngredients(recipe15);
					
					
					for (int counter3 = 0; counter3 < recipes.size(); counter3++) {
						ArrayList<String> temp = new ArrayList<String>();
						temp = recipes.get(counter3).getIngredients();
						
						/*for (int counter4 = 0; counter4 < temp.size(); counter4++) {
							Log.d("ALD", temp.get(counter4));
						}*/
					}
					
					Button categories = (Button) findViewById(R.id.categories_button);
					categories.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							 Intent i = new Intent(Recipes.this, Categories.class);
		    	             Bundle b = new Bundle();
		    	             ArrayList<String> ids = new ArrayList<String>();
		    	             
		    	             for (int counter = 0; counter < recipes.size(); counter++) {
				    	            final String recipe_id = recipes.get(counter).getRecipeID();
				    	            ids.add(recipe_id);
		    	             }
		    	             	b.putString("user", user_id);
		    	             	b.putStringArrayList("ids", ids);
				        	  i.putExtras(b);
		    	              startActivityForResult(i, 1);							
						}
					});
					
					
					ListView listview = (ListView) findViewById(R.id.listview);
					listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    	            @Override
	    	            public void onItemClick(AdapterView<?> parent, final View view,
	    	                int position, long id) {
	    	              //final Listing item = (Listing) parent.getItemAtPosition(position);
	    	              Log.d("ALD",recipes.get(position).getName());
	    	              final String recipe_id = recipes.get(position).getRecipeID();
	    	              
	    	              Intent i = new Intent(Recipes.this, Details.class);
	    	              
	    	              Bundle b = new Bundle();
	    	              b.putStringArrayList("ingredients", recipes.get(position).getIngredients());
	    	              b.putString("user", user_id);
			        	  b.putString("recipe_id", recipe_id);
			        	  b.putString("category", recipes.get(position).getCategory());
			        	  b.putString("name", recipes.get(position).getName());
			        	  b.putString("prep_time", recipes.get(position).getPrepTime());
			        	  
			        	 
			        	  i.putExtras(b);
	    	              startActivity(i);
	    	              
			        	  /*
			        	  b.putString("title", listings.get(position).getTitle());
			        	  b.putString("loggedIn", String.valueOf(value));
			        	  b.putString("price", listings.get(position).getPrice());
			        	  b.putString("description", listings.get(position).getDescription());
			        	  b.putString("dateListed", listings.get(position).getDateListed());*/
	    	            }

	    	          });
	    	        
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	
        }
    }

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         final String category=data.getStringExtra("category");
		         final String user_id = data.getStringExtra("user");
		         final ArrayList<String> names = new ArrayList<String>();
		         
		         Log.d("ALD",category);
		         Log.d("ALD",user_id);
		         
		         for (int counter = 0; counter < recipes.size(); counter++) {
		        	 if (recipes.get(counter).getCategory().equals(category)) {
		        		 names.add(recipes.get(counter).getName());
		        	 }
		         }
		         
		         final ListView listview = (ListView) findViewById(R.id.listview);
	    	       final ArrayAdapter adapter;
	    		    adapter = new CustomAdapter(Recipes.this,names);
	    	        listview.setAdapter(adapter);
	    	        
	    	        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    	            @Override
	    	            public void onItemClick(AdapterView<?> parent, final View view,
	    	                int position, long id) {
	    	              //final Listing item = (Listing) parent.getItemAtPosition(position);
	    	              String recipe_id = "";
	    	              String category = "";
	    	              String name = "";
	    	              String prep_time = "";
	    	              ArrayList<String> ingredients = new ArrayList<String>();
	    	              for (int i = 0; i < recipes.size(); i++) {
	    	            	  if (recipes.get(i).getName().equals(names.get(position))) {
	    	            		  recipe_id = recipes.get(i).getRecipeID();
	    	            		  category = recipes.get(i).getCategory();
	    	            		  ingredients = recipes.get(i).getIngredients();
	    	            		  name = recipes.get(i).getName();
	    	            		  prep_time = recipes.get(i).getPrepTime();
	    	            	  }
	    	              }
	    	              
	    	              Intent i = new Intent(Recipes.this, Details.class);
	    	              
	    	              Bundle b = new Bundle();
	    	              b.putStringArrayList("ingredients", ingredients);
	    	              b.putString("user", user_id);
			        	  b.putString("recipe_id", recipe_id);
			        	  b.putString("category", category);
			        	  b.putString("name", name);
			        	  b.putString("prep_time", prep_time);
			        	  
			        	 
			        	  i.putExtras(b);
	    	              startActivity(i);
	    	            }

	    	          });
	    	        
	    	        Button view_all = (Button) findViewById(R.id.view_all);
	    	        view_all.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							final ArrayList<String> view_all = new ArrayList<String>();
							for (int counter = 0; counter < recipes.size(); counter++) {
								view_all.add(recipes.get(counter).getName());
							}
							
							final ArrayAdapter adapter2;
			    		    adapter2 = new CustomAdapter(Recipes.this,view_all);
			    		    listview.setAdapter(adapter2);
			    		    
			    		    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			    	            @Override
			    	            public void onItemClick(AdapterView<?> parent, final View view,
			    	                int position, long id) {
			    	              //final Listing item = (Listing) parent.getItemAtPosition(position);
			    	              String recipe_id = "";
			    	              String category = "";
			    	              String name = "";
			    	              String prep_time = "";
			    	              ArrayList<String> ingredients = new ArrayList<String>();
			    	              for (int i = 0; i < recipes.size(); i++) {
			    	            	  if (recipes.get(i).getName().equals(view_all.get(position))) {
			    	            		  recipe_id = recipes.get(i).getRecipeID();
			    	            		  category = recipes.get(i).getCategory();
			    	            		  ingredients = recipes.get(i).getIngredients();
			    	            		  name = recipes.get(i).getName();
			    	            		  prep_time = recipes.get(i).getPrepTime();
			    	            	  }
			    	              }
			    	              
			    	              Intent i = new Intent(Recipes.this, Details.class);
			    	              
			    	              Bundle b = new Bundle();
			    	              b.putStringArrayList("ingredients", ingredients);
			    	              b.putString("user", user_id);
					        	  b.putString("recipe_id", recipe_id);
					        	  b.putString("category", category);
					        	  b.putString("name", name);
					        	  b.putString("prep_time", prep_time);
					        	  
					        	 
					        	  i.putExtras(b);
			    	              startActivity(i);
			    	            }

			    	          });
			    	        
						}
					});
		         
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
		}
}
