/**
 * 
 */
package org.hamster.weixinmp.model.menu;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 *
 */
@Data
@AllArgsConstructor
public class WxMenuCreateJson {

	private List<WxMenuBtnEntity> button;

}
