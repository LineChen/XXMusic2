package com.beiing.xiaoxiongmusic.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * ��̨��Ϣ
 * @author Administrator
 *
 */
public class RadioStation {
	/**
	 * ��̨����
	 */
	private String title;
	
	/**
	 * ��̨id
	 */
	private String cid;
	
	/**
	 * Ƶ���б�
	 */
	private List<ChannelPublic> channellist;

	public RadioStation() {
		channellist = new ArrayList<ChannelPublic>();
	}

	public RadioStation(String title, String cid, List<ChannelPublic> channellist) {
		super();
		this.title = title;
		this.cid = cid;
		this.channellist = channellist;
	}

	public String getTitle() {
		return title;
	}

	public RadioStation setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getCid() {
		return cid;
	}

	public RadioStation setCid(String cid) {
		this.cid = cid;
		return this;
	}

	public List<ChannelPublic> getChannellist() {
		return channellist;
	}

	public RadioStation setChannellist(List<ChannelPublic> channellist) {
		this.channellist = channellist;
		return this;
	}

	@Override
	public String toString() {
		return "RadioStation [title=" + title + ", cid=" + cid
				+ ", channellist=" + channellist + "]";
	}

	
}
