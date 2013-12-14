package com.example.vhp;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase 
{
	public static final  String KEY_ROWID = "_id";
	public static final  String KEY_TYPE = "news_video";
	public static final  String KEY_HEAD = "heading_link";
    public static final  String KEY_EXTRA = "content_extras";
    
    private static final String DATABASE_NAME = "local_database";
	private static final  String DATABASE_TABLE = "contents";
	private static final int  DATABASE_VERSION = 1 ;
    
	private static class DBhelper extends SQLiteOpenHelper
	{

		public DBhelper(Context context)
		{
			super(context, DATABASE_NAME, null,DATABASE_VERSION );
		
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+ DATABASE_TABLE +" ("+
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TYPE+" TEXT NOT NULL, "+
					 KEY_HEAD + " TEXT NOT NULL, "
			+ KEY_EXTRA + " TEXT NOT NULL);"
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) 
		{
			// TODO Auto-generated method stub
			
		   arg0.execSQL("DROP IF TABLE EXISTS "+ DATABASE_TABLE);
		
		   onCreate(arg0);
			
		}
		
	}
	
	private Context ourcontext ;
	private DBhelper ourhelper ;
	private SQLiteDatabase  ourdatabase  ;
		
	
    public DataBase (Context c )
    {
    	ourcontext = c ;
    }
    
    public DataBase open() throws SQLException  
    {
    	ourhelper = new DBhelper (ourcontext);
    	ourdatabase = ourhelper.getWritableDatabase();
        return this ; 
    }
    
    public void close ()
    {
    	ourhelper.close();
    }

    
    public long createEntry(String type , String head ,String extra) 
    {
		// TODO Auto-generated method stub
    	ContentValues cv = new ContentValues ();
		cv.put(KEY_TYPE, type);
		cv.put(KEY_HEAD, head);
		cv.put(KEY_EXTRA, extra);
		
		return ourdatabase.insert(DATABASE_TABLE, null, cv);

   }
    
    // foe news type will be 0 

    public ArrayList<News> read_news  ()
    {
    	String [] columns = new String [] {KEY_ROWID,KEY_TYPE,KEY_HEAD,KEY_EXTRA};
    	
    	Cursor c =  ourdatabase.query(DATABASE_TABLE, columns, null,  null, null,null, null); 
    	 
    	News news ; 
    	ArrayList<News> l = new ArrayList<News> () ; 
    	 
    	int type = c.getColumnIndex(KEY_TYPE);
    	int head = c.getColumnIndex(KEY_HEAD);
    	int extra = c.getColumnIndex(KEY_EXTRA);
    	 
    	 for (c.moveToFirst(); !c.isAfterLast() ; c.moveToNext())
    	 {
    		if (c.getString(type).equals("0")) 
    		{
    			news = new News () ; 
    			news.head  = c.getString(head);
        		news.content = c.getString(extra);
        		l.add(news) ;
    		}
    	 }
    	 
    	 return l ; 
    	
    }

    // for video the type will be 2 
    public ArrayList <Video> read_link ()
    {
        String [] columns = new String [] {KEY_ROWID,KEY_TYPE,KEY_HEAD,KEY_EXTRA};
    	
    	Cursor c =  ourdatabase.query(DATABASE_TABLE, columns, null,  null, null,null, null); 
    	 
    	Video video  ; 
    	ArrayList<Video> l = new ArrayList () ; 
    	 
    	int type = c.getColumnIndex(KEY_TYPE);
    	int link = c.getColumnIndex(KEY_HEAD);
    	int extra = c.getColumnIndex(KEY_EXTRA);
    	 
    	 for (c.moveToFirst(); !c.isAfterLast() ; c.moveToNext())
    	 {
    		if (c.getString(type).equals("2")) 
    		{
    			video = new Video () ; 
    			video.link = c.getString(link) ;
    			video.attributes = c.getString(extra);
    			l.add(video) ;
    		}
    	 }
    	 
    	 return l ; 
    	
    }
}
