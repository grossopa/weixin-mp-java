/**
 * 
 */
package org.hamster.weixinmp.dao.entity.qr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import com.google.gson.annotations.SerializedName;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "qr")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxQrEntity extends WxBaseEntity {
	@Column(name = "ticket", length = WxConfig.COL_LEN_TITLE, nullable = true)
	private String ticket;
	@Column(name = "scene", nullable = false)
	private Long scene;
	@Column(name = "expire_seconds", nullable = true)
	@SerializedName("expire_seconds")
	private Long expireSeconds;
}
