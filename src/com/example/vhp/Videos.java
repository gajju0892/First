package com.example.vhp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Videos extends ListActivity

{
	ArrayList<String> adapter_1 ; 
	

	ArrayList<News> l1 = new ArrayList ();
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState)
	 {
	        super.onCreate(savedInstanceState);
	       
	       DataBase db = new DataBase (this) ;
	       db.open();
	       l1 = db.read_news() ; 
	       db.close(); 
	       
	       adapter_1 = new ArrayList ();
	       
	       for (News n : l1)
	       {
	    	   adapter_1.add(n.head+"\n") ; 
	       }
	    	   
	    	   
	       
	        setListAdapter(new MyAdapter(Videos.this, R.layout.photos_1, adapter_1));
	        
	        getListView().setTextFilterEnabled(true);
	       
	        setTitle("adf as afsa");
	       
	        
	    }
	   
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id)
	 
	 {
	  		// TODO Auto-generated method stub
			
	  		super.onListItemClick(l, v, position, id);
	  	
	  			
	  	 /*   Bundle basket = new Bundle ();
			basket.putStringArray("data",new String []{l1.get(position).head.toString(),l1.get(position).content.toString()} );
		    Intent i = new Intent(Videos.this,New_display.class);
		    i.putExtras(basket);
		    startActivity(i);
		   -*/
	  		super.onListItemClick(l, v, position, id);
		  	
	  		Uri uri = Uri.parse("http://domain.com/videofile.mp4");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
		   
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

	    	public View getCustomView(int position, View convertView, ViewGroup parent) {

	    		LayoutInflater inflater=getLayoutInflater();
		    	View row=inflater.inflate(R.layout.list_news, parent, false);
		    	TextView label=(TextView)row.findViewById(R.id.news_custom_1);
		    	 label.setText(adapter_1.get(position));
                    
		    	 
		    	 if (position == 0 )
		    	 {
		    		 label.setBackgroundColor(Color.rgb(255,255,255));
		    		 label.setText("Videos\n");
		    		 
		    		 label.setTextColor(Color.rgb(0,0,0)) ;
		    		 label.setGravity(Gravity.CENTER_HORIZONTAL) ; 
		    		 
		    	 }
		    	 
		    	 
		    	 
		    /*	 if (position % 2 == 0 )
		    	 label.setBackgroundColor(Color.rgb(194 , 222, 244));
		    	 else
		    		 label.setBackgroundColor(Color.WHITE) ;
		  		*/    	return row;
	    		//a8fiff
	    	}

	   }


}
