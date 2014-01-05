/**
 * 
 */
package org.hamster.weixinmp.dao.entity.base;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hamster.weixinmp.config.WxConfig;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */

@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_base")
@DiscriminatorColumn(name = "msg_type", length = 20)
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class WxBaseMsgEntity extends WxBaseEntity {

	@Column(name = "to_user_name", length = WxConfig.COL_LEN_USER_NAME, nullable = false)
	protected String toUserName;
	@Column(name = "from_user_name", length = WxConfig.COL_LEN_USER_NAME, nullable = false)
	protected String fromUserName;
	@Column(name = "create_time", nullable = false)
	protected Long createTime;
	@Column(name = "msg_type", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
	protected String msgType;
	@Column(name = "msg_id", nullable = true)
	protected Long msgId;
}
