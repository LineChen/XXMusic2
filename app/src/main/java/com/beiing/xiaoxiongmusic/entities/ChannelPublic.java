package com.beiing.xiaoxiongmusic.entities;

/**
 * Ƶ��
 * @author Administrator
 *
 */
public class ChannelPublic {
	
	/**
	 * Ƶ������
	 */
	private String name;
	
	/**
	 * Ƶ��Id
	 */
	private String channelid;
	
	/**
	 * Ƶ��ͼ��
	 */
	private String thumb;
	
	/**
	 * Ƶ����Ϊ����������
	 */
	private String ch_name;
	
	/**
	 * Ƶ����ǩ��
	 */
	private String cate_name;
	
	/**
	 * Ƶ����ǩ��ƴ����ʾ
	 */
	private String cate_sname;

	public ChannelPublic(String name, String channelid, String thumb, String ch_name,
			String cate_name, String cate_sname) {
		this.name = name;
		this.channelid = channelid;
		this.thumb = thumb;
		this.ch_name = ch_name;
		this.cate_name = cate_name;
		this.cate_sname = cate_sname;
	}

	public ChannelPublic() {
	}

	public String getName() {
		return name;
	}

	public ChannelPublic setName(String name) {
		this.name = name;
		return this;
	}

	public String getChannelid() {
		return channelid;
	}

	public ChannelPublic setChannelid(String channelid) {
		this.channelid = channelid;
		return this;
	}

	public String getThumb() {
		return thumb;
	}

	public ChannelPublic setThumb(String thumb) {
		this.thumb = thumb;
		return this;
	}

	public String getCh_name() {
		return ch_name;
	}

	public ChannelPublic setCh_name(String ch_name) {
		this.ch_name = ch_name;
		return this;
	}

	public String getCate_name() {
		return cate_name;
	}

	public ChannelPublic setCate_name(String cate_name) {
		this.cate_name = cate_name;
		return this;
	}

	public String getCate_sname() {
		return cate_sname;
	}

	public ChannelPublic setCate_sname(String cate_sname) {
		this.cate_sname = cate_sname;
		return this;
	}

	@Override
	public String toString() {
		return "Channel [name=" + name + ", channelid=" + channelid
				+ ", thumb=" + thumb + ", ch_name=" + ch_name + ", cate_name="
				+ cate_name + ", cate_sname=" + cate_sname + "]";
	}
	
	
}

















