/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgType;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVoiceEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 24, 2013
 *
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_voice")
@DiscriminatorValue(WxMsgType.VOICE)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgVoiceEntity extends WxBaseMsgEntity {
	@ManyToOne
	@JoinColumn(name="voice_id")
	WxItemVoiceEntity voice;
}
