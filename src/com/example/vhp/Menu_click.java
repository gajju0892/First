package com.example.vhp;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;





import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Spinner;
import android.widget.Toast;


public class Menu_click extends Activity   

{

	static Button menu ;
	ListView  l3 ;

	int count = 0 ;
	String height1;

	boolean to_do = true ; 

	
	Button refresh ; 
	int width , height ; 
	ArrayList<Front_view> list = new ArrayList () ; 
	Bitmap[]  ob;
	ArrayList<News> p_1 ;
	ArrayList <String> paths = new ArrayList () ;
	ArrayList <String> paths_1 = new ArrayList () ;
	 ArrayAdapter adapter;
	 
	 
	 String test = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg" ;
	 
	 TextView tv  ; 
	 
	 private LinearLayout MenuList;
		private Button btnToggleMenuList ;
		ListView listmenu ;
		private int screenWidth;
		private boolean isExpanded;
		int count1 = 0  ; 

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
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

		 setContentView (R.layout.main);
		 
		 
		 Bundle b = this.getIntent().getExtras();
		 list= (ArrayList<Front_view>) b.getSerializable("notifications_list");
			   Bitmap bitmap = (Bitmap) b.getParcelable("BitmapImage"+String.valueOf(1));
			//	 Toast.makeText(this, list.get(0).head.toString(), 2000).show();
				/* ListView lv=(ListView)findViewById(R.id.listView1);
				 ArrayAdapter adapter	 = new   ArrayAdapter (this , android.R.layout.simple_list_item_1,new String []{"  News","  Videos","  Photos","  Movement","  Panchang","  Health Organisation","  Festival","  Jobs","  Health","  Reepie","  Music","  Technology","  Adventure","  Sports","  Beauty","  Settings","  About Us"}) ;
			     lv.setAdapter(adapter) ;*/
				// ImageView img =(ImageView)findViewById(R.id.imageView1);
				 //img.setImageBitmap(bitmap);
		 
		 
		 tv = (TextView)findViewById(R.id.textView1);
		  MenuList = (LinearLayout) findViewById(R.id.linearLayout2);
	        btnToggleMenuList = (Button) findViewById(R.id.button1);
	        refresh = (Button)findViewById(R.id.refresh_1) ; 
	        
	        refresh.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					new SomeTask().execute();	
					
				}
			}); 
	        
	        listmenu= (ListView)findViewById(R.id.button2) ;
	        ArrayAdapter adapter	 = new   ArrayAdapter (this , android.R.layout.simple_list_item_1,new String []{"  News","  Videos","  Photos","  Movement","  Panchang","  Health Organisation","  Festival","  Jobs","  Health","  Reepie","  Music","  Technology","  Adventure","  Sports","  Beauty","  Settings","Saved Pages","  About Us"}) ;
	        listmenu.setAdapter(adapter) ;
	        listmenu.setAdapter(adapter) ;
	      listmenu.setVisibility(View.GONE) ; 
	        DisplayMetrics metrics = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        screenWidth = metrics.widthPixels;
	        btnToggleMenuList.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				
	        		if (isExpanded) {
	        			
	        			isExpanded = false;
	        		 listmenu.setVisibility(View.GONE) ; 
	        			MenuList.startAnimation(new CollapseAnimation(MenuList, 0,(int)(screenWidth*0.7), 20,l3,tv));
	        		}
	        		else {
	            		isExpanded = true;
	            		// l3.setVisibility(View.GONE) ; 
	            		 tv.setVisibility(View.GONE) ;
	            				MenuList.startAnimation(new ExpandAnimation(MenuList, 0,(int)(screenWidth*0.7), 20,listmenu));
	            				 
	            				
	        		}			
	        		}
	        });

     data() ;

        DataBase db = new DataBase (this) ;
	       db.open();
	       p_1 = db.read_news() ;
	       db.close();

	       for (Front_view n :list)
	       {
	    	   int num=50;
	    	   if(n.head.length()>num)
	    	   {
	    		   paths_1.add(n.head.substring(0,num)+"...");
	    	   }
	    	   else
	    	   paths_1.add(n.head+"\n") ;
	    	  // paths.add(test);
	       }


	
	  /*     for (Front_view n : list)
	       {
	    	   
	    	   if (n.category.equals("news") || n.category.equals("photos"))
	    	   {
	    		   paths_1.add(n.head+"\n") ;
	    		   paths.add(n.image_url);
	    		  
	    	   }
	       }
*/
	   	final SavedDataBase ob=new SavedDataBase(this.getApplicationContext());
	   	ob.open();
	       
	       
		l3 = (ListView)findViewById(R.id.lv_1) ;
	l3.setAdapter(new MyAdapter(Menu_click.this, R.layout.list_news , paths_1));

	    listmenu.setOnItemClickListener(new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position ,
					long arg3) {
				// TODO Auto-generated method stub

				if (position == 0 )
				{     
					  Intent i = new Intent(Menu_click.this , Demo.class);
					  i.putExtra("data", "News") ;
					  startActivity(i) ;
					  finish() ; 
				  }
				else if (position == 2 )
				{
					  
				
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Photos") ; 
					 startActivity(i) ;
					 finish() ; 
					  
				//	 Toast.makeText(getApplicationContext(),""+ position, 2000).show() ; 
				}

				else if (position == 1 )
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Videos") ; 
					 startActivity(i) ;
					 finish() ; 
						}
				else if(position==16)
				{
					Intent login = new Intent (Menu_click.this , Menu_click.class);
			           // Parcelable[] output = new Parcelable[list.size()];
			           /*  for (int i=0; i<list.size(); i++) {
			                login.putExtra("bitmap"+String.valueOf(i),ob[i]);
			            }*/
			           Bitmap bitmap = null;
			    	//	Drawable myDrawable = getResources().getDrawable(R.drawable.fb_icon);
			         //	bitmap =  ((BitmapDrawable) myDrawable).getBitmap();
			         //	SavedDataBase ob=new SavedDataBase(this.getClass()
			          //  login.putExtra("BitmapArray", output);
			           list=ob.getAll();
			            login.putExtra("notifications_list", list);
			           
			            login.putExtra("BitmapImage"+String.valueOf(1), bitmap);
			            //login.putExtra("data",  list ) ; 
			            startActivity(login);
			            finish() ; 
				}
				else if(position==15)
				{
					Intent i1 = new Intent(Menu_click.this  ,  Settings.class);
					finish();
					  startActivity(i1) ;
					  finish() ; 
				}
				else if (position == 3 )
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Movements") ; 
					 startActivity(i) ;
					 finish() ; 
				}
				else if (position == 6 )
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Festival") ; 
					 startActivity(i) ;
					 finish() ; 
						}
				else if (position == 5)
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Health Organisation") ; 
					 startActivity(i) ;
					 finish() ; 
						}
				else if (position == 7)
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Jobs") ; 
					 startActivity(i) ;
					 finish() ; 
						}
				else if (position == 8)
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Health") ; 
					 startActivity(i) ;
					 finish() ; 
				}
				else if (position == 11)
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Technology") ; 
					 startActivity(i) ;
					 finish() ; 
				}
				else if (position == 12)
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Adventure") ; 
					 startActivity(i) ;
					 finish() ; 
				}
				else if (position == 13)
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Sports") ; 
					 startActivity(i) ;
					 finish() ; 
				}
				else if (position == 14)
				{
					 Intent i = new Intent(Menu_click.this , Demo.class);
					 i.putExtra( "data","Beauty") ; 
					 startActivity(i) ;
					 finish() ; 
				}
				

			}

	    });


         l3.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
                
				    if (list.get(position).category.equals("news")){
				   // Bundle basket = new Bundle ();
				    
					//basket.putStringArray("data",new String []{list.get(position).head.toString(),list.get(position).body.toString(),list.get(position).id} );
				    Intent i = new Intent(Menu_click.this,New_display.class);
				    ArrayList<Front_view> temp=new ArrayList();
					temp.add(list.get(position));
                     					i.putExtra("data", temp);

				    //i.putExtras(basket);
				    startActivity(i);
				    }
				    else if(list.get(position).category.equals("photos"))
				    {
				    	Bundle basket = new Bundle ();
						//basket.putStringArray("data",new String []{list.get(position).head.toString(),list.get(position).body.toString(),list.get(position).id} );
				    	ArrayList<Front_view> temp=new ArrayList<Front_view>();
						temp.add(list.get(position));
						
				    	
				    	Intent i = new Intent(Menu_click.this,Image_full_display.class);
					    i.putExtra("data", temp);
				    	//i.putExtras(basket);
					    startActivity(i);
					    	
				    }

			}


         });
         
         String results = "failed" ; 
        	
    /* 	try {
 	        URL url = new URL("http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg");
 	        //try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
 	        HttpGet httpRequest = null;
if (android.os.Build.VERSION.SDK_INT > 9) {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
}
 	        httpRequest = new HttpGet(url.toURI());

 	        HttpClient httpclient = new DefaultHttpClient();
 	        HttpResponse response = (HttpResponse) httpclient
 	                .execute(httpRequest);

 	        HttpEntity entity = response.getEntity();
 	        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
 	        InputStream input = b_entity.getContent();

 	        Bitmap bitmap = BitmapFactory.decodeStream(input);

 	        if (bitmap != null)
 	        Toast.makeText(getApplicationContext(), "success", 2000).show() ;
 	        	
 	        
 	      //  img.setImageBitmap(bitmap);
 	        
 	        //Toast.makeText(getApplicationContext(), "success", 2000).show() ;

 	    } catch (Exception ex) {
                
 	    	
 	    	results = "failed" ; 
 	    	Toast.makeText(getApplicationContext(), "results", 2000).show() ; 
 	    	
 	    }
 	    */
         
         for(int i = 0 ; i < paths.size() ; i++)
         {
        	 
         }
	}


	private void data() {
		// TODO Auto-generated method stub
		DataBase db = new DataBase (this) ;
		db.open();
		ArrayList l = db.read_news() ;

		if (l.size() == 0 )
		{
			String x = "OOP and Java may not be for everyone \n" ;
			String z =  "OOP and Java may not be for everyone \n"+  "http://domain.com/videofile.mp4" ;
			String y  = "Use Temple funds for Uttarakhand reconstruction � VHP  - Ashok V. Chowgule, Working" +
					" President (External), VHP  Mumbai, June 29Sat, 2013 � �Pilgrimage " +
					"(Teerth Yatra) circuits in the form of Chaar Dhaams as four spirituo-cultural" +
					" outposts on the four borders of Bharat, 12 Jyotirlingamsand 52 Shaktipeeths dotting " +

					"and networking the whole of Akhand Bharat, the Buddhist" ;



				db.createEntry("0", "Use Temple funds for Uttarakhand reconstruction � VHP", y);
			    db.createEntry("0", "Relief work by Vishva Hindu Parishad in Uttarakhand.", y);
			    db.createEntry("0","VHP / Hindu Help Line Systems to Help Calamity Affected Uttarakhand", y) ;
			    db.createEntry("0","Helpline for Kedarnath and Charodham Yatri.", y ) ;
			    db.createEntry("0", "VHP & Hindu Help Line Appeal to Relatives of Pilgrims who are yet at 4 Dhaam & not Traceable", y) ;
			    db.createEntry("0","�Hindu Ahead� Movement Launched for Hindu Security & Prosperity", y);
			    db.createEntry("0","Withdraw FIR against Togadia: VHP, Ashok Singhal asks Maharashtra Govt.", y);
			    db.createEntry("0","RECONSTITUTION OF THE ADVISORY BODY", y);


			for (int i = 0 ; i < 10 ; i++ )
			{
				db.createEntry("2", z, " ");
			}
		}
	/*	
		Intent i = getIntent () ; 
		list = i.getExtras()  
		*/
	}



	public class MyAdapter extends ArrayAdapter<String>{

    	public MyAdapter(Context context, int textViewResourceId,	ArrayList<String> paths_1) {
    		super(context, textViewResourceId, paths_1);

    	}

    	@Override
    	public View getDropDownView(int position, View convertView,ViewGroup parent) {
    		return getCustomView(position, convertView, parent);
    	}

    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		return getCustomView(position, convertView, parent);
    	}

    	@SuppressLint("NewApi")
		public View getCustomView(int position, View convertView, ViewGroup parent) {
    		
    		
    		 Display display = getWindowManager().getDefaultDisplay();
 	        Point size = new Point();
 	        display.getSize(size);
 	        width = size.x;
 	        height = size.y;
 	      

    		LayoutInflater inflater=getLayoutInflater();
	    	View row=inflater.inflate(R.layout.photos_1 , parent, false);
	    
		         
		         
		           TextView label=(TextView)row.findViewById(R.id.company);
		    	
		    	android.view.ViewGroup.LayoutParams params3 = label.getLayoutParams();
		         params3.height = width*8/45;
		         params3.width =  width*7/10;
		         label.setLayoutParams(params3);
		         
		    	
		    	label.setText(paths_1.get(position));
 		    	
		    	
		    	ImageView icon=(ImageView)row.findViewById(R.id.image_pic);
		    	 
		    	 android.view.ViewGroup.LayoutParams params2 = icon.getLayoutParams();
		         params2.height = width*8/45;
		         params2.width =  width*44/100 ;
		         icon.setLayoutParams(params2);
		   	
		     	  icon.setImageResource(R.drawable.banner) ;
		       
		         
		         return row;
    		
    	}

   }


	public class loading extends AsyncTask<String,Integer,ArrayList<String>>
	{

		@Override
		protected ArrayList<String> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	/*	
		Intent intent = new Intent (this ,Start.class ) ; 
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ; 
		intent.putExtra("EXIT", true )  ; 
		startActivity(intent) ; 
		*/
	}
	
	
	 private class SomeTask extends AsyncTask<String, Void, ArrayList<Front_view> > 
	    {
	      //  private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
	        Bitmap[] ob;
	        
	        ProgressDialog d ; 
	        
	        @Override
	        protected void onPreExecute()
	        {
	           // Toast.makeText(getApplicationContext(), "abhinav", 2000).show() ;
		d = new ProgressDialog (Menu_click.this) ; 
	            
	            d.setMessage("Loading") ;
	            l3.setVisibility(View.GONE) ; 
	           d.show() ; 
	             
	        
	        }

	        @Override
	        protected ArrayList<Front_view> doInBackground(String... params) 
	        {
	          
	           String results = "success" ; 
	            try {
	            	 list = new ArrayList () ; 
	            	 paths_1 = new ArrayList () ;
	            	 Connect c = new Connect () ; 
	                 list = c.front_view("http://freakkydevill.comlu.com/vhp_recent.php") ; 
	               
	                
	                 }
	              catch (Exception ex) {
	                  results = "faild" ; 
	            }
	            
	            finally
	            {
	            	 for (Front_view n :list)
	      	       {
	      	    	   int num=50;
	      	    	   if(n.head.length()>num)
	      	    	   {
	      	    		   paths_1.add(n.head.substring(0,num)+"...");
	      	    	   }
	      	    	   else
	      	    	   paths_1.add(n.head+"\n") ;
	      	    	  // paths.add(test);
	      	       }
	            }
	           return list  ;
	        }

	        @Override
	        protected void onPostExecute(ArrayList<Front_view> result)
	        {
	        	d.dismiss() ;
	        	
	        	l3.setAdapter(new MyAdapter(Menu_click.this, R.layout.list_news , paths_1));
 
	        	   l3.setVisibility(View.VISIBLE) ; 
	        	
	        }

	        
	    }

}
