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

import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 27, 2013
 *
 */
@MappedSuperclass
@ToString(callSuper = false)
public abstract class WxBaseEntity {

	protected Long id;
	protected Date createdDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
