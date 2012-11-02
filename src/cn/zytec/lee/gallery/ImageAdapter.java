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
		return CardBoxApp.appImages.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		int id = CardBoxApp.appImages[position];
		
		switch(CardBoxApp.currentViewMode) {
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
			vh.tv.setText(CardBoxApp.heroNames[position]);
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
