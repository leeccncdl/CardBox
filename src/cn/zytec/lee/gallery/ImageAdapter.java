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
	 * ������ӰЧ��
	 * 
	 * @return
	 */
	public Bitmap createReflectedImages(Bitmap originalImage) {
		// ��Ӱͼ��ԭͼ֮��ľ���
		final int reflectionGap = 4;

		// for (int imageId : mImageIds) {
		// ����ԭͼ����֮���bitmap����
		// Bitmap originalImage =
		// BitmapFactory.decodeResource(mContext.getResources(),
		// mImageIds[currentId]);
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		// �����������
		Matrix matrix = new Matrix();

		// ָ��һ���Ƕ���0,0Ϊ���������ת
		// matrix.setRotate(30);

		// ָ������(x�᲻�䣬y���෴)
		matrix.preScale(1, -1);

		// ������Ӧ�õ���ԭͼ֮�У�����һ����Ȳ��䣬�߶�Ϊԭͼ1/2�ĵ�Ӱλͼ
		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		// ����һ����Ȳ��䣬�߶�Ϊԭͼ+��Ӱͼ�߶ȵ�λͼ
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		// �����洴����λͼ��ʼ��������
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
		 * ����һ:Ϊ�������������xλ�ã� ������:Ϊy��λ�ã� ����������:�ֱ��Ӧ�����յ㣬 ������Ϊƽ�̷�ʽ��
		 * ��������Ϊ����Gradient�ǻ���Shader�࣬��������ͨ��Paint��setShader�����������������
		 */
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff,
				TileMode.MIRROR);
		// ������Ӱ
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.DST_IN));
		// ���Ѿ�����õĻ��ʹ���һ��������Ӱ����Ч��
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		// ����һ��ImageView������ʾ�Ѿ����õ�bitmapWithReflection
		// ImageView imageView = new ImageView(mContext);
		// imageView.setImageBitmap(bitmapWithReflection);
		// //����imageView��С ��Ҳ����������ʾ��ͼƬ��С
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
