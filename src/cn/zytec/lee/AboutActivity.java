package cn.zytec.lee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class AboutActivity extends Activity implements OnClickListener {

	private ImageView back;
	private Button button;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		back = (ImageView) findViewById(R.id.about_back);
		button = (Button) findViewById(R.id.introduce_bt);
		
		back.setOnClickListener(this);
		button.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about_back:
			finish();
			break;
		case R.id.introduce_bt:
			SharedPreferences sp = this.getSharedPreferences("cardBox",
					Context.MODE_PRIVATE);
			SharedPreferences.Editor edit = sp.edit();
			edit.putBoolean("INTRODUCE", true);
			edit.commit();
			Intent intent = new Intent(AboutActivity.this, IntroduceActivity.class);
			startActivity(intent);
			finish();
			break;
			
		default:
			break;
		}
		
	}
	
}
