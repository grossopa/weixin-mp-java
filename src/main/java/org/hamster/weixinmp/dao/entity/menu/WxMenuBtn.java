/**
 * 
 */
package org.hamster.weixinmp.dao.entity.menu;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 * 
 */
@Entity
@Table(name = "wx_menu_btn")
@ToString
@EqualsAndHashCode(callSuper = true)
public class WxMenuBtn extends WxBaseEntity {
	private String key;
	private String name;
	private String type;
	private WxMenuBtn parentButton;
	private List<WxMenuBtn> subButton;

	@Column(name = "key_", length = 50, nullable = false)
	public String getKey() {
		return key;
	}

	@Column(name = "name", length = 50, nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "type", length = 50, nullable = false)
	public String getType() {
		return type;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_button_id")
	public WxMenuBtn getParentButton() {
		return parentButton;
	}

	@OneToMany(mappedBy = "parentButton")
	public List<WxMenuBtn> getSubButton() {
		return subButton;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setParentButton(WxMenuBtn parentButton) {
		this.parentButton = parentButton;
	}

	public void setSubButton(List<WxMenuBtn> subButton) {
		this.subButton = subButton;
	}
}
