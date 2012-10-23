package cn.zytec.lee;

import android.app.Application;
import android.content.SharedPreferences;

public class CardBoxApp extends Application {

	public static final int  GALLERYVIEW = 0;
	public static final int  GRIDVIEW = 1;
	public static int showView = GALLERYVIEW;
	
	public static int isYaoyiyaoOpen;
	public static int  backGround;  
	
	@Override
	public void onCreate() {

		super.onCreate();
		
	}
	
}
