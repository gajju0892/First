package com.example.vhp;

import java.util.ArrayList;

import org.apache.http.util.LangUtils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
public class SavedDataBase {
        public static final String KEY_ID = "_id";
        public static final String KEY_FOREIGN = "foreign_key";

        public static final String KEY_HEAD = "head";
	    
	    public static final String KEY_IMAGE = "image_url";
	    public static final String KEY_LANGUAGE = "language";
	    public static final String KEY_TIME = "timestamp";
	    public static final String KEY_CATEGORY = "category";
	    public static final String KEY_PRIORITY = "priority";
	    public static final String KEY_BODY = "body";
	    //private static final String TAG = â€œDBAdapterâ€�;
	    private static final String DATABASE_NAME = "SavedData";
	    private static final String DATABASE_TABLE = "vhp_main_table";
	    private static final int DATABASE_VERSION = 1;
	    private static final String DATABASE_CREATE =
	        "create table vhp_main_table ( id integer primary key autoincrement,foreign_key text,head text not null,body text,image_url text,priority text,language text, timestamp text,category text);";
	    private final Context context;
	    private DatabaseHelper DBHelper;
	    private SQLiteDatabase db;
	    public SavedDataBase(Context ctx)
	    {
	        this.context = ctx;
	        DBHelper = new DatabaseHelper(context);
	    }
	    private static class DatabaseHelper extends SQLiteOpenHelper
	    {
	        DatabaseHelper(Context context)
	        {
	            super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        }
	        @Override
	        public void onCreate(SQLiteDatabase db)
	        {
	            try {
	                db.execSQL(DATABASE_CREATE);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        @Override
	        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	        {
	           
	            db.execSQL("DROP TABLE IF EXISTS contacts");
	            onCreate(db);
	        }
	    }
	    //---opens the database---
	    public SavedDataBase open() throws SQLException
	    {
	        db = DBHelper.getWritableDatabase();
	        return this;
	    }
	    //---closes the database---
	    public void close()
	    {
	        DBHelper.close();
	    }
	    //---insert a contact into the database---
	    public long insert(Front_view n)
	    {
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_FOREIGN,n.id);
	        initialValues.put(KEY_HEAD, n.head);
	        initialValues.put(KEY_BODY, n.body);
	        initialValues.put(KEY_CATEGORY, n.category);
	        initialValues.put(KEY_IMAGE, n.image_url);
	        initialValues.put(KEY_PRIORITY, n.priority);
	        initialValues.put(KEY_LANGUAGE, n.language);
	        initialValues.put(KEY_TIME, n.timestamp);
	        return db.insert(DATABASE_TABLE, null, initialValues);
	    }
	   /* //---deletes a particular contact---
	    public boolean deleteContact(long rowId)
	    {
	        return db.delete(DATABASE_TABLE, KEY_ROWID + â€œ=â€� + rowId, null) > 0;
	    }*/
	    //---retrieves all the contacts---
	    public ArrayList<Front_view> getAll()
	    {
	    	ArrayList<Front_view> list=new ArrayList();
	    	Cursor c= db.query(DATABASE_TABLE, new String[] {KEY_FOREIGN, KEY_HEAD,KEY_BODY,KEY_IMAGE,KEY_CATEGORY,KEY_PRIORITY,KEY_LANGUAGE,KEY_TIME}, null, null, null, null, null);
	    	if (c.moveToFirst()){
	    		   do{
	    			  Front_view n=new Front_view(); 
	    			  n.head=c.getString(c.getColumnIndex(KEY_HEAD));
	    			  n.body=c.getString(c.getColumnIndex(KEY_BODY));
	    			  n.image_url=c.getString(c.getColumnIndex(KEY_IMAGE));
	    			  n.id=c.getString(c.getColumnIndex(KEY_FOREIGN));
	    			  n.category=c.getString(c.getColumnIndex(KEY_CATEGORY));
	    			  n.priority=c.getString(c.getColumnIndex(KEY_PRIORITY));
	    			  n.language=c.getString(c.getColumnIndex(KEY_LANGUAGE));
	    			  n.timestamp=c.getString(c.getColumnIndex(KEY_TIME));
	    		      //String data = cursor.getString(cursor.getColumnIndex("data");
	    		      // do what ever you want here
	                  list.add(n);
	    		   }while(c.moveToNext());
	    		}
	    		c.close();
	    		return list;
	    }
	    //---retrieves a particular contact---
	   /* public Cursor getContact(long rowId) throws SQLException
	    {
	        Cursor mCursor =
	                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
	                KEY_NAME, KEY_EMAIL}, KEY_ROWID + â€œ=â€� + rowId, null,
	                null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;
	    }
	    //---updates a contact---
	    public boolean updateContact(long rowId, String name, String email
	    		 {
	        ContentValues args = new ContentValues();
	        args.put(KEY_NAME, name);
	        args.put(KEY_EMAIL, email);
	        return db.update(DATABASE_TABLE, args, KEY_ROWID + â€œ=â€� + rowId, null) > 0;
	    }*/
}
