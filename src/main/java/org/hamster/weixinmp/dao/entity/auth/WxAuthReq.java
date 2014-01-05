/**
 * 
 */
package org.hamster.weixinmp.dao.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 27, 2013
 */
@Entity
@Table(name = "wx_auth_req")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class WxAuthReq extends WxBaseEntity {
	@Column(name = "signature", length = 100, nullable = false)
	private String signature;
	@Column(name = "timestamp", length = 50, nullable = false)
	private String timestamp;
	@Column(name = "nonce", length = 50, nullable = false)
	private String nonce;
	@Column(name = "echostr", length = 200, nullable = false)
	private String echostr;
}
