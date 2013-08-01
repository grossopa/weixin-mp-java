/**
 * 
 */
package org.hamster.weixinmp.dao.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
@Entity
@Table(name = "wx_item_music")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxItemMusic extends WxBaseEntity {

	private String title;
	private String description;
	private String musicUrl;
	private String hqMusicUrl;
	
	@Column(name="title", length = 500, nullable = false)
	public String getTitle() {
		return title;
	}
	@Column(name="description", length = 2048, nullable = false)
	public String getDescription() {
		return description;
	}
	@Column(name="music_url", length = 500, nullable = false)
	public String getMusicUrl() {
		return musicUrl;
	}
	@Column(name="hq_music_url", length = 500, nullable = false)
	public String getHqMusicUrl() {
		return hqMusicUrl;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}
	
	

}
