package com.beiing.xiaoxiongmusic.cache;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.LruCache;

@SuppressLint("NewApi")
public class BitmapCache {
	/**
	 * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
	 */
	private LruCache<String, Bitmap> mMemoryCache;
	
	/**
	 * 根据实际图片大小选择合适的缓存
	 */
	private int CACHE_MAX_SIZE = 1024 * 1024 * 4;//5m--很合适

	public BitmapCache() {
		// 获取应用程序最大可用内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();// 用maxMemory缓存比较大，可以减少从网络下载次数
		int cacheSize = maxMemory / 10;//大约1.3m
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
	 * 将一张图片存储到LruCache中。
	 * 
	 * @param key
	 *            LruCache的键，这里传入图片的URL地址。
	 * @param drawable
	 *            LruCache的值，这里传入从网络上下载的BitmapDrawable对象。
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
