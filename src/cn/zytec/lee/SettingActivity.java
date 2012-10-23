package cn.zytec.lee;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SettingActivity extends Activity implements OnClickListener {
	
	private LinearLayout quit;
	private LinearLayout recommend;
	private LinearLayout yaoyiyao;
	private LinearLayout suggestion;
	private LinearLayout about;
	
	private ImageView back;
	private ImageView ivYao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		SharedPreferences sp = this.getSharedPreferences("cardBox",Context.MODE_PRIVATE);  	
		CardBoxApp.isYaoyiyaoOpen = sp.getInt("YAO", 1);
	
		setContentView(R.layout.setting);
		quit = (LinearLayout) findViewById(R.id.setting_quit_ll);
		recommend = (LinearLayout) findViewById(R.id.setting_recommend_ll);
		yaoyiyao = (LinearLayout) findViewById(R.id.setting_yaoyiyao_ll);
		suggestion = (LinearLayout) findViewById(R.id.setting_suggestion_ll);
		about = (LinearLayout) findViewById(R.id.setting_about_ll);
		
		back = (ImageView) findViewById(R.id.setting_back);
		
		ivYao = (ImageView) findViewById(R.id.setting_yaoyiyao_iv);
		refreshIv(CardBoxApp.isYaoyiyaoOpen);
		
		back.setOnClickListener(this);

		recommend.setOnClickListener(this);
		quit.setOnClickListener(this);
		yaoyiyao.setOnClickListener(this);
		suggestion.setOnClickListener(this);
		about.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.setting_back:
			this.finish();
			break;
		case R.id.setting_yaoyiyao_ll:
		{
			CardBoxApp.isYaoyiyaoOpen = (CardBoxApp.isYaoyiyaoOpen == 1? 0:1);
			refreshIv(CardBoxApp.isYaoyiyaoOpen);
		}
				break;
			default:
		}
		
	}
	
	private void refreshIv(int i) {
		switch(i) {
		case 0:
			ivYao.setImageResource(R.drawable.switch_off);
			break;
		case 1:
			ivYao.setImageResource(R.drawable.switch_on);
			break;
			default:
				
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
        SharedPreferences sp = this.getSharedPreferences("cardBox",Context.MODE_PRIVATE);  
        SharedPreferences.Editor edit = sp.edit();  
        edit.putInt("YAO", CardBoxApp.isYaoyiyaoOpen);
        edit.commit();
		
	}
	

}
