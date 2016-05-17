package com.beiing.xxmusic;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.beiing.xiaoxiongmusic.adapters.OnlineSongAdapter;
import com.beiing.xiaoxiongmusic.asynktasks.LoadAsyncTask;
import com.beiing.xiaoxiongmusic.asynktasks.LoadAsyncTask.LoadListener;
import com.beiing.xiaoxiongmusic.configs.XxMusicConfigs;
import com.beiing.xiaoxiongmusic.configs.XxMusicUrl;
import com.beiing.xiaoxiongmusic.entities.SongBrief;
import com.beiing.xiaoxiongmusic.entities.SongDetail;
import com.beiing.xiaoxiongmusic.service.XxMusicService;

public class OnlineSongListActivity extends Activity {

	private ListView listView;

	private TextView titleTv;

	private ImageButton backIb;

	private OnlineSongAdapter adapter;

	private List<SongBrief> datas;

	private String ch_name;

	private String title;

	private Context context;
	
	
	private LocalBroadcastManager lbManager;// ���ع㲥������
	

	public static void startOnlineSlActivity(Context context, String title,
			String ch_name) {
		Intent intent = new Intent(context, OnlineSongListActivity.class);
		intent.putExtra("title", title);
		intent.putExtra("ch_name", ch_name);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online_songlist);
		context = this;
		lbManager = LocalBroadcastManager.getInstance(context);
		listView = (ListView) findViewById(R.id.ac_online_list);
		titleTv = (TextView) findViewById(R.id.ac_online_title);
		backIb = (ImageButton) findViewById(R.id.ac_online_back);

		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		ch_name = intent.getStringExtra("ch_name");
		titleTv.setText(title);

		new LoadAsyncTask(context, new LoadListener() {
			@Override
			public void loadSeccess(Object result) {
				if (result != null) {
					datas = (List<SongBrief>) result;
					adapter = new OnlineSongAdapter(context, datas);
					listView.setAdapter(adapter);
				}
			}
		}).execute(String.format(XxMusicUrl.MUSIC_LIST_OF_RADIOSTATION_URL,
				ch_name), LoadAsyncTask.LOAD_SONG_LIST);

		itemClickEvent();
	}

	/**
	 * ���ĳһ����¼�
	 */
	private void itemClickEvent() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String songid = datas.get(position).getSongid();
				new LoadAsyncTask(context, new LoadListener() {
					@Override
					public void loadSeccess(Object result) {
						if (result != null) {
							SongDetail detail = (SongDetail) result;
							playMusicOnline(detail);
						}
					}
				}).execute(String.format(XxMusicUrl.MUSIC_DETAIL_URL, songid),
						LoadAsyncTask.LOAD_SONG_DETAIL);
				
			}
		});
	}

	/**
	 * ������������
	 * @param detail
	 */
	private void playMusicOnline(SongDetail detail){
		String spath = detail.getSongLink();
		Intent intent = new Intent(context,
				XxMusicService.class);
		intent.putExtra(XxMusicConfigs.EXTRA_MUSIC_PATH, spath);
		intent.putExtra(XxMusicConfigs.EXTRA_CHANGE_MUSIC, true);
		startService(intent);
		
		//���͹㲥��NaowPlayFragment
		Intent onlinePlay = new Intent(XxMusicConfigs.ACTION_PLAY_MUSIC_ONLINE);
		onlinePlay.putExtra(XxMusicConfigs.EXTRA_SONG_DETAIL_ONLINE, detail);
		Bundle bundle = new Bundle();
		lbManager.sendBroadcast(onlinePlay);
		
	}
	
	
	public void onBack(View v) {
		finish();
	}

}
