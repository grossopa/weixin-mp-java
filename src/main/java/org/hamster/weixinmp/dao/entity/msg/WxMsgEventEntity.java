/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_event")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgEventEntity extends WxBaseMsgEntity {
	@Column(name = "event", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
	private String event;
	@Column(name = "event_key", length = WxConfig.COL_LEN_TITLE, nullable = true)
	private String eventKey;
	@Column(name = "ticket", length = WxConfig.COL_LEN_TITLE, nullable = true)
	private String ticket;
	@Column(name = "latitude", nullable = true)
	private Double latitude;
	@Column(name = "longitude", nullable = true)
	private Double longitude;
	@Column(name = "precision", nullable = true)
	private Double precision;
}
