/**
 * 
 */
package org.hamster.weixinmp.dao.entity.resp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "resp_text")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespTextEntity extends WxBaseRespEntity {
	@Column(name = "content", length = WxConfig.COL_LEN_CONTENT, nullable = false)
	private String content;
}
