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
import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.smoothie_banner));
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/RumRaisin-Regular.ttf");
		
		Bundle b = getIntent().getExtras();
		final String recipeID = b.getString("recipe_id");
		final String name = b.getString("name");
		final String category = b.getString("category");
		final String prep_time = b.getString("prep_time");
		final String user_id = b.getString("user");
		final ArrayList<String> ingredients = b.getStringArrayList("ingredients");
		final LinearLayout layout = (LinearLayout) findViewById(R.id.add);
		Button favorites = (Button) findViewById(R.id.favorites);
		
		if(user_id.equals("none")) {
			favorites.setVisibility(View.INVISIBLE);
		} else {
			favorites.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new DownloadFilesTask().execute("http://ec2-54-242-12-103.compute-1.amazonaws.com/API/add_favorite.php");
				}
			});
		}
		
		for (int i = 0; i<ingredients.size(); i++) {
			final TextView ingredients_textView = new TextView(this);
			ingredients_textView.setText(ingredients.get(i));
			ingredients_textView.setTypeface(tf);
			ingredients_textView.setTextSize(16);
			ingredients_textView.setGravity(Gravity.CENTER);
			layout.addView(ingredients_textView);
		}
		
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(name);
		title.setTypeface(tf);
		
		TextView header = (TextView) findViewById(R.id.ingredients);
		header.setTypeface(tf);
		
		StringBuilder detail_full = new StringBuilder().append("Category: ").append(category);
		String detail_full_category = detail_full.toString();
		TextView detail_category = (TextView) findViewById(R.id.category);
		detail_category.setText(detail_full_category);
		detail_category.setTypeface(tf);
		
		StringBuilder prep_full = new StringBuilder().append("Prep Time: ").append(prep_time);
		String prep_full_text = prep_full.toString();
		TextView detail_prep = (TextView) findViewById(R.id.prep_time);
		detail_prep.setText(prep_full_text);
		detail_prep.setTypeface(tf);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

	private class DownloadFilesTask extends AsyncTask<String, Void, String> {
        
        @Override
        protected void onPreExecute() {
        	super.onPreExecute();
        }
    	protected String doInBackground(String... urls) {
    		final String url = urls[0];
    		
    		Bundle b = getIntent().getExtras();
    		final String recipeID = b.getString("recipe_id");
    		final String user_id = b.getString("user");
    		
    		String result = " ";
        	HttpClient client = new DefaultHttpClient();
        	final HttpPost post = new HttpPost(url);
        	
        	try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("recipeID",recipeID));
                nameValuePairs.add(new BasicNameValuePair("userID",user_id));
                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8);
                post.setEntity(ent);
                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
    			if (null != entity) {
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
     
        	int duration = Toast.LENGTH_SHORT;
        	Context context = getApplicationContext();
        		Toast toast = Toast.makeText(context, "Added to Favorites", duration);
	        	toast.show();   
        }
    }

}
