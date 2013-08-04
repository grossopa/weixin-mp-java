/**
 * 
 */
package org.hamster.weixinmp.dao.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * 
 * @author grossopaforever@gmail.com
 * @version Aug 3, 2013
 *
 */
@Entity
@Table(name = "wx_auth")
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WxAuth extends WxBaseEntity {
	private String grantType;
	private String appid;
	private String secret;
	private String accessToken;
	
	@Column(name="grant_type", length = 50, nullable = false)
	public String getGrantType() {
		return grantType;
	}
	@Column(name="appid", length = 100, nullable = false)
	public String getAppid() {
		return appid;
	}
	@Column(name="secret", length = 100, nullable = false)
	public String getSecret() {
		return secret;
	}
	@Column(name="access_token", length = 100, nullable = false)
	public String getAccessToken() {
		return accessToken;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
