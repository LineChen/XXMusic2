package com.beiing.xiaoxiongmusic.configs;

public class XxMusicUrl {
	
	/**
	 * ��̨�б�url
	 */
	public static final String RADIOSTATION_LIST_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getCategoryList&format=json";
	
	/**
	 * ĳһ��̨�µĸ����б�
	 */
	public static final String MUSIC_LIST_OF_RADIOSTATION_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&pn=0&rn=10&channelname=%s";
	
	
	/**
	 * ĳһ��������ϸ��Ϣ
	 */
	public static final String MUSIC_DETAIL_URL = "http://ting.baidu.com/data/music/links?songIds=%s";
}






















