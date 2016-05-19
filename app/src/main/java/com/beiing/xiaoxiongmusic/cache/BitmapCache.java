package com.beiing.xiaoxiongmusic.cache;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.LruCache;

@SuppressLint("NewApi")
public class BitmapCache {
	/**
	 * ͼƬ���漼���ĺ����࣬���ڻ����������غõ�ͼƬ���ڳ����ڴ�ﵽ�趨ֵʱ�Ὣ�������ʹ�õ�ͼƬ�Ƴ�����
	 */
	private LruCache<String, Bitmap> mMemoryCache;
	
	/**
	 * ����ʵ��ͼƬ��Сѡ����ʵĻ���
	 */
	private int CACHE_MAX_SIZE = 1024 * 1024 * 4;//5m--�ܺ���

	public BitmapCache() {
		// ��ȡӦ�ó����������ڴ�
		int maxMemory = (int) Runtime.getRuntime().maxMemory();// ��maxMemory����Ƚϴ󣬿��Լ��ٴ��������ش���
		int cacheSize = maxMemory / 10;//��Լ1.3m
//		Log.i("--", "===============cacheSize = " + cacheSize);
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
//				LogUtil.i("--", "drawable == null :" + (drawable == null));
				return bitmap.getByteCount();
			}
		};
	}

	/**
	 * ��һ��ͼƬ�洢��LruCache�С�
	 * 
	 * @param key
	 *            LruCache�ļ������ﴫ��ͼƬ��URL��ַ��
	 *            LruCache��ֵ�����ﴫ������������ص�BitmapDrawable����
	 */
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemoryCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemoryCache(String key) {
		return mMemoryCache.get(key);
	}
}
