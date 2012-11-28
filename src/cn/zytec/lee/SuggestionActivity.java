package cn.zytec.lee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SuggestionActivity extends Activity implements OnClickListener {
	
	private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggestion);
		
		back = (ImageView) findViewById(R.id.suggestion_back);
		back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.suggestion_back:
			finish();
			break;

		default:
			break;
		}
	}
	
}
