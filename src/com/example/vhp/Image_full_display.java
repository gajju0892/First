package com.example.vhp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Image_full_display extends Activity  {

	ImageView im ; 
	TextView tv , no ; 
	String id;
	Button save ;
	String [] display = new String [2] ;
	 String results = "0" ; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_full_display);
		
		
		tv = (TextView)findViewById(R.id.image_text);
		no = (TextView)findViewById(R.id.t1);
		im = (ImageView)findViewById(R.id.i_1) ;
		Button comment = (Button)findViewById(R.id.photo_comment);
		 save=(Button)findViewById(R.id.photo_save);
			
		 Bundle gotbasket = getIntent().getExtras();
		 final ArrayList<Front_view> li=(ArrayList<Front_view>) gotbasket.getSerializable("data");
		 
		  new SomeTask().execute();
		 tv.setText(li.get(0).head) ; 
		 id=li.get(0).id;
		final  SavedDataBase ob=new SavedDataBase(this.getApplicationContext());
		 ob.open();
		 
		
			save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		   ob.insert(li.get(0))	;	
			}
		})
	;
		
		 
		comment.setOnClickListener(new View.OnClickListener() {
			
			
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent (Image_full_display.this, Post_comment.class);
				i.putExtra("id", new String []{li.get(0).id,results});
				startActivity(i);	
			
			}
		});
		
		
	}
	
	  private class SomeTask extends AsyncTask<String, Void, String > 
	    {
	      //  private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
	       
	        ProgressDialog d ; 
	       @Override
	        protected void onPreExecute()
	        {
	           // Toast.makeText(getApplicationContext(), "abhinav", 2000).show() ;
	    	  save.setVisibility(View.GONE) ; 
		d = new ProgressDialog (Image_full_display.this) ; 
	           
	            d.setMessage("Loading") ;
	            d.show() ; 
	  	        }

	        @Override
	        protected String  doInBackground(String... params) 
	        {
	        	 HttpGet request = new HttpGet("http://freakkydevill.comlu.com/vhp_comments_count.php?foreign_key="+id);
	        
	    		 try
	    		    {
	    		        HttpClient client = new DefaultHttpClient();
	    			     HttpResponse response=client.execute(request);
	    		        HttpEntity entity=response.getEntity();
	    		        InputStream is=entity.getContent();
	    		        InputStreamReader isr = new InputStreamReader(is);
	    		        BufferedReader reader = new BufferedReader(isr);
	    		        results = reader.readLine(); 
	    		        
	    		    }
	    		 catch(Exception e)
	    		 {
	    		 
	    		 }
	    		 return results ; 
	        }

	        @Override
	        protected void onPostExecute(String x  )
	        {
	        	 
	        	 d.dismiss() ; 
	        	 save.setVisibility(View.VISIBLE);
	             no.setText("("+x+")") ; 
	        }

	        
	    }

	
	

}
