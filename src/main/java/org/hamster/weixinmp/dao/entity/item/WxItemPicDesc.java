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
@Table(name = "wx_item_pic_desc")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxItemPicDesc extends WxBaseEntity {
	private String title;
	private String description;
	private String picUrl;
	private String url;

	@Column(name="title", length = 500, nullable = false)
	public String getTitle() {
		return title;
	}
	@Column(name="description", length = 2000, nullable = false)
	public String getDescription() {
		return description;
	}

	@Column(name="pic_url", length = 500, nullable = false)
	public String getPicUrl() {
		return picUrl;
	}

	@Column(name="url", length = 500, nullable = false)
	public String getUrl() {
		return url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
