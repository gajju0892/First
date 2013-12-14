package com.example.vhp;

import java.io.Serializable;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Front_view implements Serializable {
	
	
	
	String id , language , head , body , image_url , priority , category , timestamp ; 
	
	Front_view ()
	{
		id = "" ;
		language = "" ;
		head = "" ;
		body = " " ;  
		image_url = "" ; 
		priority = " " ; 
		category = " " ;
		timestamp = "" ;
	}
/*
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(id);
		dest.writeString(language);
		dest.writeString(head);
		dest.writeString(body);
		dest.writeString(image_url);
		dest.writeString(priority);
		dest.writeString(category);
		dest.writeString(timestamp);
		
	/*	Bundle b = new Bundle();
		b.putParcelableArrayList("items", items);
		dest.writeBundle(b);*/
		
	//}*/
  
}
