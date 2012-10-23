package cn.zytec.lee;

import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.zytec.lee.CardBoxApp;
import cn.zytec.lee.gallery.GalleryFlow;
import cn.zytec.lee.gallery.ImageAdapter;
import cn.zytec.lee.voice.IsrDemoActivity;

public class MainActivity extends Activity implements OnClickListener {

	private ImageAdapter adapter;

	private SensorManager sensorManager;
	private Vibrator vibrator;

	private static final int SENSOR_SHAKE = 10;
	
	private LinearLayout mainBgLl;
	
	private int currentBg = 3;
	
	private ImageView micImageView;
	private ImageView shareImageView;
	private ImageView galleryImageView;
	private ImageView gridImageView;
	private ImageView settingImageView;
	private GalleryFlow galleryFlow;
	private GridView gridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences sp = this.getSharedPreferences("cardBox",Context.MODE_PRIVATE);  	
		CardBoxApp.backGround = sp.getInt("BACK", 0);
		
		setContentView(R.layout.activity_main);

		mainBgLl = (LinearLayout) findViewById(R.id.main_activiry_bgll);
		refreshBg(CardBoxApp.backGround);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

		Integer[] images = { R.drawable.h1, R.drawable.h2,R.drawable.h3, R.drawable.h4, R.drawable.h5,R.drawable.h6,
							R.drawable.h7, R.drawable.h8,R.drawable.h9, R.drawable.h10, R.drawable.h11,R.drawable.h12,
							R.drawable.h13, R.drawable.h14,R.drawable.h15, R.drawable.h16, R.drawable.h17,R.drawable.h18,
							R.drawable.h19, R.drawable.h20,R.drawable.h21, R.drawable.h22, R.drawable.h23,R.drawable.h24,
							R.drawable.h25, R.drawable.h26,R.drawable.h27, R.drawable.h28, R.drawable.h29,R.drawable.h30,
							R.drawable.h31, R.drawable.h32,R.drawable.h33, R.drawable.h34, R.drawable.h35,R.drawable.h36,
							R.drawable.h37, R.drawable.h38,R.drawable.h39, R.drawable.h40, R.drawable.h41,R.drawable.h42,
							R.drawable.h43, R.drawable.h44,R.drawable.h45, R.drawable.h46, R.drawable.h47,R.drawable.h48,
							R.drawable.h49, R.drawable.h50,R.drawable.h51, R.drawable.h52, R.drawable.h53,R.drawable.h54,
							R.drawable.h55, R.drawable.h56,R.drawable.h57, R.drawable.h58, R.drawable.h59,R.drawable.h60,
							R.drawable.h61, R.drawable.h62,R.drawable.h63, R.drawable.h64, R.drawable.h65,R.drawable.h66,
							R.drawable.h67, R.drawable.h68,R.drawable.h69, R.drawable.h70, R.drawable.h71,R.drawable.h72,
							R.drawable.h73, R.drawable.h74,R.drawable.h75, R.drawable.h76, R.drawable.h77,R.drawable.h78,
							R.drawable.h79, R.drawable.h80,R.drawable.h81, R.drawable.h82, R.drawable.h83,R.drawable.h84,
							R.drawable.h85, R.drawable.h86,R.drawable.h87, R.drawable.h88, R.drawable.h89,R.drawable.h90,
							R.drawable.h91, R.drawable.h92,R.drawable.h93, R.drawable.h94, R.drawable.h95,R.drawable.h96,
							R.drawable.h97, R.drawable.h98,R.drawable.h99, R.drawable.h100, R.drawable.h101,R.drawable.h102,
							R.drawable.h103, R.drawable.h104,R.drawable.h105, R.drawable.h106, R.drawable.h107,R.drawable.h108
							};


		
		micImageView = (ImageView) findViewById(R.id.main_mic_tv);
		shareImageView = (ImageView) findViewById(R.id.main_share_iv);
		galleryImageView = (ImageView) findViewById(R.id.main_gallery_view_iv);
		gridImageView = (ImageView) findViewById(R.id.main_grid_view_iv);
		settingImageView = (ImageView) findViewById(R.id.main_setting_iv);
		

		micImageView.setOnClickListener(this);
		shareImageView.setOnClickListener(this);
		galleryImageView.setOnClickListener(this);
		gridImageView.setOnClickListener(this);
		settingImageView.setOnClickListener(this);

		adapter = new ImageAdapter(this, images);
		galleryFlow = (GalleryFlow) this.findViewById(R.id.Gallery);
		gridView = (GridView)this.findViewById(R.id.main_gridview);
		upDateView();
	}
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		//点击转写按钮，跳转到语音转写页面.
		case R.id.main_mic_tv:
			intent = new Intent(this, IsrDemoActivity.class);
			break;
		//点击识别按钮，跳转到语音识别页面.
		case R.id.main_share_iv:
	         Intent shareIntent=new Intent(Intent.ACTION_SEND);
	         
	         shareIntent.setType("text/plain");
	         shareIntent.putExtra(Intent.EXTRA_SUBJECT, "分享");
	         shareIntent.putExtra(Intent.EXTRA_TEXT, "卡片盒子这个应用很好啊");
	         startActivity(Intent.createChooser(shareIntent, getTitle()));

			break;
		//点击合成按钮，跳转到语音合成页面.
		case R.id.main_gallery_view_iv:
			if(CardBoxApp.showView != CardBoxApp.GALLERYVIEW) {
				CardBoxApp.showView = CardBoxApp.GALLERYVIEW;
				upDateView();
			}
			break;
		case R.id.main_grid_view_iv:
			if(CardBoxApp.showView != CardBoxApp.GRIDVIEW) {
				CardBoxApp.showView = CardBoxApp.GRIDVIEW;
				upDateView();
			}
			break;
		case R.id.main_setting_iv:
			System.out.println("Seting");
			intent = new Intent(this, SettingActivity.class);
			break;
		}
		if(intent != null) {
			startActivity(intent);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (sensorManager != null) {
			sensorManager.registerListener(sensorEventListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sensorManager != null) {
			sensorManager.unregisterListener(sensorEventListener);
		}
        SharedPreferences sp = this.getSharedPreferences("cardBox",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();  
        edit.putInt("BACK", CardBoxApp.backGround);
        edit.commit();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}


	private void upDateView() {
		switch (CardBoxApp.showView) {
		case CardBoxApp.GALLERYVIEW:
			
			galleryImageView.setEnabled(false);
			gridImageView.setEnabled(true);
			
			if(gridView!=null) {
				gridView.setVisibility(View.GONE);
			}
			
			galleryFlow.setVisibility(View.VISIBLE);
			
			galleryFlow.setFadingEdgeLength(0);
			galleryFlow.setSpacing(-100); 
			galleryFlow.setAdapter(adapter);

			galleryFlow.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getApplicationContext(),
							String.valueOf(position), Toast.LENGTH_SHORT)
							.show();
				}

			});
			galleryFlow.setSelection(0);
			break;
		case CardBoxApp.GRIDVIEW:
			
			galleryImageView.setEnabled(true);
			gridImageView.setEnabled(false);
			
			if(galleryFlow!=null) {
				galleryFlow.setVisibility(View.GONE);
			}
			
			gridView.setVisibility(View.VISIBLE);
			gridView.setAdapter(adapter);
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getApplicationContext(),
							String.valueOf(position), Toast.LENGTH_SHORT)
							.show();
					
				}
			});
			break;
		default:
			break;
		}
	}

	private SensorEventListener sensorEventListener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if(CardBoxApp.isYaoyiyaoOpen == 1) {
				
				float[] values = event.values;
				float x = values[0];
				float y = values[1];
				float z = values[2];
				
				int medumValue = 19;
				if (Math.abs(x) > medumValue || Math.abs(y) > medumValue
						|| Math.abs(z) > medumValue) {
					vibrator.vibrate(200);
					Message msg = new Message();
					msg.what = SENSOR_SHAKE;
					handler.sendMessage(msg);
				}
			} else {
				//Nothing
			}

		}

	};

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:

				
				Random random = new Random();
				while(true) {
					int bg = random.nextInt(4);
					if( bg!= currentBg) {
						currentBg = bg;
						break;
					}
				}
				

				switch(currentBg) {
					case 0:
					{
						CardBoxApp.backGround = 0;
						refreshBg(CardBoxApp.backGround);
					}
						
						break;
					case 1:
					{
						CardBoxApp.backGround = 1;
						refreshBg(CardBoxApp.backGround);
					}
						break;
					case 2:
					{
						CardBoxApp.backGround = 2;
						refreshBg(CardBoxApp.backGround);
					}
						break;
					case 3:
					{
						CardBoxApp.backGround = 3;
						refreshBg(CardBoxApp.backGround);
					}
						break;
					default:
						break;
				
				}

				break;
			}
		}
	};

	private void refreshBg(int bgOrder) {
		switch (bgOrder) {
		case 0:
			mainBgLl.setBackgroundResource(R.drawable.main_bg_1);
			break;
		case 1:
			mainBgLl.setBackgroundResource(R.drawable.main_bg_2);
			break;
		case 2:
			mainBgLl.setBackgroundResource(R.drawable.main_bg_3);
			break;
		case 3:
			mainBgLl.setBackgroundResource(R.drawable.main_bg_4);
			break;
		default:
			break;
		}
	}

	
	
}
