package com.example.vhp;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final SharedPreferences preferences2;
        preferences2 = this.getSharedPreferences("Language", 0);
        final String languageToLoad = preferences2.getString("languageToLoad","en_US");
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
       config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
       getBaseContext().getResources().getDisplayMetrics());
		setContentView(R.layout.activity_settings);
		  final Spinner spinner3 = (Spinner) findViewById(R.id.spinner1);
	       
	        
	        if(languageToLoad.compareTo("en_US")==0){
	            spinner3.setSelection(0);}
	            else if(languageToLoad.compareTo("hi")==0){
	            	spinner3.setSelection(1);
	            }
	            else 
	            	spinner3.setSelection(2);
	        
	        
	            	spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
	 
	     			@Override
	     			public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
	     				// TODO Auto-generated method stub
	     				String str =String.valueOf(parent.getSelectedItem());
	     				//SharedPreferences preferences2;
	     				
	     		      // preferences2 = MyApp.ap.getSharedPreferences("Language", 0); 
	     		        SharedPreferences.Editor editor = preferences2.edit(); 
	     		        if(str.compareTo("English")==0)
	     		        {
	     		        	
	     		        	str="en_US";
	     		        	
	     		        }
	     		        else if(str.compareTo("Hindi")==0)
	     		        {
	     		        	str="hi";
	     		        }
	     		        else
	     		        	str="gu";
	     		        if(str.compareTo(languageToLoad)!=0)
	     	        	{
	     	        		
	     	        	editor.putString("languageToLoad",str);
	     	        	editor.commit(); 
	     	        	finish();
	     	        	startActivity(getIntent());}
	     				
	     			}

	     			@Override
	     			public void onNothingSelected(AdapterView<?> parent) {
	     				// TODO Auto-generated method stub
	     				
	     			}
	     		});
	            	
	            	
	            	final Button b1=(Button)findViewById(R.id.button1);
	            	b1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent i = new Intent (Settings.this , Menu_click.class);
							finish();
							startActivity(i);
							
						}
					});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent i = new Intent (Settings.this , Menu_click.class);
		finish();
		startActivity(i);	}

}
