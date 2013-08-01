/**
 * 
 */
package org.hamster.weixinmp.dao.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 27, 2013
 */
@Entity
@Table(name = "wx_auth_req")
@ToString
@EqualsAndHashCode(callSuper = true)
public class WxAuthReq extends WxBaseEntity {
  
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	
	@Column(name="signature", length = 100, nullable = false)
	public String getSignature() {
		return signature;
	}
	
	@Column(name="timestamp", length = 50, nullable = false)
	public String getTimestamp() {
		return timestamp;
	}
	@Column(name="nonce", length = 50, nullable = false)
	public String getNonce() {
		return nonce;
	}
	@Column(name="echostr", length = 200, nullable = false)
	public String getEchostr() {
		return echostr;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

}
