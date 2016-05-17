package com.beiing.xiaoxiongmusic.configs;

public class XxMusicConfigs {

	/**
	 * 播放另一首歌曲
	 */
	public static final String EXTRA_CHANGE_MUSIC = "change_music";
	
	/**
	 * 歌曲路径（包括本地和网络）
	 */
	public static final String EXTRA_MUSIC_PATH = "music_path";
	
	
	/**
	 * 歌曲进度action
	 */
	public static final String ACTION_MUSIC_PROGRESS = "broadcast.music.progress";
	/**
	 * 歌曲总时长
	 */
	public static final String EXTRA_MUSIC_TOTAL_LEN = "music_total_len";
	/**
	 * 当前播放位置
	 */
	public static final String EXTRA_MUSIC_CUR_LEN = "music_cur_len";
	
	/**
	 * 拖动进度条时发送的广播action
	 */
	public static final String ACTION_SEEKBAR_PROGERSS = "broadcast.music.seekbar.progress";
	
	/**
	 * 播放下一首 、 上一首
	 */
	public static final String ACTION_NEXT_OR_PREV_MUSIC = "broadcast.music.nextorprev";
	/**
	 * 播放上一首或下一首标识
	 */
	public static final String EXTRA_NEXT_OR_PREV_FLAG = "next_prev_flag";
	
	/**
	 * 播放停止，service发送广播出去
	 */
	public static final String ACTION_MUSIC_COMPLETE = "broadcast.music.complete";
	
	
	/**
	 * 播放在线音乐
	 */
	public static final String ACTION_PLAY_MUSIC_ONLINE = "broadcast.play.music.online";
	
	/**
	 * 播放在线音乐，发送音乐相信信息
	 */
	public static final String EXTRA_SONG_DETAIL_ONLINE = "song_detail_online";
}
























