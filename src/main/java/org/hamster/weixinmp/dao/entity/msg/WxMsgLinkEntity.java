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
@Table(name = WxConfig.TABLE_PREFIX + "msg_link")
@DiscriminatorValue(WxMsgType.LINK)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgLinkEntity extends WxBaseMsgEntity {
	@Column(name = "title", length = WxConfig.COL_LEN_TITLE, nullable = false)
	private String title;
	@Column(name = "description", length = WxConfig.COL_LEN_CONTENT, nullable = false)
	private String description;
	@Column(name = "url", length = WxConfig.COL_LEN_URL, nullable = false)
	private String url;
}
