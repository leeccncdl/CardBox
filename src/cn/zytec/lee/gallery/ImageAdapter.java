package cn.zytec.lee.gallery;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
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

public class ImageAdapter extends BaseAdapter {

	int mGalleryItemBackground;
	private Context mContext;
	private Integer[] mImageIds;

	@SuppressLint("UseSparseArrays")
	private static Map<Integer, SoftReference<Bitmap>> cache = new HashMap<Integer, SoftReference<Bitmap>>();

	// private int currentId = 0;

	public ImageAdapter(Context c, Integer[] ImageIds) {
		mContext = c;
		mImageIds = ImageIds;
		// mImages = new ImageView[mImageIds.length];
	}

	/**
	 * 创建倒影效果
	 * 
	 * @return
	 */
	public Bitmap createReflectedImages(Bitmap originalImage) {
		// 倒影图和原图之间的距离
		final int reflectionGap = 4;

		// for (int imageId : mImageIds) {
		// 返回原图解码之后的bitmap对象
		// Bitmap originalImage =
		// BitmapFactory.decodeResource(mContext.getResources(),
		// mImageIds[currentId]);
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		// 创建矩阵对象
		Matrix matrix = new Matrix();

		// 指定一个角度以0,0为坐标进行旋转
		// matrix.setRotate(30);

		// 指定矩阵(x轴不变，y轴相反)
		matrix.preScale(1, -1);

		// 将矩阵应用到该原图之中，返回一个宽度不变，高度为原图1/2的倒影位图
		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		// 创建一个宽度不变，高度为原图+倒影图高度的位图
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		// 将上面创建的位图初始化到画布
		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(originalImage, 0, 0, null);

		Paint deafaultPaint = new Paint();
		deafaultPaint.setAntiAlias(false);
		// canvas.drawRect(0, height, width, height +
		// reflectionGap,deafaultPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		paint.setAntiAlias(false);

		/**
		 * 参数一:为渐变起初点坐标x位置， 参数二:为y轴位置， 参数三和四:分辨对应渐变终点， 最后参数为平铺方式，
		 * 这里设置为镜像Gradient是基于Shader类，所以我们通过Paint的setShader方法来设置这个渐变
		 */
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff,
				TileMode.MIRROR);
		// 设置阴影
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.DST_IN));
		// 用已经定义好的画笔构建一个矩形阴影渐变效果
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		// 创建一个ImageView用来显示已经画好的bitmapWithReflection
		// ImageView imageView = new ImageView(mContext);
		// imageView.setImageBitmap(bitmapWithReflection);
		// //设置imageView大小 ，也就是最终显示的图片大小
		// imageView.setLayoutParams(new GalleryFlow.LayoutParams(300, 400));
		// imageView.setScaleType(ScaleType.MATRIX);
		// mImages[currentId++] = imageView;

		return bitmapWithReflection;
	}

	@SuppressWarnings("unused")
	private Resources getResources() {
		return null;
	}

	public int getCount() {
		return mImageIds.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = new ImageView(mContext);
			convertView.setLayoutParams(new GalleryFlow.LayoutParams(300, 400));
		}

		ImageView iv = (ImageView) convertView;
		int id = mImageIds[position];
		
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
		return convertView;
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

}
