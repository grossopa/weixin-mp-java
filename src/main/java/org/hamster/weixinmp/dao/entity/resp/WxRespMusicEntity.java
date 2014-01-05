/**
 * 
 */
package org.hamster.weixinmp.dao.entity.resp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemMusicEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemThumbEntity;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "resp_music")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespMusicEntity extends WxBaseRespEntity {
	@ManyToOne
	@JoinColumn(name = "music_id", nullable = false)
	private WxItemMusicEntity music;
	@ManyToOne
	@JoinColumn(name = "thumb_id", nullable = false)
	private WxItemThumbEntity thumb;
}
