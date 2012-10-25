package cn.zytec.lee;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import cn.zytec.lee.utils.StrictModeWrapper;

public class CardBoxApp extends Application {

	public static final int GALLERYVIEW = 0;
	public static final int GRIDVIEW = 1;
	public static int showView = GALLERYVIEW;

	public static boolean isYaoyiyaoOpen;
	public static int backGround;

	public static boolean isVoiceRecognizeTips;

	public static final String mGrammarId = "3a28d9e69e06e425995bb3722898ef02";

	public static Integer[] arrayImages = { R.drawable.h1, R.drawable.h2,
			R.drawable.h3, R.drawable.h4, R.drawable.h5, R.drawable.h6,
			R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
			R.drawable.h11, R.drawable.h12, R.drawable.h13, R.drawable.h14,
			R.drawable.h15, R.drawable.h16, R.drawable.h17, R.drawable.h18,
			R.drawable.h19, R.drawable.h20, R.drawable.h21, R.drawable.h22,
			R.drawable.h23, R.drawable.h24, R.drawable.h25, R.drawable.h26,
			R.drawable.h27, R.drawable.h28, R.drawable.h29, R.drawable.h30,
			R.drawable.h31, R.drawable.h32, R.drawable.h33, R.drawable.h34,
			R.drawable.h35, R.drawable.h36, R.drawable.h37, R.drawable.h38,
			R.drawable.h39, R.drawable.h40, R.drawable.h41, R.drawable.h42,
			R.drawable.h43, R.drawable.h44, R.drawable.h45, R.drawable.h46,
			R.drawable.h47, R.drawable.h48, R.drawable.h49, R.drawable.h50,
			R.drawable.h51, R.drawable.h52, R.drawable.h53, R.drawable.h54,
			R.drawable.h55, R.drawable.h56, R.drawable.h57, R.drawable.h58,
			R.drawable.h59, R.drawable.h60, R.drawable.h61, R.drawable.h62,
			R.drawable.h63, R.drawable.h64, R.drawable.h65, R.drawable.h66,
			R.drawable.h67, R.drawable.h68, R.drawable.h69, R.drawable.h70,
			R.drawable.h71, R.drawable.h72, R.drawable.h73, R.drawable.h74,
			R.drawable.h75, R.drawable.h76, R.drawable.h77, R.drawable.h78,
			R.drawable.h79, R.drawable.h80, R.drawable.h81, R.drawable.h82,
			R.drawable.h83, R.drawable.h84, R.drawable.h85, R.drawable.h86,
			R.drawable.h87, R.drawable.h88, R.drawable.h89, R.drawable.h90,
			R.drawable.h91, R.drawable.h92, R.drawable.h93, R.drawable.h94,
			R.drawable.h95, R.drawable.h96, R.drawable.h97, R.drawable.h98,
			R.drawable.h99, R.drawable.h100, R.drawable.h101, R.drawable.h102,
			R.drawable.h103, R.drawable.h104, R.drawable.h105, R.drawable.h106,
			R.drawable.h107, R.drawable.h108 };

	public static Integer[] arrayNames = { R.string.h1, R.string.h2, R.string.h3,
			R.string.h4, R.string.h5, R.string.h6, R.string.h7, R.string.h8,
			R.string.h9, R.string.h10, R.string.h11, R.string.h12,
			R.string.h13, R.string.h14, R.string.h15, R.string.h16,
			R.string.h17, R.string.h18, R.string.h19, R.string.h20,
			R.string.h21, R.string.h22, R.string.h23, R.string.h24,
			R.string.h25, R.string.h26, R.string.h27, R.string.h28,
			R.string.h29, R.string.h30, R.string.h31, R.string.h32,
			R.string.h33, R.string.h34, R.string.h35, R.string.h36,
			R.string.h37, R.string.h38, R.string.h39, R.string.h40,
			R.string.h41, R.string.h42, R.string.h43, R.string.h44,
			R.string.h45, R.string.h46, R.string.h47, R.string.h48,
			R.string.h49, R.string.h50, R.string.h51, R.string.h52,
			R.string.h53, R.string.h54, R.string.h55, R.string.h56,
			R.string.h57, R.string.h58, R.string.h59, R.string.h60,
			R.string.h61, R.string.h62, R.string.h63, R.string.h64,
			R.string.h65, R.string.h66, R.string.h67, R.string.h68,
			R.string.h69, R.string.h70, R.string.h71, R.string.h72,
			R.string.h73, R.string.h74, R.string.h75, R.string.h76,
			R.string.h77, R.string.h78, R.string.h79, R.string.h80,
			R.string.h81, R.string.h82, R.string.h83, R.string.h84,
			R.string.h85, R.string.h86, R.string.h87, R.string.h88,
			R.string.h89, R.string.h90, R.string.h91, R.string.h92,
			R.string.h93, R.string.h94, R.string.h95, R.string.h96,
			R.string.h97, R.string.h98, R.string.h99, R.string.h100,
			R.string.h101, R.string.h102, R.string.h103, R.string.h104,
			R.string.h105, R.string.h106, R.string.h107, R.string.h108 };
	
	public static Integer[] arrayDetails = { R.string.hdetail1, R.string.hdetail2, R.string.hdetail3,
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
	
	public static Integer[] arrayStars = { R.string.hs1, R.string.hs2, R.string.hs3,
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
	public void onCreate() {
		super.onCreate();
		try {
			StrictModeWrapper.init(this);
		} catch (Throwable throwable) {
			Log.v("StrictMode", "... is not available. Punting...");
		}

	}

}
