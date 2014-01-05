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
import org.hamster.weixinmp.dao.entity.item.WxItemVoiceEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 29, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "resp_voice")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespVoiceEntity extends WxBaseRespEntity {

	@ManyToOne
	@JoinColumn(name = "voice_id")
	private WxItemVoiceEntity voice;
}
