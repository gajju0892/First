package com.example.vhp;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button menu_2 ; 
	ListView l1 , l2 ;
	
	Button menu ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abhinav_1);
		 
		menu = (Button)findViewById(R.id.menu);
		menu.setOnClickListener(this) ; 
		
	/*	menu_2 = (Button)findViewById (R.id.menu_2);
		l1 = (ListView)findViewById(R.id.lv1) ; 
		l2 = (ListView)findViewById(R.id.lv2) ; 
		
		*/
		
		//active () ; 

	
        //set up full screen
      
		
		
		final SharedPreferences preferences2;
        preferences2 = this.getSharedPreferences("Language", 0); 
        final String languageToLoad = preferences2.getString("languageToLoad","en_US");
        Locale locale = new Locale(languageToLoad);  
        Locale.setDefault(locale); 
        Configuration config = new Configuration(); 
       config.locale = locale; 
        getBaseContext().getResources().updateConfiguration(config,  
       getBaseContext().getResources().getDisplayMetrics());
        
        
        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner1);
       
        
        if(languageToLoad.compareTo("en_US")==0){
            spinner3.setSelection(0);}
            else if(languageToLoad.compareTo("hi")==0){
            	spinner3.setSelection(1);
            }
            else 
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
     		        	str="fr";
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
            
            	spinner3.setSelection(2);
        
		
	}

	


	@Override
	public void onClick(View arg0) 
	{
		switch (arg0.getId())
		{
		case R.id.menu :
			
			DataBase db = new DataBase (this) ; 
			db.open();
			ArrayList l = db.read_news() ; 
			
			if (l.size() == 0 )
			{
				String x = "OOP and Java may not be for everyone \n" ; 
				String z =  "OOP and Java may not be for everyone \n"+  "http://domain.com/videofile.mp4" ; 
				String y  = "Use Temple funds for Uttarakhand reconstruction – VHP  - Ashok V. Chowgule, Working" +
						" President (External), VHP  Mumbai, June 29Sat, 2013 – “Pilgrimage " +
						"(Teerth Yatra) circuits in the form of Chaar Dhaams as four spirituo-cultural" +
						" outposts on the four borders of Bharat, 12 Jyotirlingamsand 52 Shaktipeeths dotting " +
				
						"and networking the whole of Akhand Bharat, the Buddhist" ;
						
						
			
					db.createEntry("0", "Use Temple funds for Uttarakhand reconstruction – VHP\n", y);
				    db.createEntry("0", "Relief work by Vishva Hindu Parishad in Uttarakhand.\n", y);
				    db.createEntry("0","VHP / Hindu Help Line Systems to Help Calamity Affected Uttarakhand\n", y) ; 
				    db.createEntry("0","Helpline for Kedarnath and Charodham Yatri.\n", y ) ;
				    db.createEntry("0", "VHP & Hindu Help Line Appeal to Relatives of Pilgrims who are yet at 4 Dhaam & not Traceable\n", y) ;
				    db.createEntry("0","‘Hindu Ahead’ Movement Launched for Hindu Security & Prosperity\n", y);
				    db.createEntry("0","Withdraw FIR against Togadia: VHP, Ashok Singhal asks Maharashtra Govt.\n", y);
				    db.createEntry("0","RECONSTITUTION OF THE ADVISORY BODY\n", y);
					
				
				for (int i = 0 ; i < 10 ; i++ )
				{
					db.createEntry("2", z, " ");
				}
			}
			
			db.close() ; 
			Intent i = new Intent (MainActivity.this , Menu_click.class) ;
			startActivity (i);  
			
			
			break ; 
			
		}
	}

	
	public void active ()
	{
		/*menu_2.setOnClickListener(new View.OnClickListener()
		
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent login = new Intent (MainActivity.this , MainActivity.class);
				login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(login);
				
			}
		});
		*/
		l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			
			

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				if (position == 0 )
				{
					Intent i = new Intent (MainActivity.this,News_list.class);
			        startActivity (i) ; 		
			}
				if (position == 1 )
				{
					Intent i = new Intent (MainActivity.this,News_list.class);
			        startActivity (i) ; 		
			}
				
				
			
			}
		});
	
		
l2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			
			

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				if (position == 2 )
				{
					Intent i = new Intent (MainActivity.this,Videos_list.class);
			        startActivity (i) ; 		
			}
				
			if(position == 0)
			{
				Intent i = new Intent (MainActivity.this,Photo_list.class);
		        startActivity (i) ;
				
			}
			}
		});
		
		
	}
	
}
