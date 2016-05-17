package com.beiing.xiaoxiongmusic.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.beiing.xiaoxiongmusic.entities.ChannelMusician;
import com.beiing.xiaoxiongmusic.entities.ChannelPublic;
import com.beiing.xiaoxiongmusic.entities.RadioStation;
import com.beiing.xiaoxiongmusic.entities.SongBrief;
import com.beiing.xiaoxiongmusic.entities.SongDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonParseUtil {
	
	/**
	 * 获得公共频道
	 * @param jsonStr
	 * @return
	 * @throws JSONException
	 */
	public static List<ChannelPublic> parsePbChs(String jsonStr) throws JSONException{
		List<ChannelPublic> channelPublics = new ArrayList<ChannelPublic>();
		JSONObject json = new JSONObject(jsonStr);
		JSONArray jArr = json.getJSONArray("result");
		JSONObject jo = jArr.getJSONObject(0);
		JSONArray jclist = jo.getJSONArray("channellist");
		Gson gson = new Gson();
		TypeToken<List<ChannelPublic>> token = new TypeToken<List<ChannelPublic>>(){};
		channelPublics = gson.fromJson(jclist.toString(), token.getType());
		return channelPublics;		
	}
	
	/**
	 * 解析音乐人频道
	 * @param jsonStr
	 * @return
	 * @throws JSONException 
	 */
	public static List<ChannelMusician> parseMuChs(String jsonStr) throws JSONException{
		List<ChannelMusician> channelMusicians = new ArrayList<ChannelMusician>();
		JSONObject json = new JSONObject(jsonStr);
		JSONArray jArr = json.getJSONArray("result");
		JSONObject jo = jArr.getJSONObject(1);
		JSONArray jclist = jo.getJSONArray("channellist");
		Gson gson = new Gson();
		TypeToken<List<ChannelMusician>> token = new TypeToken<List<ChannelMusician>>(){};
		channelMusicians = gson.fromJson(jclist.toString(), token.getType());
		return channelMusicians;
	}
	
	
	/**
	 * 解析电台歌曲列表
	 * @param jsonStr
	 * @return
	 * @throws JSONException
	 */
	public static List<SongBrief> parseSongBrief(String jsonStr) throws JSONException{
		List<SongBrief> list = new ArrayList<SongBrief>();
		JSONObject json = new JSONObject(jsonStr);
		JSONObject jr = json.getJSONObject("result");
		JSONArray jasonglist = jr.getJSONArray("songlist");
		Gson gson = new Gson();
		TypeToken<List<SongBrief>> token = new TypeToken<List<SongBrief>>(){};
		list = gson.fromJson(jasonglist.toString(), token.getType());
		return list;		
	}
	
	/**
	 * 歌曲详情解析
	 * @param jsonStr
	 * @return
	 * @throws JSONException
	 */
	public static SongDetail parseSongdetail(String jsonStr) throws JSONException{
		SongDetail songDetail = null;
		JSONObject json = new JSONObject(jsonStr);
		JSONObject jd = json.getJSONObject("data");
		JSONArray ja = jd.getJSONArray("songList");
		JSONObject js = ja.getJSONObject(0);
		Gson gson = new Gson();
		songDetail = gson.fromJson(js.toString(), SongDetail.class);
		return songDetail;		
	}
	
}










