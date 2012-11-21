package cn.zytec.lee;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import cn.zytec.lee.utils.StrictModeWrapper;

public class CardBoxApp extends Application {
	
	private List<Activity> activityList  = new LinkedList<Activity>();
	private static CardBoxApp instance;
	
	public static int displayWidth;
	public static int displayHeight;

	public static int currentBg = 0;
	
	public static final int GALLERYVIEW = 0;
	public static final int GRIDVIEW = 1;
	public static int currentViewMode = GALLERYVIEW;

	public static boolean vibraState;
	public static int backGround;
	public static boolean isVoiceRecognizeTips;

	public static final String mGrammarId = "3a28d9e69e06e425995bb3722898ef02";

	public static Integer[] appImages = { R.drawable.h1, R.drawable.h2,
			R.drawable.h3, R.drawable.h4, R.drawable.h5, R.drawable.h6,
			R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
			R.drawable.h11, R.drawable.h12, R.drawable.h13, R.drawable.h14,
			R.drawable.h15, R.drawable.h16, R.drawable.h17, R.drawable.h18,
			R.drawable.h19, R.drawable.h20, R.drawable.h21, R.drawable.h22,
			R.drawable.h23, R.drawable.h24, R.drawable.h25, R.drawable.h26,
			R.drawable.h27, R.drawable.h28, R.drawable.h29, R.drawable.h30,
			R.drawable.h31, R.drawable.h32, R.drawable.h33, R.drawable.h34,
			R.drawable.h35, R.drawable.h36, R.drawable.h37, R.drawable.h38,
			R.drawable.h39, R.drawable.h40, R.drawable.h41, R.drawable.h42,
			R.drawable.h43, R.drawable.h44, R.drawable.h45, R.drawable.h46,
			R.drawable.h47, R.drawable.h48, R.drawable.h49, R.drawable.h50,
			R.drawable.h51, R.drawable.h52, R.drawable.h53, R.drawable.h54,
			R.drawable.h55, R.drawable.h56, R.drawable.h57, R.drawable.h58,
			R.drawable.h59, R.drawable.h60, R.drawable.h61, R.drawable.h62,
			R.drawable.h63, R.drawable.h64, R.drawable.h65, R.drawable.h66,
			R.drawable.h67, R.drawable.h68, R.drawable.h69, R.drawable.h70,
			R.drawable.h71, R.drawable.h72, R.drawable.h73, R.drawable.h74,
			R.drawable.h75, R.drawable.h76, R.drawable.h77, R.drawable.h78,
			R.drawable.h79, R.drawable.h80, R.drawable.h81, R.drawable.h82,
			R.drawable.h83, R.drawable.h84, R.drawable.h85, R.drawable.h86,
			R.drawable.h87, R.drawable.h88, R.drawable.h89, R.drawable.h90,
			R.drawable.h91, R.drawable.h92, R.drawable.h93, R.drawable.h94,
			R.drawable.h95, R.drawable.h96, R.drawable.h97, R.drawable.h98,
			R.drawable.h99, R.drawable.h100, R.drawable.h101, R.drawable.h102,
			R.drawable.h103, R.drawable.h104, R.drawable.h105, R.drawable.h106,
			R.drawable.h107, R.drawable.h108 };

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			StrictModeWrapper.init(this);
		} catch (Throwable throwable) {
			Log.v("StrictMode", "... is not available. Punting...");
		}

	}
	
	public void addActivity(Activity act) {
		activityList.add(act);
	}
	
	public void exit() {
		for(Activity activity:activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	private CardBoxApp() {
		
	}
	
	public static CardBoxApp getInstance() {
		if(instance == null) {
			instance = new CardBoxApp();
		}
		return instance;
	}
}
