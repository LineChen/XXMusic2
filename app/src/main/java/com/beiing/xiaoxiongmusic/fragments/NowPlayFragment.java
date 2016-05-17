package com.beiing.xiaoxiongmusic.fragments;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.xiaoxiongmusic.asynktasks.ImageLoadAsyncTask;
import com.beiing.xiaoxiongmusic.asynktasks.ImageLoadAsyncTask.LoadImageListner;
import com.beiing.xiaoxiongmusic.basics.BaseFragment;
import com.beiing.xiaoxiongmusic.configs.XxMusicConfigs;
import com.beiing.xiaoxiongmusic.entities.SongDetail;
import com.beiing.xiaoxiongmusic.service.XxMusicService;
import com.beiing.xiaoxiongmusic.utils.FileUtil;
import com.beiing.xiaoxiongmusic.utils.LogUtil;
import com.beiing.xxmusic.R;

public class NowPlayFragment extends BaseFragment {

	/**
	 * ���Ž��棺���ſ��Ƽ��������粥�š���һ�ס���һ�� --->ʵ��fragment֮���ͨ��
	 */
	public interface PlayContralListener {
		/**
		 * ������Ű�ť,�����ж��Ƿ��ǵ�һ�β��ţ���������;����
		 */
		public void onPlayClick();

		/**
		 * �����һ��
		 */
		public void onNextClick();

		/**
		 * �����һ��
		 */
		public void onPrevClick();

		/**
		 * ����������һ�׸�
		 */
		public void onCompleteSong();
	}

	PlayContralListener playContralListener;

	RelativeLayout rootRL;// ����ͼ

	TextView titleTv;// ������
	TextView artistTv;// �ݳ�����
	TextView curTimeTv;// ��ǰ���ŵ�λ��
	TextView totTimeTv;// ��ǰ��������ʱ��

	ImageButton playIb;// ���Ű�ť
	ImageButton prevIb;// ��һ��
	ImageButton nextIb;// ��һ��
	ImageButton modeIb;// ����ѭ����������

	 SeekBar seekBar;// ������

    private SimpleDateFormat dateFormat;
    private LocalBroadcastManager lbManager;// ���ع㲥������

    PrgReceiver prgReceiver;// ���ȹ㲥������

    private boolean isPlaying = false;

    // �������������
    private boolean haveStoped = false;// ֹֻͣһ��

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		playContralListener = (PlayContralListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_now_play, null);
		rootRL = (RelativeLayout) view.findViewById(R.id.fnp_rl_root);
		titleTv = (TextView) view.findViewById(R.id.fnp_title);
		artistTv = (TextView) view.findViewById(R.id.fnp_artist);
		curTimeTv = (TextView) view.findViewById(R.id.fnp_current_time);
		totTimeTv = (TextView) view.findViewById(R.id.fnp_total_time);

		playIb = (ImageButton) view.findViewById(R.id.fnp_contral_play);
		prevIb = (ImageButton) view.findViewById(R.id.fnp_contral_prev);
		nextIb = (ImageButton) view.findViewById(R.id.fnp_contral_next);
		modeIb = (ImageButton) view.findViewById(R.id.fnp_contral_play_mode);
		seekBar = (SeekBar) view.findViewById(R.id.fnp_seekbar);

		clickEvent();

		progEvent();

		return view;
	}

	/**
	 * �������϶��¼�
	 */
	private void progEvent() {
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				int cur = seekBar.getProgress();
				Intent intent = new Intent(
						XxMusicConfigs.ACTION_SEEKBAR_PROGERSS);
				intent.putExtra(XxMusicConfigs.EXTRA_MUSIC_CUR_LEN, cur);
				lbManager.sendBroadcast(intent);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// �϶�������ʱ����ǰʱ��ˢ��
				curTimeTv.setText(dateFormat.format(new Date(progress)));
			}
		});

	}

	/**
	 * ��ť����¼�
	 */
	private void clickEvent() {
		ClickListner listener = new ClickListner();
		playIb.setOnClickListener(listener);
		prevIb.setOnClickListener(listener);
		nextIb.setOnClickListener(listener);
		modeIb.setOnClickListener(listener);
	}

	/**
	 * �ж��Ƿ��ǵ�һ�ε�����Ű�������ֱ�ӵ�����Ű�ť�������ǵ�������б��е�һ��
	 */
	private boolean isFirst = true;

	class ClickListner implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.fnp_contral_play:
				if (isFirst) {
					playContralListener.onPlayClick();
					isFirst = false;
				} else {
					// �����ǰ���ڲ��ţ�����֪ͨ��service����ͣ�����򣬴��б���ѡ��һ�ײ���
					// ���Ż���ͣ
					Intent intent = new Intent(getActivity(),
							XxMusicService.class);
					intent.putExtra(XxMusicConfigs.EXTRA_CHANGE_MUSIC, false);
					getActivity().startService(intent);
					isPlaying = !isPlaying;
					if (isPlaying)
						playIb.setBackgroundResource(R.drawable.btn_pause);
					else
						playIb.setBackgroundResource(R.drawable.btn_play);
				}
				break;

			case R.id.fnp_contral_prev:
				playContralListener.onPrevClick();
				// ������һ��
				break;
			case R.id.fnp_contral_next:
				playContralListener.onNextClick();
				// ������һ��
				break;
			case R.id.fnp_contral_play_mode:
				// �޸Ĳ���ģʽ

				break;
			}
		}
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dateFormat = new SimpleDateFormat("mm:ss", Locale.CHINA);
		prgReceiver = new PrgReceiver();
		lbManager = LocalBroadcastManager.getInstance(getActivity());

		IntentFilter proFilter = new IntentFilter();
		proFilter.addAction(XxMusicConfigs.ACTION_MUSIC_PROGRESS);// ע����ȹ㲥Ƶ��
		proFilter.addAction(XxMusicConfigs.ACTION_MUSIC_COMPLETE);// ������ɵ�֪ͨ
		proFilter.addAction(XxMusicConfigs.ACTION_PLAY_MUSIC_ONLINE);// �����������ֵ�֪ͨ
		lbManager.registerReceiver(prgReceiver, proFilter);
	}

	/**
	 * �Զ���㲥������
	 */
	class PrgReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO ���ղ��ŷ�������з��͵Ľ��ȹ㲥
			String action = intent.getAction();
			if (action.equals(XxMusicConfigs.ACTION_MUSIC_PROGRESS)) {
				int max = intent.getIntExtra(
						XxMusicConfigs.EXTRA_MUSIC_TOTAL_LEN, 0);
				int cur = intent.getIntExtra(
						XxMusicConfigs.EXTRA_MUSIC_CUR_LEN, 0);
				seekBar.setMax(max);
				seekBar.setProgress(cur);

				curTimeTv.setText(dateFormat.format(new Date(cur)));
				totTimeTv.setText(dateFormat.format(new Date(max)));
			} else if (action.equals(XxMusicConfigs.ACTION_MUSIC_COMPLETE)) {
				// ����service���Ĳ�����ɵ�֪ͨ
				isPlaying = false;
				playIb.setBackgroundResource(R.drawable.btn_play);
				playContralListener.onCompleteSong();// �����������,ֱ��������һ��
				seekBar.setProgress(0);
			} else if (action.equals(XxMusicConfigs.ACTION_PLAY_MUSIC_ONLINE)) {
				// ������������
				SongDetail detail = (SongDetail) intent
						.getSerializableExtra(XxMusicConfigs.EXTRA_SONG_DETAIL_ONLINE);
				// Log.i("--", "�յ�����������Ϣ:" + detail.toString());
				updatePlayUI(detail);
			}
		}
	}

	/**
	 * ����UI����
	 * 
	 * @param detail
	 */
	public void updatePlayUI(SongDetail detail) {
		isPlaying = true;
		isFirst = false;
		if (playIb != null)
			playIb.setBackgroundResource(R.drawable.btn_pause);
		titleTv.setText(detail.getSongName());
		artistTv.setText(detail.getArtistName());

		final String artistHeadurl = detail.getSongPicBig();
		if (artistHeadurl != null && !TextUtils.isEmpty(artistHeadurl)) {
			Bitmap bgBitmap = FileUtil.readImage(artistHeadurl);
			if(bgBitmap != null){
				rootRL.setBackgroundDrawable(new BitmapDrawable(
						getResources(), bgBitmap));
			}else {
				// ���ظ���ͼƬ
				new ImageLoadAsyncTask(new LoadImageListner() {
					@Override
					public void imageLoadSuccess(Bitmap bitmap) {
						if (bitmap != null) {
							rootRL.setBackgroundDrawable(new BitmapDrawable(
									getResources(), bitmap));
							try {
								FileUtil.saveImage(artistHeadurl, bitmap,
										FileUtil.FORMAT_JPEG, bitmap.getWidth(),
										bitmap.getHeight());
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				}).execute(artistHeadurl);
			}
		} else{
			rootRL.setBackgroundResource(R.drawable.bg_main);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		lbManager.unregisterReceiver(prgReceiver);
	}

}
