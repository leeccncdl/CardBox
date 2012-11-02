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
	
	private Integer[] heroDetails = { R.string.hdetail1, R.string.hdetail2, R.string.hdetail3,
		R.string.hdetail4, R.string.hdetail5, R.string.hdetail6, R.string.hdetail7, R.string.hdetail8,
		R.string.hdetail9, R.string.hdetail10, R.string.hdetail11, R.string.hdetail12,
		R.string.hdetail13, R.string.hdetail14, R.string.hdetail15, R.string.hdetail16,
		R.string.hdetail17, R.string.hdetail18, R.string.hdetail19, R.string.hdetail20,
		R.string.hdetail21, R.string.hdetail22, R.string.hdetail23, R.string.hdetail24,
		R.string.hdetail25, R.string.hdetail26, R.string.hdetail27, R.string.hdetail28,
		R.string.hdetail29, R.string.hdetail30, R.string.hdetail31, R.string.hdetail32,
		R.string.hdetail33, R.string.hdetail34, R.string.hdetail35, R.string.hdetail36,
		R.string.hdetail37, R.string.hdetail38, R.string.hdetail39, R.string.hdetail40,
		R.string.hdetail41, R.string.hdetail42, R.string.hdetail43, R.string.hdetail44,
		R.string.hdetail45, R.string.hdetail46, R.string.hdetail47, R.string.hdetail48,
		R.string.hdetail49, R.string.hdetail50, R.string.hdetail51, R.string.hdetail52,
		R.string.hdetail53, R.string.hdetail54, R.string.hdetail55, R.string.hdetail56,
		R.string.hdetail57, R.string.hdetail58, R.string.hdetail59, R.string.hdetail60,
		R.string.hdetail61, R.string.hdetail62, R.string.hdetail63, R.string.hdetail64,
		R.string.hdetail65, R.string.hdetail66, R.string.hdetail67, R.string.hdetail68,
		R.string.hdetail69, R.string.hdetail70, R.string.hdetail71, R.string.hdetail72,
		R.string.hdetail73, R.string.hdetail74, R.string.hdetail75, R.string.hdetail76,
		R.string.hdetail77, R.string.hdetail78, R.string.hdetail79, R.string.hdetail80,
		R.string.hdetail81, R.string.hdetail82, R.string.hdetail83, R.string.hdetail84,
		R.string.hdetail85, R.string.hdetail86, R.string.hdetail87, R.string.hdetail88,
		R.string.hdetail89, R.string.hdetail90, R.string.hdetail91, R.string.hdetail92,
		R.string.hdetail93, R.string.hdetail94, R.string.hdetail95, R.string.hdetail96,
		R.string.hdetail97, R.string.hdetail98, R.string.hdetail99, R.string.hdetail100,
		R.string.hdetail101, R.string.hdetail102, R.string.hdetail103, R.string.hdetail104,
		R.string.hdetail105, R.string.hdetail106, R.string.hdetail107, R.string.hdetail108 };
	
	private Integer[] heroStars = { R.string.hs1, R.string.hs2, R.string.hs3,
		R.string.hs4, R.string.hs5, R.string.hs6, R.string.hs7, R.string.hs8,
		R.string.hs9, R.string.hs10, R.string.hs11, R.string.hs12,
		R.string.hs13, R.string.hs14, R.string.hs15, R.string.hs16,
		R.string.hs17, R.string.hs18, R.string.hs19, R.string.hs20,
		R.string.hs21, R.string.hs22, R.string.hs23, R.string.hs24,
		R.string.hs25, R.string.hs26, R.string.hs27, R.string.hs28,
		R.string.hs29, R.string.hs30, R.string.hs31, R.string.hs32,
		R.string.hs33, R.string.hs34, R.string.hs35, R.string.hs36,
		R.string.hs37, R.string.hs38, R.string.hs39, R.string.hs40,
		R.string.hs41, R.string.hs42, R.string.hs43, R.string.hs44,
		R.string.hs45, R.string.hs46, R.string.hs47, R.string.hs48,
		R.string.hs49, R.string.hs50, R.string.hs51, R.string.hs52,
		R.string.hs53, R.string.hs54, R.string.hs55, R.string.hs56,
		R.string.hs57, R.string.hs58, R.string.hs59, R.string.hs60,
		R.string.hs61, R.string.hs62, R.string.hs63, R.string.hs64,
		R.string.hs65, R.string.hs66, R.string.hs67, R.string.hs68,
		R.string.hs69, R.string.hs70, R.string.hs71, R.string.hs72,
		R.string.hs73, R.string.hs74, R.string.hs75, R.string.hs76,
		R.string.hs77, R.string.hs78, R.string.hs79, R.string.hs80,
		R.string.hs81, R.string.hs82, R.string.hs83, R.string.hs84,
		R.string.hs85, R.string.hs86, R.string.hs87, R.string.hs88,
		R.string.hs89, R.string.hs90, R.string.hs91, R.string.hs92,
		R.string.hs93, R.string.hs94, R.string.hs95, R.string.hs96,
		R.string.hs97, R.string.hs98, R.string.hs99, R.string.hs100,
		R.string.hs101, R.string.hs102, R.string.hs103, R.string.hs104,
		R.string.hs105, R.string.hs106, R.string.hs107, R.string.hs108 };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		findView();
		addListener();
		
		mPosition = getIntent().getIntExtra("position", 88);
		mHeroDetailIv.setImageResource(CardBoxApp.appImages[mPosition]);
		mHeroStarsTv.setText(heroStars[mPosition]);
		mHeroDetailTv.setText(heroDetails[mPosition]);
		mHeroNameTv.setText(CardBoxApp.heroNames[mPosition]);
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
