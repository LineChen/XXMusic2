package com.beiing.xiaoxiongmusic.entities;

/**
 * ������Ҫ����ʾ�ڵ�̨�����б���
 * @author Administrator
 *
 */
public class SongBrief {

	/**
	 * ����Id
	 */
	private String songid;
	
	/**
	 * ������
	 */
	private String title;
	/**
	 * �ݳ���
	 */
	private String artist;
	/**
	 * ͼ��
	 */
	private String thumb;
	/**
	 * �ݳ���Id
	 */
	private String artist_id;
	/**
	 * �����ݳ���Id
	 */
	private String all_artist_id;
	/**
	 * ��δ֪
	 */
	private String all_rate;

	public SongBrief() {
		super();
	}

	public SongBrief(String songid, String title, String artist, String thumb,
			String artist_id, String all_artist_id, String all_rate) {
		super();
		this.songid = songid;
		this.title = title;
		this.artist = artist;
		this.thumb = thumb;
		this.artist_id = artist_id;
		this.all_artist_id = all_artist_id;
		this.all_rate = all_rate;
	}

	public String getSongid() {
		return songid;
	}

	public void setSongid(String songid) {
		this.songid = songid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}

	public String getAll_artist_id() {
		return all_artist_id;
	}

	public void setAll_artist_id(String all_artist_id) {
		this.all_artist_id = all_artist_id;
	}

	public String getAll_rate() {
		return all_rate;
	}

	public void setAll_rate(String all_rate) {
		this.all_rate = all_rate;
	}
	
	
}







