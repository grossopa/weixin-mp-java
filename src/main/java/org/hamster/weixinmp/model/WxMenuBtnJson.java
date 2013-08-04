/**
 * 
 */
package org.hamster.weixinmp.model;

import java.util.ArrayList;
import java.util.List;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMenuBtnJson {
	
	private String key;
	private String name;
	private String type;
	private List<WxMenuBtnJson> sub_button;
	
	public WxMenuBtnJson(WxMenuBtn wxMenuBtn) {
		this.key = wxMenuBtn.getKey();
		this.name = wxMenuBtn.getName();
		this.type = wxMenuBtn.getType();
		sub_button = new ArrayList<WxMenuBtnJson>();
		if (wxMenuBtn.getSubButton() != null) {
			for (WxMenuBtn obj : wxMenuBtn.getSubButton()) {
				sub_button.add(new WxMenuBtnJson(obj));
			}
		}
	}
	
}
