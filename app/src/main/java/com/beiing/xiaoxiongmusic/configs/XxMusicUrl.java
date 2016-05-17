package com.beiing.xiaoxiongmusic.configs;

public class XxMusicUrl {
	
	/**
	 * 电台列表url
	 */
	public static final String RADIOSTATION_LIST_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getCategoryList&format=json";
	
	/**
	 * 某一电台下的歌曲列表
	 */
	public static final String MUSIC_LIST_OF_RADIOSTATION_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&pn=0&rn=10&channelname=%s";
	
	
	/**
	 * 某一歌曲的详细信息
	 */
	public static final String MUSIC_DETAIL_URL = "http://ting.baidu.com/data/music/links?songIds=%s";
}






















