package com.beiing.xiaoxiongmusic.fragments;

import java.util.List;

import com.beiing.xiaoxiongmusic.adapters.PbMuAdapter;
import com.beiing.xiaoxiongmusic.asynktasks.LoadAsyncTask;
import com.beiing.xiaoxiongmusic.asynktasks.LoadAsyncTask.LoadListener;
import com.beiing.xiaoxiongmusic.configs.XxMusicUrl;
import com.beiing.xiaoxiongmusic.entities.ChannelMusician;
import com.beiing.xiaoxiongmusic.entities.ChannelPublic;
import com.beiing.xxmusic.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ChMuFragment extends Fragment {

	GridView gridView;

	List<ChannelMusician> channelMusicians;

	PbMuAdapter pbMuAdapter;

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
				
			}
		});
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		new LoadAsyncTask(getActivity(), new LoadListener() {
			@Override
			public void loadSeccess(Object result) {
				if (result != null) {
					channelMusicians = (List<ChannelMusician>) result;
					pbMuAdapter = new PbMuAdapter(getActivity(),
							channelMusicians);
					gridView.setAdapter(pbMuAdapter);
				}
			}
		}).execute(XxMusicUrl.RADIOSTATION_LIST_URL,
				LoadAsyncTask.LOAD_STATION_MUSICIAN);
	}

}
