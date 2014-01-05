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
 * @version Dec 29, 2013
 *
 */
@Entity
@Table(name=WxConfig.TABLE_PREFIX + "item_image")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxItemImageEntity extends WxBaseItemMediaEntity {
	@Column(name = "media_id", length = WxConfig.COL_LEN_INDICATOR, nullable = true)
	private String mediaId;
	@Column(name="pic_url", length=WxConfig.COL_LEN_URL, nullable=true)
	private String picUrl;
}