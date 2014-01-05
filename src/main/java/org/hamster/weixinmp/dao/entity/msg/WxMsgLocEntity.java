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
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_loc")
@DiscriminatorValue(WxMsgType.LOCATION)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgLocEntity extends WxBaseMsgEntity {
	@Column(name = "location_x", nullable = false)
	private Double locationX;
	@Column(name = "location_y", nullable = false)
	private Double locationY;
	@Column(name = "scale", nullable = false)
	private Double scale;
	@Column(name = "label", length = WxConfig.COL_LEN_TITLE, nullable = false)
	private String label;
}
