package com.beiing.xiaoxiongmusic.entities;

public class ChannelMusician {
	private String name;
	
	private String artistid;
	
	private String avatar;

	public ChannelMusician() {
		super();
	}

	public ChannelMusician(String name, String artistid, String avatar) {
		super();
		this.name = name;
		this.artistid = artistid;
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistid() {
		return artistid;
	}

	public void setArtistid(String artistid) {
		this.artistid = artistid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "ChannelMusician [name=" + name + ", artistid=" + artistid
				+ ", avatar=" + avatar + "]";
	}
	
	
}
