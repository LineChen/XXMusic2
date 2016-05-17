package com.beiing.xiaoxiongmusic.basics;

import com.beiing.xiaoxiongmusic.utils.LogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		LogUtil.i("--", this.getClass().getSimpleName() + "--onAttach");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.i("--", this.getClass().getSimpleName() + "--onCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		LogUtil.i("--", this.getClass().getSimpleName() + "--onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		LogUtil.i("--", this.getClass().getSimpleName() + "--onStart");
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		LogUtil.i("--", this.getClass().getSimpleName() + "--onPause");
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		LogUtil.i("--", this.getClass().getSimpleName() + "--onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		LogUtil.i("--", this.getClass().getSimpleName() + "--onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtil.i("--", this.getClass().getSimpleName() + "--onDestroy");
	}
	
}



