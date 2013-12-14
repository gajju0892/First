package com.example.vhp;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application{
	
	
	public static Context ap;
	public  void onCreate()
	{
		ap=this;
	}

}
