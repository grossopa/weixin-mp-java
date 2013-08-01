/**
 * 
 */
package org.hamster.weixinmp.dao.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@MappedSuperclass
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class WxBaseMsgEntity extends WxBaseEntity {

	protected String toUserName;
	protected String fromUserName;
	protected Long createTime;
	protected String msgType;
	protected Long msgId;

	@Column(name = "to_user_name", length = 100, nullable = false)
	public String getToUserName() {
		return toUserName;
	}

	@Column(name = "from_user_name", length = 100, nullable = false)
	public String getFromUserName() {
		return fromUserName;
	}

	@Column(name = "create_time", nullable = false)
	public Long getCreateTime() {
		return createTime;
	}

	@Column(name = "msg_type", length = 20, nullable = false)
	public String getMsgType() {
		return msgType;
	}

	@Column(name = "msg_id", nullable = true)
	public Long getMsgId() {
		return msgId;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
}
