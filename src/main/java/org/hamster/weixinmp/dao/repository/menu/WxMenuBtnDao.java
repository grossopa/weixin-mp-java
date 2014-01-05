/**
 * 
 */
package org.hamster.weixinmp.dao.repository.menu;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 *
 */
@Repository
public interface WxMenuBtnDao extends PagingAndSortingRepository<WxMenuBtnEntity, Long> {

}
