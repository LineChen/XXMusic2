package com.beiing.xiaoxiongmusic.asynktasks;

import com.beiing.xiaoxiongmusic.utils.HttpUtils;
import com.beiing.xiaoxiongmusic.utils.JsonParseUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

public class LoadAsyncTask extends AsyncTask<String, Void, Object> {

	private LoadListener loadListener;
	private Context context;
	private final int LOAD_START = 0x100;
	
	/**
	 * ���ص�̨��Ϣ,����Ƶ��
	 */
	public static final String LOAD_STATION_PUBLIC = "load_station_public";
	
	/**
	 * ���ص�̨��Ϣ,������Ƶ��
	 */
	public static final String LOAD_STATION_MUSICIAN = "load_station_musician";
	
	/**
	 * ����ĳһ��̨�µ������б�
	 */
	public static final String LOAD_SONG_LIST = "load_song_list";
	
	/**
	 * ���ظ�������
	 */
	public static final String LOAD_SONG_DETAIL = "load_song_detail";
	

	public LoadAsyncTask(Context context, LoadListener loadListener) {
		this.context = context;
		this.loadListener = loadListener;
	}

	// ��һ��������url���ڶ��������Ǽ��ص�����
	@Override
	protected Object doInBackground(String... params) {
		Object info = null;
		String url = params[0];
		String type = params[1];
		if (url != null) {
			try {
				byte[] buffer = HttpUtils.getDataBytes(url);
				String jsonStr = HttpUtils.getJsonStr(buffer, "utf-8");
				if(type.equals(LOAD_STATION_PUBLIC)){
					info = JsonParseUtil.parsePbChs(jsonStr);
				} else if(type.equals(LOAD_STATION_MUSICIAN)){
					info = JsonParseUtil.parseMuChs(jsonStr);
				}
				else if(type.equals(LOAD_SONG_LIST)){
					info = JsonParseUtil.parseSongBrief(jsonStr);
				} else if(type.equals(LOAD_SONG_DETAIL)){
					info = JsonParseUtil.parseSongdetail(jsonStr);
				}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return info;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		loadListener.loadSeccess(result);
	}

	public interface LoadListener {
		public void loadSeccess(Object result);
	}

}
