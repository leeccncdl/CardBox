package cn.zytec.lee;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

		Integer[] images = { R.drawable.img0001, R.drawable.img0030,
				R.drawable.img0100, R.drawable.img0130, R.drawable.img0200,
				R.drawable.img0230, R.drawable.img0330, R.drawable.img0354 };

		adapter = new ImageAdapter(this, images);
		upDateView();
		
		micImageView = (ImageView) findViewById(R.id.main_mic_tv);
		shareImageView = (ImageView) findViewById(R.id.main_share_iv);
		galleryImageView = (ImageView) findViewById(R.id.main_gallery_view_iv);
		gridImageView = (ImageView) findViewById(R.id.main_grid_view_iv);
		settingImageView = (ImageView) findViewById(R.id.main_grid_view_iv);
		
		galleryImageView.setEnabled(false);
		micImageView.setOnClickListener(this);
		shareImageView.setOnClickListener(this);
		galleryImageView.setOnClickListener(this);
		gridImageView.setOnClickListener(this);
		settingImageView.setOnClickListener(this);

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
//			intent = new Intent(this, TtsDemoActivity.class);
			break;
		case R.id.main_grid_view_iv:
			break;
		case R.id.main_setting_iv:
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}

	/**
	 * ������ͼ �����ʾ��ͼ�ľ�̬������������ʾ ������ͼ���߱����ͼ
	 */
	private void upDateView() {
		switch (CardBoxApp.showView) {
		case CardBoxApp.GALLERYVIEW:
			GalleryFlow galleryFlow = (GalleryFlow) this
					.findViewById(R.id.Gallery);
			galleryFlow.setFadingEdgeLength(0);
			galleryFlow.setSpacing(-100); // ͼƬ֮��ļ��
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
			// TODO Auto-generated method stub
			float[] values = event.values;
			float x = values[0];
			float y = values[1];
			float z = values[2];

			Log.i("LOG", "x�᷽����������ٶ�" + x + ";y�᷽����������ٶ�" + y + "��z�᷽����������ٶ�"
					+ z);

			int medumValue = 19;
			if (Math.abs(x) > medumValue || Math.abs(y) > medumValue
					|| Math.abs(z) > medumValue) {
				vibrator.vibrate(200);
				Message msg = new Message();
				msg.what = SENSOR_SHAKE;
				handler.sendMessage(msg);
			}

		}

	};

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:
//				Toast.makeText(MainActivity.this, "��⵽ҡ�Σ�ִ�в�����",
//						Toast.LENGTH_SHORT).show();
				
				mainBgLl = (LinearLayout)findViewById(R.id.main_activiry_bgll);
				
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


				break;
			}
		}
	};


}
