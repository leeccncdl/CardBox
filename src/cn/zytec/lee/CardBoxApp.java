package cn.zytec.lee;

import android.app.Application;

public class CardBoxApp extends Application {

	public static final int  GALLERYVIEW = 0;
	public static final int  GRIDVIEW = 1;
	public static int showView = GALLERYVIEW;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
}
