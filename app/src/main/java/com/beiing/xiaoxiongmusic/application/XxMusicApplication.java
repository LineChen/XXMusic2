package com.beiing.xiaoxiongmusic.application;


import java.util.LinkedList;
import java.util.Queue;

import com.beiing.xiaoxiongmusic.cache.BitmapCache;
import com.beiing.xiaoxiongmusic.entities.SongDetail;

import android.app.Application;
import android.content.Context;

/**
 * 定义全局变量
 * @author Administrator
 *
 */
public class XxMusicApplication extends Application {
	
	private static Context context;
	
	private static BitmapCache bitmapCache;//维护一个全局缓存
	
	/**
	 * 维护一个歌曲播放队列，
	 */
	private static Queue<SongDetail> musicQueue;
	
	/**
	 * 队列最大size
	 */
	private int MAX_SIZE = 40;
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();	
		bitmapCache = new BitmapCache();
		
		musicQueue = new LinkedList<SongDetail>();
	}
	
	/**
	 * 把一首歌加入到队列中
	 * @param detail
	 * @return
	 */
	public boolean musicEnqueue(SongDetail detail){
		return musicQueue.add(detail);
	}
	
	
	public static Context getContext(){
		return context;
	}
	
	public static BitmapCache getBitMapCache(){
		return bitmapCache;
	}
	
}
