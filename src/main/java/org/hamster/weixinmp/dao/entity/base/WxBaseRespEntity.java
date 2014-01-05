/**
 * 
 */
package org.hamster.weixinmp.dao.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hamster.weixinmp.config.WxConfig;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class WxBaseRespEntity extends WxBaseEntity {
	@Column(name="func_flag", nullable = false)
	protected Integer funcFlag;
	@Column(name = "to_user_name", length = WxConfig.COL_LEN_USER_NAME, nullable = false)
	protected String toUserName;
	@Column(name = "from_user_name", length = WxConfig.COL_LEN_USER_NAME, nullable = false)
	protected String fromUserName;
	@Column(name = "create_time", nullable = false)
	protected Long createTime;
	@Column(name = "msg_type", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
	protected String msgType;
}
