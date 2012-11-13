package cn.zytec.lee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.zytec.lee.gallery.GalleryFlow;
import cn.zytec.lee.gallery.ImageAdapter;

import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechConfig.RATE;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

public class MainActivity extends Activity implements OnClickListener,
		RecognizerDialogListener {

	private RecognizerDialog isrDialog = null;

	private ImageAdapter adapter;

	private SensorManager sensorManager;
	private Vibrator vibrator;

	private static final int SENSOR_SHAKE = 10;


	private long mLastBackTime = 0;
	private long TIME_DIFF = 2 * 1000;

	private LinearLayout mainBgLl;
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

		SharedPreferences sp = this.getSharedPreferences("cardBox",
				Context.MODE_PRIVATE);
		CardBoxApp.backGround = sp.getInt("BACK", 0);
		CardBoxApp.isVoiceRecognizeTips = sp.getBoolean("VOICEREC", true);
		CardBoxApp.vibraState = sp.getBoolean("YAO", true);

		setContentView(R.layout.activity_main);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

		adapter = new ImageAdapter(this);
		isrDialog = new RecognizerDialog(this, "appid="
				+ getString(R.string.app_id));

		findView();
		addListener();
		refreshBg(CardBoxApp.backGround);

		upDateView();

	}

	private void addListener() {
		isrDialog.setListener(this);
		micImageView.setOnClickListener(this);
		shareImageView.setOnClickListener(this);
		galleryImageView.setOnClickListener(this);
		gridImageView.setOnClickListener(this);
		settingImageView.setOnClickListener(this);

	}

	private void findView() {
		mainBgLl = (LinearLayout) findViewById(R.id.main_activiry_bgll);
		galleryFlow = (GalleryFlow) this.findViewById(R.id.Gallery);
		gridView = (GridView) this.findViewById(R.id.main_gridview);
		micImageView = (ImageView) findViewById(R.id.main_mic_tv);
		shareImageView = (ImageView) findViewById(R.id.main_share_iv);
		galleryImageView = (ImageView) findViewById(R.id.main_gallery_view_iv);
		gridImageView = (ImageView) findViewById(R.id.main_grid_view_iv);
		settingImageView = (ImageView) findViewById(R.id.main_setting_iv);

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		// 点击转写按钮，跳转到语音转写页面.
		case R.id.main_mic_tv:
			// intent = new Intent(this, IsrDemoActivity.class)
			isrDialog.setSampleRate(RATE.rate16k);
			// 设置Grammar ID
			isrDialog.setEngine(null, null, CardBoxApp.mGrammarId);
			// 显示识别Dialog
			isrDialog.show();

			break;
		// 点击识别按钮，跳转到语音识别页面.
		case R.id.main_share_iv:
			Intent shareIntent = new Intent(Intent.ACTION_SEND);

			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "卡片盒子这个应用很好啊");
			startActivity(Intent.createChooser(shareIntent, getTitle()));

			break;
		// 点击合成按钮，跳转到语音合成页面.
		case R.id.main_gallery_view_iv:
			if (CardBoxApp.currentViewMode != CardBoxApp.GALLERYVIEW) {
				CardBoxApp.currentViewMode = CardBoxApp.GALLERYVIEW;
				upDateView();
			}
			break;
		case R.id.main_grid_view_iv:
			// if(CardBoxApp.currentViewMode != CardBoxApp.GRIDVIEW) {
			// CardBoxApp.currentViewMode = CardBoxApp.GRIDVIEW;
			// upDateView();
			// }
			break;
		case R.id.main_setting_iv:
			System.out.println("Seting");
			intent = new Intent(this, SettingActivity.class);
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return super.dispatchTouchEvent(ev);
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
		SharedPreferences sp = this.getSharedPreferences("cardBox",
				Context.MODE_PRIVATE);
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
		switch (CardBoxApp.currentViewMode) {
		case CardBoxApp.GALLERYVIEW:

			galleryImageView.setEnabled(false);
			gridImageView.setEnabled(true);

			if (gridView != null) {
				gridView.setVisibility(View.GONE);
			}

			galleryFlow.setVisibility(View.VISIBLE);

			galleryFlow.setFadingEdgeLength(0);
			galleryFlow.setSpacing(-100);
			galleryFlow.setAdapter(adapter);

			galleryFlow.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// Toast.makeText(getApplicationContext(),
					// String.valueOf(position), Toast.LENGTH_SHORT)
					// .show();
					Intent intent = new Intent(MainActivity.this,
							DetailActivity.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}

			});
			galleryFlow.setSelection(0);
			break;
		case CardBoxApp.GRIDVIEW:

			galleryImageView.setEnabled(true);
			gridImageView.setEnabled(false);

			if (galleryFlow != null) {
				galleryFlow.setVisibility(View.GONE);
			}

			gridView.setVisibility(View.VISIBLE);
			gridView.setAdapter(adapter);
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// Toast.makeText(getApplicationContext(),
					// String.valueOf(position), Toast.LENGTH_SHORT)
					// .show();
					Intent intent = new Intent(MainActivity.this,
							DetailActivity.class);
					intent.putExtra("position", position);
					startActivity(intent);
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

			if (CardBoxApp.vibraState) {

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
				// Nothing
			}

		}

	};

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:

				Random random = new Random();
				while (true) {
					int bg = random.nextInt(4);
					if (bg != CardBoxApp.currentBg) {
						CardBoxApp.currentBg = bg;
						break;
					}
				}

				switch (CardBoxApp.currentBg) {
				case 0: {
					CardBoxApp.backGround = 0;
					refreshBg(CardBoxApp.backGround);
				}

					break;
				case 1: {
					CardBoxApp.backGround = 1;
					refreshBg(CardBoxApp.backGround);
				}
					break;
				case 2: {
					CardBoxApp.backGround = 2;
					refreshBg(CardBoxApp.backGround);
				}
					break;
				case 3: {
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

	@Override
	public void onEnd(SpeechError arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResults(ArrayList<RecognizerResult> results, boolean isLast) {
		// StringBuilder builder = new StringBuilder();

		String recognize = results.get(0).text;
//		int  i = results.get(0).confidence;
	
//		Toast.makeText(this, i, Toast.LENGTH_LONG).show();
		Resources res = this.getResources();
		String[] heros = res.getStringArray(R.array.heros_for_voice);
		List<String> search = Arrays.asList(heros);

		int position = search.indexOf(recognize);

		if (position > 107) {
			position = position - 108;
		} 
		Intent intent = new Intent(MainActivity.this, DetailActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);

		// results是ArrayList类型的对象，需要对其每一个元素进行解析.
		// for (RecognizerResult recognizerResult : results) {
		// builder.append(recognizerResult.text);
		// builder.append(":");
		// //通过累加value获取识别结果的全部内容.
		// for (HashMap<String, String> hashMap : recognizerResult.semanteme) {
		// for (String value : hashMap.values()) {
		// builder.append(value);
		// Toast.makeText(this, hashMap.toString(), Toast.LENGTH_LONG).show();
		// }
		// }
		// builder.append("(");
		// //带上识别结果得分，仅在识别时有意义，转写的得分均为100.
		// builder.append(recognizerResult.confidence);
		// builder.append(")\n");
		// }
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			long now = new Date().getTime();
//			if (now - mLastBackTime < TIME_DIFF) {
//				return super.onKeyDown(keyCode, event);
//			} else {
//				mLastBackTime = now;
//				Toast.makeText(this, "再点一次将推出", 2000).show();
//			}
//			return true;
//		}
		System.exit(0);
//		ActivityManager activityMgr= (ActivityManager) this.getSystemService(ACTIVITY_SERVICE );
//		activityMgr.restartPackage(getPackageName());
		return true;
	}

}
