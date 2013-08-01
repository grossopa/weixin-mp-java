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
 *
 */
@Entity
@Table(name = "wx_msg_text")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgText extends WxBaseMsgEntity {

	private String content;
	
	@Column(name = "content", length = 500, nullable = false)
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
