package com.beiing.xiaoxiongmusic.fragments;

import java.util.ArrayList;
import java.util.List;

import com.beiing.xiaoxiongmusic.adapters.PbChAdapter;
import com.beiing.xiaoxiongmusic.asynktasks.LoadAsyncTask;
import com.beiing.xiaoxiongmusic.asynktasks.LoadAsyncTask.LoadListener;
import com.beiing.xiaoxiongmusic.configs.XxMusicUrl;
import com.beiing.xiaoxiongmusic.entities.ChannelPublic;
import com.beiing.xxmusic.OnlineSongListActivity;
import com.beiing.xxmusic.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

public class ChPbFragment extends Fragment {
	
	GridView gridView;

	List<ChannelPublic> channelPublics;
	
	PbChAdapter pbChAdapter;
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_channel_list, null);
		gridView = (GridView) view.findViewById(R.id.fcl_ch_listGv);	
		
		gvEvent();
		return view;
	}
	
	/**
	 * Ƶ������¼�
	 */
	private void gvEvent() {
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String ch_name = channelPublics.get(position).getCh_name();
				String title = channelPublics.get(position).getName();
				OnlineSongListActivity.startOnlineSlActivity(getActivity(), title, ch_name);
			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		new LoadAsyncTask(getActivity(), new LoadListener() {
			@Override
			public void loadSeccess(Object result) {
				if(result != null){
					channelPublics = (List<ChannelPublic>) result;
					pbChAdapter = new PbChAdapter(getActivity(), channelPublics);
					gridView.setAdapter(pbChAdapter);
				}
			}
		}).execute(XxMusicUrl.RADIOSTATION_LIST_URL, LoadAsyncTask.LOAD_STATION_PUBLIC);
	}
}












