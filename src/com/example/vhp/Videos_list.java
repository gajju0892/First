package com.example.vhp;

import java.io.File;
import java.util.ArrayList;



import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Videos_list extends ListActivity


{
	ArrayList <Video> l1 = new ArrayList ();
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState)
	 {
	        super.onCreate(savedInstanceState);
	       
	       DataBase db = new DataBase (this) ;
	       db.open();
	       l1 = db.read_link() ; 
	       db.close(); 
	       
          ArrayList<String> adapter_1 = new ArrayList ();
	       
	       for (Video v : l1)
	       {
	    	   adapter_1.add(v.link) ; 
	       }
	       
	        ArrayAdapter adapter = new   ArrayAdapter (this , android.R.layout.simple_list_item_1,adapter_1) ; 
	        setListAdapter(adapter);
	        
	        getListView().setTextFilterEnabled(true);
	       
	        
	    }
	   
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id)
	 
	 {
	  		// TODO Auto-generated method stub
			
	  		super.onListItemClick(l, v, position, id);
	  	
	  		Uri uri = Uri.parse("http://domain.com/videofile.mp4");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
	 }

}
