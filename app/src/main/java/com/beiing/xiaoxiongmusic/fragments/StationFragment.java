package com.beiing.xiaoxiongmusic.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.xiaoxiongmusic.basics.BaseFragment;
import com.beiing.xxmusic.R;

public class StationFragment extends BaseFragment {
	
	TextView pbChTv, muChTv;
	
	FragmentManager fragmentManager;
	
	ChPbFragment pbChFragment;
	ChMuFragment muChFragment;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragmentManager = getActivity().getSupportFragmentManager();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_station, null);
		pbChTv = (TextView) view.findViewById(R.id.fs_tv_public_ch);
		muChTv = (TextView) view.findViewById(R.id.fs_tv_public_musician);
		
		btnEvent();
		
		return view;
	}
	
	/**
	 * button����¼�
	 */
	private void btnEvent() {
		pbChTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Toast.makeText(getActivity(), "�������Ƶ��", 0).show();
				FragmentTransaction transaction = fragmentManager.beginTransaction();
				transaction.show(pbChFragment);
				transaction.hide(muChFragment);
				transaction.commit();
				pbChTv.setTextColor(getResources().getColor(R.color.deep_purl));
				muChTv.setTextColor(getResources().getColor(R.color.black));
				
			}
		});
		
		muChTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Toast.makeText(getActivity(), "���������Ƶ��", 0).show();
				FragmentTransaction transaction = fragmentManager.beginTransaction();
				transaction.show(muChFragment);
				transaction.hide(pbChFragment);
				transaction.commit();
				muChTv.setTextColor(getResources().getColor(R.color.deep_purl));
				pbChTv.setTextColor(getResources().getColor(R.color.black));
			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		pbChFragment = new ChPbFragment();
		muChFragment = new ChMuFragment();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.fs_stationFl, pbChFragment);
		transaction.add(R.id.fs_stationFl, muChFragment);
		transaction.hide(muChFragment);
		transaction.commit();
	}
	
}










