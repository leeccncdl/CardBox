package cn.zytec.lee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DetailActivity extends Activity implements OnClickListener {
	
	private ImageView back;
	
	private int mPosition;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		mPosition = getIntent().getIntExtra("position", 88);

		back = (ImageView) findViewById(R.id.detail_back_iv);
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
