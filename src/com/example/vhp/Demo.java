package com.example.vhp;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.vhp.Menu_click.MyAdapter;




import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Demo extends Activity {
	 private LinearLayout MenuList;
		private Button btnToggleMenuList ;
		ListView listmenu ;
		private int screenWidth;
		private boolean isExpanded;
		int count1 = 0  ; 
		 TextView tv  ; 
			ListView  l3 ;
			int width , height ; 
			
			ArrayList <String> paths = new ArrayList () ;
			ArrayList <String> paths_1 = new ArrayList () ;
			
			ArrayList<Front_view> list = new ArrayList () ;
			
			 Bitmap  bitmap ; 
			 Button refresh ; 
			 String ss = "ff" ;
			 int menu_click = 0  ; 
			
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
	
		Bundle gotbasket = getIntent().getExtras();
		 String   ss1 = gotbasket.getString("data");
		 ss= ss1 ; 
		
		
		
		  MenuList = (LinearLayout) findViewById(R.id.linearLayout2_n);
	        btnToggleMenuList = (Button) findViewById(R.id.button1_n);
	        
	        listmenu= (ListView)findViewById(R.id.button2_n) ;
	        ArrayAdapter adapter	 = new   ArrayAdapter (this , android.R.layout.simple_list_item_1,new String []{"News","Videos","Photos","Movement","Panchang","Health Organisation","Festival","Jobs","Health","Reepie","Music","Technology","Adventure","Sports","Beauty","Settings","saved pages","About Us"}) ;
	        listmenu.setAdapter(adapter) ;
	        listmenu.setAdapter(adapter) ;
	      listmenu.setVisibility(View.GONE) ; 
	      tv = (TextView)findViewById(R.id.textView1_n);
	      tv.setText(ss) ; 
	        DisplayMetrics metrics = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        screenWidth = metrics.widthPixels;
	        
	    	l3 = (ListView)findViewById(R.id.lv_1_n) ;
	    	refresh = (Button)findViewById(R.id.refresh_2) ; 
	    			
	    	refresh.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = getIntent();
					finish();
					 intent.putExtra( "data",ss) ;
					startActivity(intent);
				}
			}) ; 
	    	
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
	            		
	            				MenuList.startAnimation(new ExpandAnimation(MenuList, 0,(int)(screenWidth*0.7), 20,listmenu));
	            				 
	            				
	        		}			
	        		}
	        });
	        
	        final SavedDataBase ob=new SavedDataBase(this.getApplicationContext());
	        ob.open();
	        listmenu.setOnItemClickListener(new OnItemClickListener (){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position ,
						long arg3) {
					// TODO Auto-generated method stub

					if (position == 0)
					{
						
						Intent intent = getIntent();
						finish();
						 intent.putExtra( "data","News") ;
						startActivity(intent);
					}
					else if(position==16)
					{
						
						ArrayList<Front_view> temp=ob.getAll();
						
						if(!temp.isEmpty())
						
							
							//Toast.makeText(this,"Nothing Saved In Database",2000).show();
						{
						Bitmap bitmap = null;
						 Intent login = new Intent (Demo.this , Menu_click.class);
				          
				    		//Drawable myDrawable = getResources().getDrawable(R.drawable.fb_icon);
				         //	bitmap =  ((BitmapDrawable) myDrawable).getBitmap();
				         	
				          //  login.putExtra("BitmapArray", output);
				            login.putExtra("notifications_list", temp);
				            
				           
				            login.putExtra("BitmapImage"+String.valueOf(1), bitmap);
				            //login.putExtra("data",  list ) ; 
				            startActivity(login);
						}
					}
					else if (position == 2 )
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Photos") ;
						startActivity(intent);
					}

					else if (position == 1 )
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Videos") ;
						startActivity(intent);
					}
					else if(position==15)
					{
						Intent i1 = new Intent(Demo.this  ,  Settings.class);
						finish();
						  startActivity(i1) ;
					}
					else if (position == 3 )
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Movements") ;
						startActivity(intent);
					}
					else if (position == 5 )
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Health Organisation") ;
						startActivity(intent);
					}
					else if (position == 6)
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Festival") ;
						startActivity(intent);
					}
					else if (position == 7 )
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Jobs") ;
						startActivity(intent);
					}
					else if (position == 8)
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Health") ;
						startActivity(intent);
					}
					else if (position == 10)
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Music") ;
						startActivity(intent);
					}
					else if (position == 11)
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Technology") ;
						startActivity(intent);
					}
					else if (position == 12)
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Adventure") ;
						startActivity(intent);
					}
					else if (position == 13)
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Sports") ;
						startActivity(intent);
					}
					else if (position == 14)
					{
						Intent intent = getIntent();
						finish();
						intent.putExtra( "data","Beauty") ;
						startActivity(intent);
					}
				}

		    });
	        
	        l3.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long arg3) {
					// TODO Auto-generated method stub

					if (ss.equals("Videos")){
						Uri uri = Uri.parse("http://domain.com/videofile.mp4");
			            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			            startActivity(intent);
						}
					else	if (list.get(position).category.equals("news")){
					 Bundle basket = new Bundle ();
						//basket.putStringArray("data",new String []{list.get(position).head.toString(),list.get(position).body.toString(),list.get(position).id.toString()} );
					    Intent i = new Intent(Demo.this,New_display.class);
					    ArrayList<Front_view> temp=new ArrayList<Front_view>();
					    temp.add(list.get(position));
					    
					    i.putExtra("data",temp);
					    startActivity(i);
					}
					
					else	if (list.get(position).category.equals("photos")){
						 Bundle basket = new Bundle ();
							//basket.putStringArray("data",new String []{list.get(position).head.toString(),list.get(position).body.toString(),list.get(position).id.toString()} );
						    Intent i = new Intent(Demo.this,Image_full_display.class);
						    //i.putExtras(basket);
						    ArrayList<Front_view> temp=new ArrayList<Front_view>();
						    temp.add(list.get(position));
						    
						    i.putExtra("data",temp);
						    startActivity(i);
						}
					

				}


	         });
	        
	        

	        new loadsome().execute() ; 
	 
	}
	

	public class loadsome extends AsyncTask<String,Integer,ArrayList<Front_view>>
	{

		ProgressDialog d ; 
		
		@Override
		protected ArrayList<Front_view> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try 
			{
				
			  /*  URL url = new URL("http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg");
	 	        //try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
	 	        HttpGet httpRequest = null;

	 	        httpRequest = new HttpGet(url.toURI());
      
	 	        HttpClient httpclient = new DefaultHttpClient();
	 	        HttpResponse response = (HttpResponse) httpclient
	 	                .execute(httpRequest);
         
	 	        
	 	        HttpEntity entity = response.getEntity();
	 	        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
	 	        InputStream input = b_entity.getContent();*/

//	 	        bitmap  = BitmapFactory.decodeStream(input);
	 	       if (menu_click == 0) {
	 	      Connect c = new Connect () ; 
              if(ss.equals("Videos" )==false)
	 	      list = c.front_view("http://freakkydevill.comlu.com/vhp.php?condition=order+by+timestamp+desc&key=category&value="+ss.toLowerCase().toString()) ; 
              else
              {
            	  list = c.front_view("http://freakkydevill.comlu.com/vhp.php?condition=order+by+timestamp+desc&key=category&value="+"news") ; 
              }
              for (Front_view n :list)
   	       {
   	    	   paths_1.add(n.head+"\n") ;
   	    	  // paths.add(test);
   	       }

	 	       }
	 	       else if (menu_click == 1)
	 	       {
	 	    	  Connect c = new Connect () ; 
	                 list = c.front_view("http://freakkydevill.comlu.com/vhp_recent.php") ; 
	                
	 	       }
				
			}catch (Exception e )
			{
				
			}
			
			return list;
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		
		@Override
		protected void onPostExecute(ArrayList<Front_view> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (menu_click == 0 )
			{
	    	l3.setAdapter(new MyAdapter(Demo.this, R.layout.list_news , paths_1));
	       

			d.dismiss() ;
			 l3.setVisibility(View.VISIBLE) ;
			}
			else if (menu_click == 1 )
			{
				  Intent login = new Intent(Demo.this , Menu_click.class);
				  Bitmap bitmap = null;
		    		Drawable myDrawable = getResources().getDrawable(R.drawable.fb_icon);
		         	bitmap =  ((BitmapDrawable) myDrawable).getBitmap();
		         	 login.putExtra("notifications_list", result);
		             
		             login.putExtra("BitmapImage"+String.valueOf(1), bitmap);
		             d.dismiss() ; 
		             startActivity(login);
		             
		             finish() ; 
		             
			}
			
			//Toast.makeText(getApplicationContext(), ss.toString(),2000).show() ; 
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			 l3.setVisibility(View.GONE) ;
			
			tv.setText("H App4Hindus");
			d = new ProgressDialog (Demo.this) ; 
            
            d.setMessage("Loading") ;
            
            d.show() ; 
            
			
			
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
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
		   	
      	     	
		       if (ss.equals("Videos")==false)
      	    	icon.setImageResource(R.drawable.banner);
      	     	
		       else {
		    	   icon.setVisibility(View.GONE) ; 
		    	   android.view.ViewGroup.LayoutParams params4 = label.getLayoutParams();
			         params4.height = width*8/45;
			         params4.width =  width ;
			         label.setLayoutParams(params4);
			         
			    	
		       }
		         return row;
    		
    	}

   }
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	
		menu_click = 1 ; 
		 new loadsome().execute();
	
	}
	
}
