package com.example.vhp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Photo_list extends Activity  
{
	
	String[] subs = {"Use Temple funds for Uttarakhand reconstruction – VHP\n","Use Temple funds for Uttarakhand reconstruction – VHP \n",
			 "Use Temple funds for Uttarakhand reconstruction – VHP \n",
			 "Use Temple funds for Uttarakhand reconstruction – VHP \n", "Use Temple funds for Uttarakhand reconstruction – VHP\n"} ; 

	int []  arr_images = {R.drawable.image_4,R.drawable.kerala_1,R.drawable.image_4,R.drawable.image_5,R.drawable.image_6,R.drawable.image};

	String []  arr_images_1 = {"R.drawable.image_3","R.drawable.image_3","R.drawable.image_4","R.drawable.image_5","R.drawable.image_6","R.drawable.image"};

	
	int width , height ; 
	
	Bitmap bmp [] = new Bitmap [4] ; 
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView (R.layout.photo_list) ;
		
		  Display display = getWindowManager().getDefaultDisplay();
	        Point size = new Point();
	        display.getSize(size);
	        width = size.x;
	        height = size.y;
	      
	        
		ListView lv = (ListView) findViewById(R.id.lv_pic) ; 
		
		
		lv.setAdapter(new MyAdapter(Photo_list.this, R.layout.photos_1, subs));
		// setListAdapter(adapter);
		
		convert () ; 
	     
		
lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			
			

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				
					Intent i = new Intent (Photo_list.this,Image_full_display.class);
			       
					Bundle basket = new Bundle ();
					basket.putInt("image", arr_images[position]);
				   
				    i.putExtras(basket);
				   
				    
					startActivity (i) ; 		
			
			}
		});
		
		
		
	}
	
	void convert ()
	{
	    bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.image_3);
		
		bmp[1] = resizing (bmp[0],width/5,height/5) ; 
	}
	
	
	 public class MyAdapter extends ArrayAdapter<String>{

	    	public MyAdapter(Context context, int textViewResourceId,	String[] objects) {
	    		super(context, textViewResourceId, objects);
	    		
	    	}

	    	@Override
	    	public View getDropDownView(int position, View convertView,ViewGroup parent) {
	    		return getCustomView(position, convertView, parent);
	    	}

	    	@Override
	    	public View getView(int position, View convertView, ViewGroup parent) {
	    		return getCustomView(position, convertView, parent);
	    	}

	    	public View getCustomView(int position, View convertView, ViewGroup parent) {
				
	    		LayoutInflater inflater=getLayoutInflater();
		    	View row=inflater.inflate(R.layout.photos_1, parent, false);
		    	 
                 TextView label=(TextView)row.findViewById(R.id.company);
		    	
		    	android.view.ViewGroup.LayoutParams params3 = label.getLayoutParams();
		         params3.height = width*10/45;
		         params3.width =  width*7/10;
		         label.setLayoutParams(params3);
		         
		    	
		    	label.setText(subs[position]);
 		    	ImageView icon=(ImageView)row.findViewById(R.id.image_pic);
		    	android.view.ViewGroup.LayoutParams params2 = icon.getLayoutParams();
		         params2.height = width*10/45;
		         params2.width =  width*44/100 ;
		         icon.setLayoutParams(params2);
		   	
		     	icon.setImageResource(arr_images[position]);
		       
		     	return row;
	    		
	    	}
	
       }

	 public static Bitmap resizing (Bitmap bmp , int height ,int width )
		{
			int bmp_width = bmp.getWidth();
			int bmp_height = bmp.getHeight();
			float scale_width = ((float)width)/bmp_width ; 
			float scale_float = ((float)height)/bmp_height ; 
			Matrix new_matrix = new Matrix ();
			Bitmap resized_bmp = Bitmap.createBitmap(bmp, 0, 0, width,
					height,new_matrix,false);
			return resized_bmp ;
			
		}
	 @Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			
			Intent i = new Intent (this , Menu_click.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			finish();
			startActivity(i);	}

}