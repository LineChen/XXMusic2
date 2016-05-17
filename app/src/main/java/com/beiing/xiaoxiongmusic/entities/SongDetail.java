package com.beiing.xiaoxiongmusic.entities;

import java.io.Serializable;

public class SongDetail implements Serializable{

	/**
	 * 歌曲id
	 */
	private String songId;

	/**
	 * 歌曲名称
	 */
	private String songName;

	/**
	 * 演唱者Id
	 */
	private String artistId;

	/**
	 * 演唱者姓名
	 */
	private String artistName;

	/**
	 * 本首歌的专辑Id
	 */
	private String albumId;

	/**
	 * 本首歌的专辑名称
	 */
	private String albumName;

	/**
	 * 小图标
	 */
	private String songPicSmall;

	/**
	 * 大图标
	 */
	private String songPicBig;

	/**
	 * 所在电台图标
	 */
	private String songPicRadio;

	/**
	 * 歌词链接
	 */
	private String lrcLink;

	/**
	 * 歌曲时长
	 */
	private String time;

	/**
	 * 歌曲链接
	 */
	private String songLink;

	/**
	 * 歌曲链接
	 */
	private String showLink;

	/**
	 * 歌曲大小
	 */
	private String size;
	

	public SongDetail() {
		super();
	}

	public SongDetail setSongName(String songName) {
		this.songName = songName;
		return this;
	}
	

	public SongDetail setArtistName(String artistName) {
		this.artistName = artistName;
		return this;
	}
	

	public SongDetail setSongPicBig(String songPicBig) {
		this.songPicBig = songPicBig;
		return this;
	}
	
	
	
	public String getSongId() {
		return songId;
	}



	public void setSongId(String songId) {
		this.songId = songId;
	}



	public String getSongName() {
		return songName;
	}



	public String getArtistId() {
		return artistId;
	}



	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}



	public String getArtistName() {
		return artistName;
	}




	public String getAlbumId() {
		return albumId;
	}



	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}



	public String getAlbumName() {
		return albumName;
	}



	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}



	public String getSongPicSmall() {
		return songPicSmall;
	}



	public void setSongPicSmall(String songPicSmall) {
		this.songPicSmall = songPicSmall;
	}



	public String getSongPicBig() {
		return songPicBig;
	}



	public String getSongPicRadio() {
		return songPicRadio;
	}



	public void setSongPicRadio(String songPicRadio) {
		this.songPicRadio = songPicRadio;
	}



	public String getLrcLink() {
		return lrcLink;
	}



	public void setLrcLink(String lrcLink) {
		this.lrcLink = lrcLink;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getSongLink() {
		return songLink;
	}



	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}



	public String getShowLink() {
		return showLink;
	}



	public void setShowLink(String showLink) {
		this.showLink = showLink;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	@Override
	public String toString() {
		return "SongDetail [songId=" + songId + ", songName=" + songName
				+ ", artistId=" + artistId + ", artistName=" + artistName
				+ ", albumId=" + albumId + ", albumName=" + albumName
				+ ", songPicSmall=" + songPicSmall + ", songPicBig="
				+ songPicBig + ", songPicRadio=" + songPicRadio + ", lrcLink="
				+ lrcLink + ", time=" + time + ", songLink=" + songLink
				+ ", showLink=" + showLink + ", size=" + size + "]";
	}

	
}
