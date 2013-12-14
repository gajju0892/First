package com.example.vhp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

//import com.example.test123.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

public class Start  extends Activity {
    
    
    ArrayList<Front_view> list = new ArrayList () ;
    ArrayList<Bitmap> list_1 = new ArrayList () ;
  //  Bitmap bitmap ; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    /*    if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish () ; 
        }
        
        */
        setContentView(R.layout.start_1) ; 
        
     
        
        new SomeTask().execute();
        
    }
    
    
    
    /** Inner class for implementing progress bar before fetching data **/
    private class SomeTask extends AsyncTask<String, Void, ArrayList<Front_view> > 
    {
      //  private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        Bitmap[] ob;
        
        ProgressDialog d ; 
        
        @Override
        protected void onPreExecute()
        {
           // Toast.makeText(getApplicationContext(), "abhinav", 2000).show() ;
	d = new ProgressDialog (Start.this) ; 
            
            d.setMessage("Loading") ;
            
         //   d.show() ; 
             
        
        }

        @Override
        protected ArrayList<Front_view> doInBackground(String... params) 
        {
          
            
            String results = "success" ; 
            try {
           
                
                
          /*       x= 22 ; y = 44 ; 
                 
                //Toast.makeText(getApplicationContext(), "hiiiii", Toast.LENGTH_SHORT).show() ;
                
                code for image fetch
                       URL url = new URL("http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg");
                 //try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
                 HttpGet httpRequest = null;

                 httpRequest = new HttpGet(url.toURI());
      
                 HttpClient httpclient = new DefaultHttpClient();
                 HttpResponse response = (HttpResponse) httpclient
                         .execute(httpRequest);
         
                 
                 HttpEntity entity = response.getEntity();
                 BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
                 InputStream inpusdfdf1t = b_entity.getContent();

                 bitmap  = BitmapFactory.decodeStream(input);

                 /*
           */      
                 //Toast.makeText(getApplicationContext(), "success", 2000).show() ;
          //      Toast.makeText(getApplicationContext(), results, 2000).show() ;
             /*    ob=new Bitmap[list.size()];
                 for(int i=0;i<list.size();i++)
                 {
                     URL url = new URL(list.get(i).image_url);
                     //try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
                     HttpGet httpRequest = null;

                     httpRequest = new HttpGet(url.toURI());
          
                     HttpClient httpclient = new DefaultHttpClient();
                     HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
             
                     
                     HttpEntity entity = response.getEntity();
                     BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
                     InputStream input = b_entity.getContent();

                     ob[i]  = BitmapFactory.decodeStream(input);

                 }*/
                 Connect c = new Connect () ; 
                 list = c.front_view("http://freakkydevill.comlu.com/vhp_recent.php") ; 
                
              //   Toast.makeText(getApplicationContext(), list.toString(), 1500).show();
                
                
                 }
              catch (Exception ex) {
                    
                 
                  results = "faild" ; 
             //    Toast.makeText(getApplicationContext(), ex.toString(), 2000).show() ; 
                 
             }
            
            for (int i = 0 ; i < list.size() ; i++)
            {
                if (list.get(i).image_url != null)
                {
                    
                }
            }
            
            return list  ;
        }

        @Override
        protected void onPostExecute(ArrayList<Front_view> result)
        {
        	//d.dismiss() ;
            //img.setImageBitmap(bitmap);
           Intent login = new Intent (Start.this , Menu_click.class);
           // Parcelable[] output = new Parcelable[list.size()];
           /*  for (int i=0; i<list.size(); i++) {
                login.putExtra("bitmap"+String.valueOf(i),ob[i]);
            }*/
           Bitmap bitmap = null;
    		Drawable myDrawable = getResources().getDrawable(R.drawable.fb_icon);
         	bitmap =  ((BitmapDrawable) myDrawable).getBitmap();
         	
          //  login.putExtra("BitmapArray", output);
            login.putExtra("notifications_list", list);
           
            login.putExtra("BitmapImage"+String.valueOf(1), bitmap);
            //login.putExtra("data",  list ) ; 
            startActivity(login);
            finish() ; 
            
         
        }

        
    }


}