package cn.zytec.lee;

import android.app.Application;

public class CardBoxApp extends Application {

	public static final int  GALLERYVIEW = 0;
	public static final int  GRIDVIEW = 1;
	public static int showView = GALLERYVIEW;
	
	public static boolean isYaoyiyaoOpen;
	public static int  backGround;  
	
	public static boolean isVoiceRecognizeTips;
	
	public static final String mGrammarId = "3a28d9e69e06e425995bb3722898ef02";
	
	@Override
	public void onCreate() {

		super.onCreate();
		
	}
	
}
