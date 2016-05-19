package com.beiing.xiaoxiongmusic.application;


import java.util.LinkedList;
import java.util.Queue;

import com.beiing.xiaoxiongmusic.cache.BitmapCache;
import com.beiing.xiaoxiongmusic.entities.SongDetail;

import android.app.Application;
import android.content.Context;

/**
 * ����ȫ�ֱ���
 * @author Administrator
 *
 */
public class XxMusicApplication extends Application {
	
	private static Context context;
	
	private static BitmapCache bitmapCache;//ά��һ��ȫ�ֻ���
	
	private static Queue<SongDetail> musicQueue;
	
	/**
	 * �������size
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
	 * ��һ�׸���뵽������
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
