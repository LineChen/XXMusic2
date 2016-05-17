package com.beiing.xiaoxiongmusic.adapters;

import java.io.FileNotFoundException;
import java.util.List;

import com.beiing.xiaoxiongmusic.adapters.PbChAdapter.ViewHolder;
import com.beiing.xiaoxiongmusic.application.XxMusicApplication;
import com.beiing.xiaoxiongmusic.asynktasks.ImageLoadAsyncTask;
import com.beiing.xiaoxiongmusic.asynktasks.ImageLoadAsyncTask.LoadImageListner;
import com.beiing.xiaoxiongmusic.entities.SongBrief;
import com.beiing.xiaoxiongmusic.utils.FileUtil;
import com.beiing.xxmusic.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OnlineSongAdapter extends BaseAdapter {

	private Context context;

	private List<SongBrief> list;

	private ListView listView;

	public OnlineSongAdapter(Context context, List<SongBrief> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (parent != null)
			listView = (ListView) parent;

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_online_song, parent, false);
			holder.headIv = (ImageView) convertView
					.findViewById(R.id.item_online_head);
			holder.listenIb = (ImageView) convertView
					.findViewById(R.id.item_online_listen);
			holder.dloadIb = (ImageView) convertView
					.findViewById(R.id.item_online_download);
			holder.nameTv = (TextView) convertView
					.findViewById(R.id.item_online_name);
			holder.artistTv = (TextView) convertView
					.findViewById(R.id.item_online_artist);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		SongBrief brief = list.get(position);
		holder.listenIb.setImageResource(R.drawable.try_to_listen_music);
		holder.dloadIb.setImageResource(R.drawable.btn_down_loading);
		holder.nameTv.setText(brief.getTitle());
		holder.artistTv.setText(brief.getArtist());
		holder.headIv.setImageResource(R.drawable.default_img);
		final String imgurl = brief.getThumb();
		if (imgurl != null) {
			holder.headIv.setTag(imgurl);
			Bitmap bitmap = XxMusicApplication.getBitMapCache()
					.getBitmapFromMemoryCache(imgurl);
			if (bitmap == null) {
				bitmap = FileUtil.readImage(imgurl);
				if (bitmap == null){
					new ImageLoadAsyncTask(new LoadImageListner() {
						@Override
						public void imageLoadSuccess(Bitmap bitmap) {
							ImageView head = (ImageView) listView
									.findViewWithTag(imgurl);
							if (head != null)
								head.setImageBitmap(bitmap);
							// ���浽��չ��
							try {
								// ѹ������
								FileUtil.saveImage(imgurl, bitmap,
										FileUtil.FORMAT_PNG, 100, 100);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
					}).execute(imgurl);
				}
			}else
				holder.headIv.setImageBitmap(bitmap);
		}

		return convertView;
	}

	class ViewHolder {
		ImageView headIv;
		ImageView listenIb;
		ImageView dloadIb;
		TextView nameTv;
		TextView artistTv;
	}

}
