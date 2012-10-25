package cn.zytec.lee.gallery;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zytec.lee.CardBoxApp;
import cn.zytec.lee.R;

public class ImageAdapter extends BaseAdapter {

	int mGalleryItemBackground;
	private Context mContext;
	
//	private Integer[] names = {
//			R.string.h1, R.string.h2,R.string.h3, R.string.h4, R.string.h5,R.string.h6,
//			R.string.h7, R.string.h8,R.string.h9, R.string.h10, R.string.h11,R.string.h12,
//			R.string.h13, R.string.h14,R.string.h15, R.string.h16, R.string.h17,R.string.h18,
//			R.string.h19, R.string.h20,R.string.h21, R.string.h22, R.string.h23,R.string.h24,
//			R.string.h25, R.string.h26,R.string.h27, R.string.h28, R.string.h29,R.string.h30,
//			R.string.h31, R.string.h32,R.string.h33, R.string.h34, R.string.h35,R.string.h36,
//			R.string.h37, R.string.h38,R.string.h39, R.string.h40, R.string.h41,R.string.h42,
//			R.string.h43, R.string.h44,R.string.h45, R.string.h46, R.string.h47,R.string.h48,
//			R.string.h49, R.string.h50,R.string.h51, R.string.h52, R.string.h53,R.string.h54,
//			R.string.h55, R.string.h56,R.string.h57, R.string.h58, R.string.h59,R.string.h60,
//			R.string.h61, R.string.h62,R.string.h63, R.string.h64, R.string.h65,R.string.h66,
//			R.string.h67, R.string.h68,R.string.h69, R.string.h70, R.string.h71,R.string.h72,
//			R.string.h73, R.string.h74,R.string.h75, R.string.h76, R.string.h77,R.string.h78,
//			R.string.h79, R.string.h80,R.string.h81, R.string.h82, R.string.h83,R.string.h84,
//			R.string.h85, R.string.h86,R.string.h87, R.string.h88, R.string.h89,R.string.h90,
//			R.string.h91, R.string.h92,R.string.h93, R.string.h94, R.string.h95,R.string.h96,
//			R.string.h97, R.string.h98,R.string.h99, R.string.h100, R.string.h101,R.string.h102,
//			R.string.h103, R.string.h104,R.string.h105, R.string.h106, R.string.h107,R.string.h108};
//	
	

	@SuppressLint("UseSparseArrays")
	private static Map<Integer, SoftReference<Bitmap>> cache = new HashMap<Integer, SoftReference<Bitmap>>();

	// private int currentId = 0;

	public ImageAdapter(Context c) {
		mContext = c;
		// mImages = new ImageView[mImageIds.length];
	}


	public Bitmap createReflectedImages(Bitmap originalImage) {

		final int reflectionGap = 4;

		// for (int imageId : mImageIds) {
		// Bitmap originalImage =
		// BitmapFactory.decodeResource(mContext.getResources(),
		// mImageIds[currentId]);
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();


		Matrix matrix = new Matrix();

		// matrix.setRotate(30);
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(originalImage, 0, 0, null);

		Paint deafaultPaint = new Paint();
		deafaultPaint.setAntiAlias(false);
		// canvas.drawRect(0, height, width, height +
		// reflectionGap,deafaultPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		paint.setAntiAlias(false);


		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff,
				TileMode.MIRROR);

		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.DST_IN));

		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);


		// ImageView imageView = new ImageView(mContext);
		// imageView.setImageBitmap(bitmapWithReflection);
		// imageView.setLayoutParams(new GalleryFlow.LayoutParams(300, 400));
		// imageView.setScaleType(ScaleType.MATRIX);
		// mImages[currentId++] = imageView;

		return bitmapWithReflection;
	}


	public int getCount() {
		return CardBoxApp.arrayImages.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		int id = CardBoxApp.arrayImages[position];
		
		switch(CardBoxApp.showView) {
		//画廊显示方式
		case CardBoxApp.GALLERYVIEW:
			if (convertView == null) {
				convertView = new ImageView(mContext);
				convertView.setLayoutParams(new GalleryFlow.LayoutParams(300, 400));
			}

			ImageView iv = (ImageView) convertView;
			
			
			if (!cache.containsKey(id)) {
				Bitmap originalImage = BitmapFactory.decodeResource(
						mContext.getResources(), id);
				cache.put(id, new SoftReference<Bitmap>(originalImage));
				iv.setImageBitmap(createReflectedImages(originalImage));
			} else {
				Bitmap b = cache.get(id).get();
				if (b == null) {
					Bitmap originalImage = BitmapFactory.decodeResource(
							mContext.getResources(), id);
					cache.put(id, new SoftReference<Bitmap>(originalImage));
					iv.setImageBitmap(createReflectedImages(originalImage));
				}else {
					iv.setImageBitmap(createReflectedImages(b));
				}
			}
			break;
			
			//表格显示方式
			
		case CardBoxApp.GRIDVIEW:

			ViewHolder vh = null;
			if(convertView == null) {
				vh = new ViewHolder();
				convertView = View.inflate(mContext,R.layout.gridview_item, null);
				vh.iv = (ImageView) convertView.
					findViewById(R.id.gridview_item_iv);
				vh.tv = (TextView) convertView.
					findViewById(R.id.grid_view_tv);

				convertView.setTag(vh);
				
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			
			if (!cache.containsKey(id)) {
				Bitmap originalImage = BitmapFactory.decodeResource(
						mContext.getResources(), id);
				cache.put(id, new SoftReference<Bitmap>(originalImage));
				vh.iv.setImageBitmap(originalImage);
			} else {
				Bitmap b = cache.get(id).get();
				if (b == null) {
					Bitmap originalImage = BitmapFactory.decodeResource(
							mContext.getResources(), id);
					cache.put(id, new SoftReference<Bitmap>(originalImage));
					vh.iv.setImageBitmap(originalImage);
				}else {
					vh.iv.setImageBitmap(b);
				}
			}
			vh.tv.setText(CardBoxApp.arrayNames[position]);
			break;
			
		}
		return convertView;
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

	private class ViewHolder{
		private TextView tv;
		private ImageView iv;

	}
	
}
