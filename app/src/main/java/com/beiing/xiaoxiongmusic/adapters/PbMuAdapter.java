package com.beiing.xiaoxiongmusic.adapters;

import java.io.FileNotFoundException;
import java.util.List;

import com.beiing.xiaoxiongmusic.adapters.PbChAdapter.ViewHolder;
import com.beiing.xiaoxiongmusic.application.XxMusicApplication;
import com.beiing.xiaoxiongmusic.asynktasks.ImageLoadAsyncTask;
import com.beiing.xiaoxiongmusic.asynktasks.ImageLoadAsyncTask.LoadImageListner;
import com.beiing.xiaoxiongmusic.defviews.RoundImageView;
import com.beiing.xiaoxiongmusic.entities.ChannelMusician;
import com.beiing.xiaoxiongmusic.entities.ChannelPublic;
import com.beiing.xiaoxiongmusic.utils.FileUtil;
import com.beiing.xxmusic.R;
import com.bumptech.glide.Glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class PbMuAdapter extends BaseAdapter {
	private Context context;

	private List<ChannelMusician> list;

	private GridView gridView;

	public PbMuAdapter(Context context, List<ChannelMusician> list) {
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
			gridView = (GridView) parent;
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_channel, parent, false);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.item_channelPic);
			holder.textView = (TextView) convertView
					.findViewById(R.id.item_channelName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.imageView.setImageResource(R.drawable.default_img);

		ChannelMusician chm = list.get(position);
		holder.textView.setText(chm.getName());
		final String imgurl = chm.getAvatar();
		if (imgurl != null) {
			Glide.with(context).load(imgurl).into(holder.imageView);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView textView;

	}

}
