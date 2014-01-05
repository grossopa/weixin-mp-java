/**
 * 
 */
package org.hamster.weixinmp.dao.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 27, 2013
 * 
 */
@MappedSuperclass
@Data
@ToString(callSuper = false)
@EqualsAndHashCode
public abstract class WxBaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose(serialize = false, deserialize = false)
	@SerializedName("_id")
	protected Long id;
	@Column(name = "created_date")
	@Expose(serialize = false, deserialize = false)
	@SerializedName("_createddate")
	protected Date createdDate;
}
