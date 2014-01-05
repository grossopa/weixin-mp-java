/**
 * 
 */
package org.hamster.weixinmp.dao.entity.user;

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
 * @version Dec 30, 2013
 *
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "user_group")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxGroupEntity extends WxBaseEntity {
	@SerializedName("id")
	@Column(name="wx_id", nullable=false)
	private Long wxId;
	@Column(name="name", length=WxConfig.COL_LEN_TITLE, nullable=false)
	private String name;
	@Column(name="count", nullable=false)
	private String count;
}
