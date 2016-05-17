package com.beiing.xiaoxiongmusic.service;

import com.beiing.xiaoxiongmusic.configs.XxMusicConfigs;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class XxMusicService extends Service {

	private MediaPlayer mPlayer;

	private LocalBroadcastManager lbManager;

	private int sumLen;// ��ʱ��

	ProReceiver proReceiver;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mPlayer = new MediaPlayer();
		// ��ȡ���ع㲥������
		lbManager = LocalBroadcastManager.getInstance(getApplicationContext());
		
		proReceiver = new ProReceiver();
		
		lbManager.registerReceiver(proReceiver, new IntentFilter(XxMusicConfigs.ACTION_SEEKBAR_PROGERSS));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// �ж��Ƿ񲥷��µĸ���
		if (intent.getBooleanExtra(XxMusicConfigs.EXTRA_CHANGE_MUSIC, false)) {
			// ��ȡ����·��
			String path = intent.getStringExtra(XxMusicConfigs.EXTRA_MUSIC_PATH);
			mPlayer.reset();
			try {
				mPlayer.setDataSource(path);
				mPlayer.prepareAsync();
				mPlayer.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						if (mPlayer.isPlaying()) {
							mPlayer.pause();// ��ͣ
						} else {
							mPlayer.start();// ����
							onCompleteEvent();//���Ž����ļ���
							
							new ProgressThread().start();//���������߳�
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (mPlayer.isPlaying()) {
				mPlayer.pause();// ��ͣ
			} else {
				mPlayer.start();// ����
				new ProgressThread().start();//���������߳�
			}
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	

	/**
	 * ���Ž����ļ���
	 */
	private void onCompleteEvent() {
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO ���Ͳ�����ϵĹ㲥
				Intent intent = new Intent(XxMusicConfigs.ACTION_MUSIC_COMPLETE);
				lbManager.sendBroadcast(intent);
			}
		});
	}
	
	
	
	/**
	 * ���㲥�Ž��ȵ��߳�
	 */
	class ProgressThread extends Thread {
		@Override
		public void run() {
			try {
				while (mPlayer != null && mPlayer.isPlaying()) {
					sumLen = mPlayer.getDuration();
					int currentPosition = mPlayer.getCurrentPosition();
					// ׼�����ͽ��ȹ㲥
					Intent intent = new Intent(XxMusicConfigs.ACTION_MUSIC_PROGRESS);
					intent.putExtra(XxMusicConfigs.EXTRA_MUSIC_TOTAL_LEN, sumLen);
					intent.putExtra(XxMusicConfigs.EXTRA_MUSIC_CUR_LEN,
							currentPosition);
					lbManager.sendBroadcast(intent);
					
					Thread.sleep(200);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ���ȹ㲥������
	 */
	class ProReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			int cur = intent.getIntExtra(XxMusicConfigs.EXTRA_MUSIC_CUR_LEN, 0);
			mPlayer.seekTo(cur);
		}
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		lbManager.unregisterReceiver(proReceiver);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
