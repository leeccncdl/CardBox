package cn.zytec.lee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

		CardBoxApp.getInstance().addActivity(this);
		
		setContentView(R.layout.setting);
		quit = (LinearLayout) findViewById(R.id.setting_quit_ll);
		recommend = (LinearLayout) findViewById(R.id.setting_recommend_ll);
		yaoyiyao = (LinearLayout) findViewById(R.id.setting_yaoyiyao_ll);
		suggestion = (LinearLayout) findViewById(R.id.setting_suggestion_ll);
		about = (LinearLayout) findViewById(R.id.setting_about_ll);
		
		back = (ImageView) findViewById(R.id.setting_back);
		
		ivYao = (ImageView) findViewById(R.id.setting_yaoyiyao_iv);
		refreshIv(CardBoxApp.vibraState);
		
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
			CardBoxApp.vibraState = !CardBoxApp.vibraState;
			refreshIv(CardBoxApp.vibraState);
		}
				break;
		case R.id.setting_recommend_ll:
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "卡片盒子这个应用很好啊");//推荐应用叙述
			startActivity(Intent.createChooser(shareIntent, getTitle()));
			break;
		case R.id.setting_suggestion_ll:
			Intent intentSug = new Intent(SettingActivity.this,
					SuggestionActivity.class);
			startActivity(intentSug);
			break;
		case R.id.setting_about_ll:
			Intent intentAbo = new Intent(SettingActivity.this,
					AboutActivity.class);
			startActivity(intentAbo);
			break;
		case R.id.setting_quit_ll:
			CardBoxApp.getInstance().exit();
			break;
			default:
		}
		
	}
	
	private void refreshIv(boolean isOpen) {
		
		if(!isOpen) {
			ivYao.setImageResource(R.drawable.switch_off);
		} else {
			ivYao.setImageResource(R.drawable.switch_on);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
        SharedPreferences sp = this.getSharedPreferences("cardBox",Context.MODE_PRIVATE);  
        SharedPreferences.Editor edit = sp.edit();  
        edit.putBoolean("YAO", CardBoxApp.vibraState);
        edit.commit();
		
	}
	

}
