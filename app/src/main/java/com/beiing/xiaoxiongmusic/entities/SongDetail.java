package com.beiing.xiaoxiongmusic.entities;

import java.io.Serializable;

public class SongDetail implements Serializable{

	/**
	 * ����id
	 */
	private String songId;

	/**
	 * ��������
	 */
	private String songName;

	/**
	 * �ݳ���Id
	 */
	private String artistId;

	/**
	 * �ݳ�������
	 */
	private String artistName;

	/**
	 * ���׸��ר��Id
	 */
	private String albumId;

	/**
	 * ���׸��ר������
	 */
	private String albumName;

	/**
	 * Сͼ��
	 */
	private String songPicSmall;

	/**
	 * ��ͼ��
	 */
	private String songPicBig;

	/**
	 * ���ڵ�̨ͼ��
	 */
	private String songPicRadio;

	/**
	 * �������
	 */
	private String lrcLink;

	/**
	 * ����ʱ��
	 */
	private String time;

	/**
	 * ��������
	 */
	private String songLink;

	/**
	 * ��������
	 */
	private String showLink;

	/**
	 * ������С
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
