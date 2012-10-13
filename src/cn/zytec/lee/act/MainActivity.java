package cn.zytec.lee.act;

import java.util.Random;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.zytec.lee.CardBoxApp;
import cn.zytec.lee.gallery.GalleryFlow;
import cn.zytec.lee.gallery.ImageAdapter;

public class MainActivity extends Activity {

	private ImageAdapter adapter;

	private SensorManager sensorManager;
	private Vibrator vibrator;

	private static final int SENSOR_SHAKE = 10;
	
	private LinearLayout mainBgLl;
	
	private int currentBg = 3;

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
	 * 更新视图 根据显示视图的静态变量，决定显示 画廊视图或者表格视图
	 */
	private void upDateView() {
		switch (CardBoxApp.showView) {
		case CardBoxApp.GALLERYVIEW:
			GalleryFlow galleryFlow = (GalleryFlow) this
					.findViewById(R.id.Gallery);
			galleryFlow.setFadingEdgeLength(0);
			galleryFlow.setSpacing(-100); // 图片之间的间距
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

			Log.i("LOG", "x轴方向的重力加速度" + x + ";y轴方向的重力加速度" + y + "；z轴方向的重力加速度"
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
//				Toast.makeText(MainActivity.this, "检测到摇晃，执行操作！",
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
