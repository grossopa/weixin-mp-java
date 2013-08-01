/**
 * 
 */
package org.hamster.weixinmp.dao.entity.resp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;

import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = "wx_resp_text")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespText extends WxBaseRespEntity {
	private String content;

	@Column(name = "content", length = 2048, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
