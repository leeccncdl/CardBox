package cn.zytec.lee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailActivity extends Activity implements OnClickListener {

	private ImageView back;

	private ImageView mHeroDetailIv;
	private TextView mHeroStarsTv;
	private TextView mHeroDetailTv;
	private TextView mHeroPositionTv;
	private TextView mHeroNameTv;

	private LinearLayout background; 
	
	private int mPosition;

	private Integer[] heroDetails = { R.string.hdetail1, R.string.hdetail2,
			R.string.hdetail3, R.string.hdetail4, R.string.hdetail5,
			R.string.hdetail6, R.string.hdetail7, R.string.hdetail8,
			R.string.hdetail9, R.string.hdetail10, R.string.hdetail11,
			R.string.hdetail12, R.string.hdetail13, R.string.hdetail14,
			R.string.hdetail15, R.string.hdetail16, R.string.hdetail17,
			R.string.hdetail18, R.string.hdetail19, R.string.hdetail20,
			R.string.hdetail21, R.string.hdetail22, R.string.hdetail23,
			R.string.hdetail24, R.string.hdetail25, R.string.hdetail26,
			R.string.hdetail27, R.string.hdetail28, R.string.hdetail29,
			R.string.hdetail30, R.string.hdetail31, R.string.hdetail32,
			R.string.hdetail33, R.string.hdetail34, R.string.hdetail35,
			R.string.hdetail36, R.string.hdetail37, R.string.hdetail38,
			R.string.hdetail39, R.string.hdetail40, R.string.hdetail41,
			R.string.hdetail42, R.string.hdetail43, R.string.hdetail44,
			R.string.hdetail45, R.string.hdetail46, R.string.hdetail47,
			R.string.hdetail48, R.string.hdetail49, R.string.hdetail50,
			R.string.hdetail51, R.string.hdetail52, R.string.hdetail53,
			R.string.hdetail54, R.string.hdetail55, R.string.hdetail56,
			R.string.hdetail57, R.string.hdetail58, R.string.hdetail59,
			R.string.hdetail60, R.string.hdetail61, R.string.hdetail62,
			R.string.hdetail63, R.string.hdetail64, R.string.hdetail65,
			R.string.hdetail66, R.string.hdetail67, R.string.hdetail68,
			R.string.hdetail69, R.string.hdetail70, R.string.hdetail71,
			R.string.hdetail72, R.string.hdetail73, R.string.hdetail74,
			R.string.hdetail75, R.string.hdetail76, R.string.hdetail77,
			R.string.hdetail78, R.string.hdetail79, R.string.hdetail80,
			R.string.hdetail81, R.string.hdetail82, R.string.hdetail83,
			R.string.hdetail84, R.string.hdetail85, R.string.hdetail86,
			R.string.hdetail87, R.string.hdetail88, R.string.hdetail89,
			R.string.hdetail90, R.string.hdetail91, R.string.hdetail92,
			R.string.hdetail93, R.string.hdetail94, R.string.hdetail95,
			R.string.hdetail96, R.string.hdetail97, R.string.hdetail98,
			R.string.hdetail99, R.string.hdetail100, R.string.hdetail101,
			R.string.hdetail102, R.string.hdetail103, R.string.hdetail104,
			R.string.hdetail105, R.string.hdetail106, R.string.hdetail107,
			R.string.hdetail108 };
	
//	public static Integer[] heroNames = { R.string.h1, R.string.h2, R.string.h3,
//		R.string.h4, R.string.h5, R.string.h6, R.string.h7, R.string.h8,
//		R.string.h9, R.string.h10, R.string.h11, R.string.h12,
//		R.string.h13, R.string.h14, R.string.h15, R.string.h16,
//		R.string.h17, R.string.h18, R.string.h19, R.string.h20,
//		R.string.h21, R.string.h22, R.string.h23, R.string.h24,
//		R.string.h25, R.string.h26, R.string.h27, R.string.h28,
//		R.string.h29, R.string.h30, R.string.h31, R.string.h32,
//		R.string.h33, R.string.h34, R.string.h35, R.string.h36,
//		R.string.h37, R.string.h38, R.string.h39, R.string.h40,
//		R.string.h41, R.string.h42, R.string.h43, R.string.h44,
//		R.string.h45, R.string.h46, R.string.h47, R.string.h48,
//		R.string.h49, R.string.h50, R.string.h51, R.string.h52,
//		R.string.h53, R.string.h54, R.string.h55, R.string.h56,
//		R.string.h57, R.string.h58, R.string.h59, R.string.h60,
//		R.string.h61, R.string.h62, R.string.h63, R.string.h64,
//		R.string.h65, R.string.h66, R.string.h67, R.string.h68,
//		R.string.h69, R.string.h70, R.string.h71, R.string.h72,
//		R.string.h73, R.string.h74, R.string.h75, R.string.h76,
//		R.string.h77, R.string.h78, R.string.h79, R.string.h80,
//		R.string.h81, R.string.h82, R.string.h83, R.string.h84,
//		R.string.h85, R.string.h86, R.string.h87, R.string.h88,
//		R.string.h89, R.string.h90, R.string.h91, R.string.h92,
//		R.string.h93, R.string.h94, R.string.h95, R.string.h96,
//		R.string.h97, R.string.h98, R.string.h99, R.string.h100,
//		R.string.h101, R.string.h102, R.string.h103, R.string.h104,
//		R.string.h105, R.string.h106, R.string.h107, R.string.h108 };

//	private Integer[] heroStars = { R.string.hs1, R.string.hs2, R.string.hs3,
//			R.string.hs4, R.string.hs5, R.string.hs6, R.string.hs7,
//			R.string.hs8, R.string.hs9, R.string.hs10, R.string.hs11,
//			R.string.hs12, R.string.hs13, R.string.hs14, R.string.hs15,
//			R.string.hs16, R.string.hs17, R.string.hs18, R.string.hs19,
//			R.string.hs20, R.string.hs21, R.string.hs22, R.string.hs23,
//			R.string.hs24, R.string.hs25, R.string.hs26, R.string.hs27,
//			R.string.hs28, R.string.hs29, R.string.hs30, R.string.hs31,
//			R.string.hs32, R.string.hs33, R.string.hs34, R.string.hs35,
//			R.string.hs36, R.string.hs37, R.string.hs38, R.string.hs39,
//			R.string.hs40, R.string.hs41, R.string.hs42, R.string.hs43,
//			R.string.hs44, R.string.hs45, R.string.hs46, R.string.hs47,
//			R.string.hs48, R.string.hs49, R.string.hs50, R.string.hs51,
//			R.string.hs52, R.string.hs53, R.string.hs54, R.string.hs55,
//			R.string.hs56, R.string.hs57, R.string.hs58, R.string.hs59,
//			R.string.hs60, R.string.hs61, R.string.hs62, R.string.hs63,
//			R.string.hs64, R.string.hs65, R.string.hs66, R.string.hs67,
//			R.string.hs68, R.string.hs69, R.string.hs70, R.string.hs71,
//			R.string.hs72, R.string.hs73, R.string.hs74, R.string.hs75,
//			R.string.hs76, R.string.hs77, R.string.hs78, R.string.hs79,
//			R.string.hs80, R.string.hs81, R.string.hs82, R.string.hs83,
//			R.string.hs84, R.string.hs85, R.string.hs86, R.string.hs87,
//			R.string.hs88, R.string.hs89, R.string.hs90, R.string.hs91,
//			R.string.hs92, R.string.hs93, R.string.hs94, R.string.hs95,
//			R.string.hs96, R.string.hs97, R.string.hs98, R.string.hs99,
//			R.string.hs100, R.string.hs101, R.string.hs102, R.string.hs103,
//			R.string.hs104, R.string.hs105, R.string.hs106, R.string.hs107,
//			R.string.hs108 };
	public static String[] heroNames = { "呼保义－宋江", "玉麒麟－卢俊义", "智多星－吴用",
			"入云龙－公孙胜", "大刀-关胜", "豹子头-林冲", "霹雳火－秦明", "双鞭－呼延灼", "小李广－花荣",
			"小旋风－柴进", "扑天雕－李应", "美髯公-朱仝", "花和尚－鲁智深", "行者-武松", "双枪将－董平",
			"没羽箭-张清", "青面兽－杨志", "金枪手－徐宁", "急先锋-索超", "神行太保－戴宗", "赤发鬼－刘唐",
			"黑旋风－李逵", "九纹龙－史进", "没遮拦-穆弘", "插翅虎－雷横", "混江龙-李俊", "立地太岁－阮小二",
			"船火儿－张横", "短命二郞－阮小五", "浪里白条－张顺", "活阎罗－阮小七", "病关索-杨雄", "拼命三郎-石秀",
			"两头蛇－解珍", "双尾蝎－解宝", "浪子－燕青", "神机军师－朱武", "镇三山-黄信", "病尉迟－孙立",
			"丑郡马－宣赞", "井木犴－郝思文", "百胜将－韩滔", "天目将－彭玘", "圣水将－单廷圭", "神火将-魏定国",
			"圣手书生－萧让", "铁面孔目－裴宣", "摩云金翅－鸥鹏", "火眼狻猊－邓飞", "锦毛虎－燕顺", "锦豹子-杨林",
			"轰天雷－凌振", "神算子－蒋敬", "小温侯－吕方", "赛仁贵－郭盛", "神医-安道全", "紫髯伯－皇甫瑞",
			"矮脚虎－王英", "一丈青－扈三娘", "丧门神－鲍旭", "混世魔王－樊瑞", "毛头星－孔明", "独火星－孔亮",
			"八臂哪吒－项充", "飞天大圣－李衮", "玉臂匠－金大坚", "铁笛仙－马麟", "出洞蛟－童威", "翻江蜃－童猛",
			"玉幡－孟康", "通臂猿－侯健", "跳涧虎－陈达", "白花蛇－杨春", "白面郞君－郑天寿", "九尾龟－陶宗旺",
			"铁扇子－宋清", "铁叫子－乐和", "花项虎－龚旺", "中箭虎－丁得孙", "小遮拦－穆春", "操刀鬼－曹正",
			"云里金刚－宋万", "摸着天－杜迁", "病大虫－薛永", "金眼彪－施恩", "小霸王-周通", "打虎将－李忠",
			"鬼脸儿－杜兴", "金钱豹子－汤隆", "独角龙－邹润", "出林龙－邹渊", "笑面虎－朱富", "旱地忽律－朱贵",
			"铁臂膊-蔡福", "一枝花－蔡庆", "催命判官－李立", "青眼虎－李云", "没面目-焦挺", "石将军－石勇",
			"小尉迟－孙新", "母大虫－顾大嫂", "菜园子－张青", "母夜叉－孙二娘", "活闪婆－王定六", "险道神－郁保四",
			"白日鼠-白胜", "鼓上蚤－时迁", "金毛犬－段景住" };

	private String[] heroStars = { "天罡：天魁星", "天罡：天罡星", "天罡：天机星", "天罡：天闲星",
			"天罡：天勇星", "天罡：天雄星", "天罡：天猛星", "天罡：天威星", "天罡：天英星", "天罡：天贵星",
			"天罡：天富星", "天罡：天满星", "天罡：天孤星", "天罡：天伤星", "天罡：天立星", "天罡：天捷星",
			"天罡：天暗星", "天罡：天佑星", "天罡：天空星", "天罡：天速星", "天罡：天异星", "天罡：天杀星",
			"天罡：天微星", "天罡：天究星", "天罡：天退星", "天罡：天寿星", "天罡：天剑星", "天罡：天平星",
			"天罡：天罪星", "天罡：天损星", "天罡：天败星", "天罡：天牢星", "天罡：天慧星", "天罡：天暴星",
			"天罡：天哭星", "天罡：天巧星", "地煞：地魁星", "地煞：地煞星", "地煞：地勇星", "地煞：地杰星",
			"地煞：地雄星", "地煞：地威星", "地煞：地英星", "地煞：地奇星", "地煞：地猛星", "地煞：地文星",
			"地煞：地正星", "地煞：地辟星", "地煞：地阖星", "地煞：地强星", "地煞：地暗星", "地煞：地轴星",
			"地煞：地轴星", "地煞：地佐星", "地煞：地佑星", "地煞：地灵星", "地煞：地兽星", "地煞：地微星",
			"地煞：地慧星", "地煞：地暴星", "地煞：地默星", "地煞：地猖星", "地煞：地狂星", "地煞：地飞星",
			"地煞：地走星", "地煞：地巧星", "地煞：地明星", "地煞：地进星", "地煞：地退星", "地煞：地满星",
			"地煞：地遂星", "地煞：地周星", "地煞：地隐星", "地煞：地异星", "地煞：地理星", "地煞：地俊星",
			"地煞：地乐星", "地煞：地捷星", "地煞：地速星", "地煞：地镇星", "地煞：地嵇星", "地煞：地魔星",
			"地煞：地妖星", "地煞：地幽星", "地煞：地伏星", "地煞：地空星", "地煞：地僻星", "地煞：地全星",
			"地煞：地孤星", "地煞：地角星", "地煞：地短星", "地煞：地藏星", "地煞：地囚星", "地煞：地平星",
			"地煞：地损星", "地煞：地奴星", "地煞：地察星", "地煞：地恶星", "地煞：地丑星", "地煞：地数星",
			"地煞：地阴星", "地煞：地刑星", "地煞：地壮星", "地煞：地劣星", "地煞：地健星", "地煞：地耗星",
			"地煞：地贼星", "地煞：地狗星" };
	
	
	public static String[] heroPos = { "总督兵马大元帅", "总督兵马副元", "正军师", "副军师",
			"五虎大将之左军大将", "五虎大将之右军大将", "五虎大将之先锋大将", "五虎大将之合后大将", "马军八虎骑兼先锋使之首",
			"内库房大统领", "粮食库大统领", "马军八虎骑兼先锋使之六", "步军总大将", "步军副大将", "五虎大将之虎军大将",
			"马军八虎骑兼先锋使之五", "马军八虎骑兼先锋使之三", "马军八虎骑兼先锋使之二", "马军八虎骑兼先锋使之四",
			"机密情报营总指挥使", "步军统领兼冲击营指挥", "步军统领兼侍卫队长", "马军八虎骑兼先锋使之七",
			"马军八虎骑兼先锋使之八", "步军统领兼突击营指挥", "水军大都督", "水军副都督兼楼船营指挥", "水军副都督兼炮艇营指挥",
			"水军副都督兼弩船营指挥", "水军副都督兼水鬼营指挥", "水军副都督兼快艇营指挥", "步军统领兼后援营指挥",
			"步军统领兼游击营指挥", "步军统领", "步军统领", "步军统领兼谍报队长", "军事参赞", "马军小彪将兼虎翼营都统",
			"马军小彪将兼龙翼营都统", "马军小彪将兼虎翼营都统", "马军小彪将兼虎翼营都统", "马军小彪将兼哨探营都统",
			"马军小彪将兼飞骑营都统", "马军小彪将兼圣水营都统", "马军小彪将兼神火营都统", "兵部侍郎", "定功罪赏罚军政司总管",
			"马军小彪将兼右军营都统", "马军小彪将兼长弓营都统", "马军小彪将兼飞虎营都统", "马军小彪将兼飞豹营都统", "火炮营统",
			"户部侍郎", "中军护卫队马军都统", "中军护卫队马军都统", "梁山疗伤营总巡查", "马房总管", "内营护卫队头领",
			"内营娘子军统领", "装甲重剑营都统", "魔法营步军头领", "中军守护营步兵队队长", "中军守护营步兵队副队长",
			"横冲营步军头领", "横冲营步军头领", "兵符印信房总管", "马军小彪将兼南军营都统", "水军水雷营指挥",
			"水军火船营指挥", "战船制造使", "旌旗军服房总管", "马军小彪将兼先锋营都统", "马军小彪将兼先锋营都统",
			"长枪营步军统领", "工程部都总管", "礼宾司总管", "机密情报营头领", "奇袭营步兵都统", "奇袭营步军头领",
			"前锋营步军统领", "梁山牧场总管", "步军左翼营统领", "步军右翼营统领", "长枪营步军统领", "巡逻营步军头领",
			"马军小彪将兼北军营都统", "横冲营步军头领", "梁山南山酒店迎宾使之一", "兵器制造房总管", "游击营步军头领",
			"游击营步军头领", "酿酒工房总管", "梁山南山酒店迎宾使之一", "行刑部头领", "行刑部副头领",
			"梁山北山酒店迎宾使之", "工程部总管", "山地营步军头领", "搜捕营步军头领", "梁山东山酒店迎宾使之一",
			"梁山东山酒店迎宾使之", "梁山西山酒店迎宾使之一", "梁山西山酒店迎宾使之一", "梁山北山酒店迎宾使之一",
			"中军帐帅字旗掌旗人", "机密情报营头领", "机密情报营头领", "机密情报营头领" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		findView();
		addListener();
		switch (CardBoxApp.currentBg) {
		case 0: {
			CardBoxApp.backGround = 0;
			background.setBackgroundResource(R.drawable.main_bg_1);
		}

			break;
		case 1: {
			CardBoxApp.backGround = 1;
			background.setBackgroundResource(R.drawable.main_bg_2);
		}
			break;
		case 2: {
			CardBoxApp.backGround = 2;
			background.setBackgroundResource(R.drawable.main_bg_3);
		}
			break;
		case 3: {
			CardBoxApp.backGround = 3;
			background.setBackgroundResource(R.drawable.main_bg_4);
		}
			break;
		default:
			break;

		}
		mPosition = getIntent().getIntExtra("position", 88);
		mHeroDetailIv.setImageResource(CardBoxApp.appImages[mPosition]);
		mHeroStarsTv.setText(heroStars[mPosition]);
		mHeroDetailTv.setText(heroDetails[mPosition]);
		mHeroNameTv.setText(heroNames[mPosition]);
		mHeroPositionTv.setText("职位："+heroPos[mPosition]);
	}

	private void findView() {
		back = (ImageView) findViewById(R.id.detail_back_iv);
		mHeroDetailIv = (ImageView) findViewById(R.id.detail_hero_iv);
		mHeroStarsTv = (TextView) findViewById(R.id.detail_star_tv);
		mHeroDetailTv = (TextView) findViewById(R.id.detail_hero_tv);
		mHeroPositionTv = (TextView) findViewById(R.id.detail_hero_position_tv);
		mHeroNameTv = (TextView) findViewById(R.id.detail_name_tv);
		background = (LinearLayout) findViewById(R.id.main_activiry_bgll);
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
