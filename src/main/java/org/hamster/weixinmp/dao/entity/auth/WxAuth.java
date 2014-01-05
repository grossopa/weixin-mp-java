/**
 * 
 */
package org.hamster.weixinmp.dao.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * 
 * @author grossopaforever@gmail.com
 * @version Aug 3, 2013
 * 
 */
@Entity
@Table(name = "wx_auth")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WxAuth extends WxBaseEntity {
	@Column(name = "grant_type", length = 50, nullable = false)
	private String grantType;
	@Column(name = "appid", length = 100, nullable = false)
	private String appid;
	@Column(name = "secret", length = 100, nullable = false)
	private String secret;
	@SerializedName("access_token")
	@Column(name = "access_token", length = 200, nullable = false)
	private String accessToken;
	@SerializedName("expires_in")
	@Column(name = "expires_in", nullable = false)
	private Long expiresIn;

}
