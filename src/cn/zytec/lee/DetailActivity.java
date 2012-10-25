package cn.zytec.lee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity implements OnClickListener {
	
	private ImageView back;
	
	private ImageView mHeroDetailIv;
	private TextView mHeroStarsTv;
	private TextView mHeroDetailTv;
	private TextView mHeroPositionTv;
	private TextView mHeroNameTv;
	
	private int mPosition;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		findView();
		addListener();
		
		mPosition = getIntent().getIntExtra("position", 88);
		mHeroDetailIv.setImageResource(CardBoxApp.arrayImages[mPosition]);
		mHeroStarsTv.setText(CardBoxApp.arrayStars[mPosition]);
		mHeroDetailTv.setText(CardBoxApp.arrayDetails[mPosition]);
		mHeroNameTv.setText(CardBoxApp.arrayNames[mPosition]);
//		mHeroDetailTv.setText(CardBoxApp.arrayNames[mPosition]);
		
	}
	
	private void findView() {
		back = (ImageView) findViewById(R.id.detail_back_iv);
		mHeroDetailIv = (ImageView) findViewById(R.id.detail_hero_iv);
		mHeroStarsTv = (TextView) findViewById(R.id.detail_star_tv);
		mHeroDetailTv = (TextView) findViewById(R.id.detail_hero_tv);
		mHeroPositionTv = (TextView) findViewById(R.id.detail_hero_position_tv);
		mHeroNameTv = (TextView) findViewById(R.id.detail_name_tv);
	}
	
	private void addListener() {
		back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_back_iv:
			finish();
			break;
		default:
			break;
		}
		
	}
}
