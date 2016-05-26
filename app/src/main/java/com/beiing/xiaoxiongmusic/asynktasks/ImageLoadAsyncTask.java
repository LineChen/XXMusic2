package com.beiing.xiaoxiongmusic.asynktasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.beiing.xiaoxiongmusic.application.XxMusicApplication;
import com.beiing.xiaoxiongmusic.utils.FileUtil;
import com.beiing.xiaoxiongmusic.utils.HttpUtils;

public class ImageLoadAsyncTask extends AsyncTask<String, Void, Bitmap> {

	public interface LoadImageListner{
		public void imageLoadSuccess(Bitmap bitmap);
	}
	
	LoadImageListner loadImageListner;
	private String imageUrl;

	public ImageLoadAsyncTask(LoadImageListner loadImageListner) {
		this.loadImageListner = loadImageListner;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = null;
		if (params[0] != null) {
			imageUrl = params[0];
			byte[] buffer = null;
			try {
				buffer = HttpUtils.getDataBytes(imageUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(buffer != null){
				bitmap = BitmapFactory.decodeByteArray(buffer, 0,
						buffer.length);
				XxMusicApplication.getBitMapCache().addBitmapToMemoryCache(imageUrl, bitmap);
			}
		}
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if(bitmap != null){
			loadImageListner.imageLoadSuccess(bitmap);
			XxMusicApplication.getBitMapCache().addBitmapToMemoryCache(imageUrl, bitmap);
		}
	}
}
