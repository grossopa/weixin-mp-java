/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;

import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = "wx_msg_event")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgEvent extends WxBaseMsgEntity {

	private String event;
	private String eventKey;

	@Column(name = "event", length = 20, nullable = false)
	public String getEvent() {
		return event;
	}

	@Column(name = "event_key", length = 20, nullable = false)
	public String getEventKey() {
		return eventKey;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
