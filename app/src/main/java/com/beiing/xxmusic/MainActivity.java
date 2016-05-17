package com.beiing.xxmusic;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.xiaoxiongmusic.adapters.VPagerAdapter;
import com.beiing.xiaoxiongmusic.entities.SongDetail;
import com.beiing.xiaoxiongmusic.fragments.LocalListFragment;
import com.beiing.xiaoxiongmusic.fragments.LocalListFragment.MusicPlayListener;
import com.beiing.xiaoxiongmusic.fragments.NowPlayFragment;
import com.beiing.xiaoxiongmusic.fragments.NowPlayFragment.PlayContralListener;
import com.beiing.xiaoxiongmusic.fragments.StationFragment;
import com.beiing.xiaoxiongmusic.viewpageranimation.ZoomOutSlideTransformer;

public class MainActivity extends FragmentActivity implements
		PlayContralListener, MusicPlayListener {

	ViewPager vPager;
	VPagerAdapter vPagerAdapter;
	List<Fragment> fragments;

	LinearLayout LlNav;
	TextView locSongTv, stationTv;

	LocalListFragment localListFragment;
	StationFragment stationFragment;
	NowPlayFragment nowPlayFragment;

	FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LlNav = (LinearLayout) findViewById(R.id.main_ll_bottom);

		initVPager();

		vPagerEvent();

		modelClickEvent();

		selectNav(0);
	}

	/**
	 * �ײ�������ģ��ѡ���¼�
	 */
	private void modelClickEvent() {
		TextView tv = null;
		for (int i = 0; i < LlNav.getChildCount(); i++) {
			tv = (TextView) LlNav.getChildAt(i);
			tv.setTag(i);
			tv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = (Integer) v.getTag();
					vPager.setCurrentItem(position);
				}
			});
		}
	}

	/**
	 * ������ѡ��
	 * 
	 * @param position
	 */
	private void selectNav(int position) {
		TextView tv = null;
		for (int i = 0; i < LlNav.getChildCount(); i++) {
			tv = (TextView) LlNav.getChildAt(i);
			if (i != position)
				tv.setTextColor(getResources().getColor(R.color.black));
			else
				tv.setTextColor(getResources().getColor(R.color.deep_purl));
		}
	}

	/**
	 * vPger�¼�����
	 */
	private void vPagerEvent() {
		vPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				selectNav(position);
			}

			@Override
			public void onPageScrolled(int position, float offset,
					int offsetpiexs) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	/**
	 * ��ʼ��
	 */
	private void initVPager() {
		fragmentManager = getSupportFragmentManager();
		vPager = (ViewPager) findViewById(R.id.main_vPager);
		
		//����ViewPager�л�����
		vPager.setPageTransformer(true, new ZoomOutSlideTransformer());
		
		vPager.setOffscreenPageLimit(3);
		fragments = new ArrayList<Fragment>();

		localListFragment = new LocalListFragment();
		stationFragment = new StationFragment();
		nowPlayFragment = new NowPlayFragment();

		fragments.add(nowPlayFragment);
		fragments.add(localListFragment);
		fragments.add(stationFragment);
		vPagerAdapter = new VPagerAdapter(getSupportFragmentManager(),
				fragments);
		vPager.setAdapter(vPagerAdapter);
	}


	@Override
	public void onPlayClick() {
		// TODO ���Ž���������
		// ���ŵ�һ��
		onNextClick();
	}

	@Override
	public void onNextClick() {
		// TODO ���Ž�������һ��
		if (localListFragment != null) {
			localListFragment.playNextSong();
		}
	}

	@Override
	public void onPrevClick() {
		// TODO ���Ž�������һ��
		if (localListFragment != null) {
			localListFragment.playPrexSong();
		}
	}

	@Override
	public void onCompleteSong() {
		// TODO �Զ�������һ��
		onNextClick();
	}

	@Override
	public void onMusicPlay(SongDetail detail) {
		// TODO �����ֲ��ţ����²��Ž���
		if (nowPlayFragment != null)
			nowPlayFragment.updatePlayUI(detail);
	}

	
	private long firstClickTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - firstClickTime) > 2000) {
				Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�",
						Toast.LENGTH_SHORT).show();
				firstClickTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	
}
