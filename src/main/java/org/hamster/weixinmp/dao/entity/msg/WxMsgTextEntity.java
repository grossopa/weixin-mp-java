/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgType;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 *
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_text")
@DiscriminatorValue(WxMsgType.TEXT)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgTextEntity extends WxBaseMsgEntity {
	@Column(name = "content", length = WxConfig.COL_LEN_CONTENT, nullable = false)
	private String content;
}
