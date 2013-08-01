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
public abstract class WxBaseRespEntity extends WxBaseEntity {

	protected Integer funcFlag;
	protected String toUserName;
	protected String fromUserName;
	protected Long createTime;
	protected String msgType;

	@Column(name="func_flag", nullable = false)
	public Integer getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(Integer funcFlag) {
		this.funcFlag = funcFlag;
	}
	
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
}
