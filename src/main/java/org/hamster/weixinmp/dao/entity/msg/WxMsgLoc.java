/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;

import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = "wx_msg_loc")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgLoc extends WxBaseMsgEntity {
	private Double locationX;
	private Double locationY;
	private Double scale;
	private String label;

	@Column(name = "location_x", nullable = false)
	public Double getLocationX() {
		return locationX;
	}

	@Column(name = "location_y", nullable = false)
	public Double getLocationY() {
		return locationY;
	}

	@Column(name = "scale", nullable = false)
	public Double getScale() {
		return scale;
	}

	@Column(name = "label", length = 500, nullable = false)
	public String getLabel() {
		return label;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
