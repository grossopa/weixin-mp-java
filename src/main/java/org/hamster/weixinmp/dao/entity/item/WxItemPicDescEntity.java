/**
 * 
 */
package org.hamster.weixinmp.dao.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseItemMediaEntity;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name=WxConfig.TABLE_PREFIX + "wx_item_pic_desc")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxItemPicDescEntity extends WxBaseItemMediaEntity {
	@Column(name="title", length = WxConfig.COL_LEN_TITLE, nullable = false)
	private String title;
	@Column(name="description", length = WxConfig.COL_LEN_CONTENT, nullable = false)
	private String description;
	@Column(name="pic_url", length = WxConfig.COL_LEN_URL, nullable = false)
	private String picUrl;
	@Column(name="url", length = WxConfig.COL_LEN_URL, nullable = false)
	private String url;
}