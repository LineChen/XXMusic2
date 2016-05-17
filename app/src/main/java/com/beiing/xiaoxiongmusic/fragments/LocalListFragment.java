package com.beiing.xiaoxiongmusic.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.beiing.xiaoxiongmusic.basics.BaseFragment;
import com.beiing.xiaoxiongmusic.configs.XxMusicConfigs;
import com.beiing.xiaoxiongmusic.entities.SongDetail;
import com.beiing.xiaoxiongmusic.service.XxMusicService;
import com.beiing.xiaoxiongmusic.utils.LogUtil;
import com.beiing.xxmusic.R;

public class LocalListFragment extends BaseFragment {

	/**
	 * ��������
	 */
	public interface MusicPlayListener {
		/**
		 * ���ظ����б���������
		 */
		public void onMusicPlay(SongDetail detail);

	}

	MusicPlayListener musicPlayListener;

	private ListView listView;

	private SimpleCursorAdapter adapter;

	private SimpleDateFormat dateFormat;

	private Uri mp3uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;// ��ȡ��չ�������е���Ƶ�ļ�
	private String[] columns = { MediaStore.Audio.Media._ID, // _id
			MediaStore.Audio.Media.DISPLAY_NAME,// ������
			MediaStore.Audio.Media.ARTIST,// �ݳ���
			MediaStore.Audio.Media.DURATION,// ��ʱ��
			MediaStore.Audio.Media.DATA // �ļ�·��
	};

	// private LocalBroadcastManager lbManager;// ���ع㲥������

	private Cursor cursor;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		musicPlayListener = (MusicPlayListener) activity;
		loadSongs();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_local_song_list, null);
		listView = (ListView) view.findViewById(R.id.loc_song_listLv);
		itemClickEvent();

		initListView();

		return view;
	}

	/**
	 * ��listView��������
	 */
	private void initListView() {
		adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.item_local_song, null, new String[] { columns[1],
						columns[2], columns[3] }, new int[] {
						R.id.item_local_tvName, R.id.item_local_tvArtist,
						R.id.item_local_tvTime },
				SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
			@Override
			public void setViewText(TextView v, String text) {
				// TODO ��д�÷�����
				super.setViewText(v, text);
				if (v.getId() == R.id.item_local_tvTime) {
					long time = Long.parseLong(text);
					v.setText(dateFormat.format(new Date(time)));
				}
			}

		};
		listView.setAdapter(adapter);
		if(cursor != null)
			adapter.swapCursor(cursor);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dateFormat = new SimpleDateFormat("mm:ss", Locale.CHINA);
	}

	/**
	 * ���item��������
	 */
	private void itemClickEvent() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("--", "���itemλ�ã�" + position);
				cursor.moveToPosition(position);
				playSong();
			}
		});
	}

	/**
	 * ���ر��ظ���
	 */
	private void loadSongs() {
		getActivity().getLoaderManager().initLoader(7, null,
				new LoaderCallbacks<Cursor>() {
					@Override
					public Loader<Cursor> onCreateLoader(int id, Bundle args) {
						return new CursorLoader(getActivity(), mp3uri, columns,
								null, null, null);
					}
					@Override
					public void onLoadFinished(Loader<Cursor> loader,
							Cursor data) {
						if(adapter != null)
							adapter.swapCursor(data);
						cursor = data;
						cursor.moveToPosition(0);
					}

					@Override
					public void onLoaderReset(Loader<Cursor> loader) {

					}
				});
	}

	/**
	 * ������һ��
	 */
	public void playNextSong() {
		if (cursor.moveToNext()) {
		} else {
			cursor.moveToFirst();
		}
		playSong();
	}

	/**
	 * ������һ��
	 */
	public void playPrexSong() {
		if (cursor.moveToPrevious()) {
		} else
			cursor.moveToLast();
		playSong();
	}
	
	//���Ÿ���
	private void playSong(){
		String name = cursor.getString(1);
		String artist = cursor.getString(2);
		String path = cursor.getString(4);
		SongDetail detail = new SongDetail();
		detail.setSongName(name).setArtistName(artist);
		musicPlayListener.onMusicPlay(detail);
		
		Intent swapIntent = new Intent(getActivity(), XxMusicService.class);
		swapIntent.putExtra(XxMusicConfigs.EXTRA_MUSIC_PATH, path);
		swapIntent.putExtra(XxMusicConfigs.EXTRA_CHANGE_MUSIC, true);
		getActivity().startService(swapIntent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
